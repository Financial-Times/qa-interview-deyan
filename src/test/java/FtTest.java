
import enums.Browser;
import enums.CountryEnums;
import enums.SubscriptionProductsEnums;
import enums.WeekendPrintProductsEnums;
import ft.pages.*;
import ft.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class FtTest {

    private WebDriver driver;

    @BeforeTest(description = "Initialize web driver")
    public void InitBrowser(){
        //Change path to chromedriver.
        //System.setProperty("webdriver.chrome.driver","your/path/to/chromedriver-ver95.exe");
        driver = WebDriverFactory.getDriver(Browser.CHROME);
        driver.get("https://www.ft.com");
    }

    @Test(description = "FT Automation test")
    public void FinancialTimeTest() {
        //FT page is open
        FtHomePage ftHomePage = new FtHomePage(driver);
        Assert.assertTrue(ftHomePage.isPageOpened());
        Assert.assertEquals(ftHomePage.getTitle(), "Financial Times");

        //Subscribe Page is Open
        SubscribePage subscribePage = ftHomePage.clickOnSubscribeButton();

        //Verify Trial, Digital and EPaper options are available
        List<WebElement> subscribeProducts = new ArrayList<>();
        subscribeProducts.add(subscribePage.getSubscriptionProductByTitle(SubscriptionProductsEnums.TRIAL.getTitle()));
        subscribeProducts.add(subscribePage.getSubscriptionProductByTitle(SubscriptionProductsEnums.DIGITAL.getTitle()));
        subscribeProducts.add(subscribePage.getSubscriptionProductByTitle(SubscriptionProductsEnums.EPAPER.getTitle()));
        subscribeProducts.forEach(product -> Assert.assertTrue(product.isDisplayed()));
        subscribeProducts.forEach(product -> Assert.assertTrue(subscribePage.hasSelectButton(product)));

        //Verify Weekend Print Button
        Assert.assertTrue(subscribePage.getWeekendPrintButton().isDisplayed());
        Assert.assertTrue(subscribePage.getWeekendPrintButton().isEnabled());
        Assert.assertEquals(subscribePage.getWeekendPrintButton().getText(),
                WeekendPrintProductsEnums.WEEKEND_PRINT.getTitle());

        //Verify the Price and Promo Price of Digital Subscription
        Assert.assertEquals(subscribePage
                        .getSubscriptionPrice(SubscriptionProductsEnums.DIGITAL.getTitle()),
                SubscriptionProductsEnums.DIGITAL.getPrice());
        Assert.assertEquals(subscribePage
                        .getSubscriptionPromoPrice(SubscriptionProductsEnums.DIGITAL.getTitle()),
                SubscriptionProductsEnums.DIGITAL.getPromoPrice());

        //Weekend Print page is open and Verify that ePaper is presented as option.
        WeekendPrintPage weekendPrintPage = subscribePage.selectWeekendPrint();
        Assert.assertTrue(weekendPrintPage.isPageOpened());
        Assert.assertTrue(weekendPrintPage.isSelectButtonActive());

        // Verify that Bulgaria is selected as region and Verify the price
        Assert.assertEquals(weekendPrintPage.getCurrentRegionText(), CountryEnums.BULGARIA.getCountry());
        Assert.assertEquals(weekendPrintPage.getPriceAsText(), CountryEnums.BULGARIA.getPrice());

        // Change the region to United Kingdom
        UKProductsPage ukProductsPage = weekendPrintPage.changeRegionToUK(CountryEnums.UNITED_KINGDOM.getCountry());

        //Verify that Weekend Print, Weekend Print + Standard Digital and
        // Weekend Print + Premium Digital are available as options
        List<WebElement> productForRegion = new ArrayList<>();
        productForRegion
                .add(ukProductsPage.getSingleProduct(WeekendPrintProductsEnums.WEEKEND_PRINT.getTitle()));
        productForRegion
                .add(ukProductsPage.getSingleProduct(WeekendPrintProductsEnums.WEEKEND_PREMIUM_DIGITAL.getTitle()));
        productForRegion
                .add(ukProductsPage.getSingleProduct(WeekendPrintProductsEnums.WEEKEND_STANDARD_DIGITAL.getTitle()));
        productForRegion.forEach(productRegion -> Assert.assertTrue(productRegion.isDisplayed()));
        productForRegion.forEach(element -> Assert.assertTrue(ukProductsPage.hasSelectButton(element)));

        //Verify the price for Weekend Print and Weekend Print + Premium Digital
        Assert.assertEquals(ukProductsPage.getProductPrice(
                WeekendPrintProductsEnums.WEEKEND_PRINT.getTitle()).getText(),
                WeekendPrintProductsEnums.WEEKEND_PRINT.getPrice());

        Assert.assertEquals(ukProductsPage.
                        getProductPrice(WeekendPrintProductsEnums.WEEKEND_PREMIUM_DIGITAL.getTitle()).getText(),
                WeekendPrintProductsEnums.WEEKEND_PREMIUM_DIGITAL.getPrice());

        // Select Weekend Print + Premium Digital
        DetailsPage detailsPage = ukProductsPage
                .selectUKProduct(WeekendPrintProductsEnums.WEEKEND_PREMIUM_DIGITAL.getTitle());

        // Verify Details Page is open
        Assert.assertTrue(detailsPage.getChosenProduct().isDisplayed());
        Assert.assertEquals(detailsPage.getChosenProduct().getText(),
                "You have chosen " + WeekendPrintProductsEnums.WEEKEND_PREMIUM_DIGITAL.getTitle());

        Assert.assertTrue(detailsPage.isCurrentStep());
        Assert.assertTrue(detailsPage.isDetailsTitleExist(), "Details Title doesn't exist!");

        // Click let's get Started and
        // Fill in the text boxes with valid data (valid email, use “123456789” for phone and “SW15 1BN” for postal code
        DeliveryPage deliveryPage = detailsPage.fillDetailsForm();

        //Verify user is on Delivery page
        Assert.assertTrue(deliveryPage.isCurrentStep());
        Assert.assertTrue(deliveryPage.isDeliveryTitleExist(), "Title doesn't exist!");

        //Verify the postal code from the previous page is present
        Assert.assertEquals(deliveryPage.getPostalCode(), "SW15 1BN");

        //Verify Home Delivery and Paper Vouchers Options
        WebElement homeDelivery = deliveryPage.getDeliveryOption("Home delivery");
        Assert.assertTrue(homeDelivery.isDisplayed());
        Assert.assertTrue(homeDelivery.isEnabled());
        WebElement paperVouchers = deliveryPage.getDeliveryOption("Paper vouchers");
        Assert.assertTrue(paperVouchers.isDisplayed());
        Assert.assertTrue(paperVouchers.isEnabled());

        //Select Paper vouchers
        deliveryPage.clickOnPaperVoucherOption();

        //Verify that the Delivery start date is changed to a Saturday day
        Assert.assertTrue(deliveryPage.isDateSaturday(), "Expected date to be Saturday");

        //Modify the Delivery start date with a random non-Saturday day from the future
        deliveryPage.typeNonSaturdayDate();

        //Lose focus of the field - By clicking again on paper voucher we lose focus on the date field.
        deliveryPage.clickOnPaperVoucherOption();

        //Verify that the Delivery start date is again updated to a Saturday day
        Assert.assertTrue(deliveryPage.isDateSaturday(), "Expected date to be Saturday");

        //Verify that the updated Delivery start date is at least 7 days further into the future from the “today”
        Assert.assertTrue(deliveryPage.isDateAtLeastSevenDaysFromNow(), "Days are not seven days from now");
    }

    @AfterTest(description = "Close WebDriver")
    public void closeBrowser(){
        WebDriverFactory.finishTest();
    }
}
