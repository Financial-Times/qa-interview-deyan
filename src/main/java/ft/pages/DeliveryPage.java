package ft.pages;

import ft.common.BasePage;
import ft.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DeliveryPage extends BasePage {

    //This xpath check for class o-stepped-progress__label, then will return the parent if 'step--current' class exist.
    @FindBy(xpath = "//*[contains(@class, 'o-stepped-progress__label') " +
            "and text()=\"Delivery\"]/parent::div[contains(@class, 'step--current')]")
    private WebElement deliveryStep;

    @FindBy(xpath = "//legend[contains(text(),'Delivery')]")
    private WebElement deliveryFormTitle;

    @FindBy(css = "div.ns__highlight>p>span")
    private WebElement postalCode;

    @FindBy(css = "#deliveryOptionField>span>label")
    private List<WebElement> deliveryOptions;

    @FindBy(xpath = "//input[@id=\"deliveryStartDate\"]")
    private WebElement date;

    @FindBy(xpath = "#start-date-picker-title-span")
    private WebElement dateTitle;

    public DeliveryPage(WebDriver driver) {
        super(driver);
    }

    public String getPostalCode(){
        return postalCode.getText();
    }

    public WebElement getDeliveryOption(String option){
       WebElement element = deliveryOptions.stream()
                .filter(deliveryOption ->
                        deliveryOption.findElement(By.cssSelector("span.o-forms-input__label>span.ncf__delivery-option__title"))
                                .getText().equals(option)).findFirst().orElse(null);
       if(element == null){
           throw new NullPointerException(option + "doesn't exist!");
       }
        return element;
    }

    public void clickOnPaperVoucherOption(){
        getDeliveryOption("Paper vouchers").click();
    }

    public void typeNonSaturdayDate(){
        date.sendKeys(generateNonSaturdayDay());
    }

    public boolean isDateSaturday(){
        Helper.waitForSeconds(5);
        LocalDate dateField = LocalDate.parse(getDateFieldValue());
        return dateField.getDayOfWeek().name().toUpperCase().equals(DayOfWeek.SATURDAY.toString());
    }

    private String generateNonSaturdayDay(){
        LocalDate localDate = LocalDate.now().plusDays(8);
        if(localDate.getDayOfWeek().name().toUpperCase().equals(DayOfWeek.SATURDAY.toString())){
            localDate=localDate.plusDays(1);
        }
        return localDate.toString();
    }

    /**
     * This function will verify that current date in the field is at least seven days from now
     */
    public boolean isDateAtLeastSevenDaysFromNow(){
        LocalDate sevenDaysFromNow = LocalDate.now().plusDays(7);
        LocalDate dateAfterChanges = LocalDate.parse(getDateFieldValue());
        return dateAfterChanges.isAfter(sevenDaysFromNow);
    }

    private String getDateFieldValue(){
        return date.getAttribute("value");
    }

    public boolean isCurrentStep() {
        return deliveryStep.isDisplayed();
    }

    public boolean isDeliveryTitleExist() {
        return deliveryStep.isDisplayed();
    }
}
