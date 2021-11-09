package ft.pages;

import ft.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WeekendPrintPage extends BasePage {
    //
    @FindBy(xpath = "//select[@id=\"country_selector_desktop\"]")
    private WebElement regionDropdownOptions;

    @FindBy(xpath = "//h4[contains(text(),'ePaper')]")
    private WebElement ePaperText;

    @FindBy(xpath = "//div[@class=\"subscription-grid-item__pricing hide-for-small \"]//span")
    private WebElement weekendPrintPrice;

    @FindBy(css = "div.subscription-grid-item__header>div.subscription-grid-item__cta>a")
    private WebElement selectButton;

    public WeekendPrintPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return ePaperText.isDisplayed();
    }

    public String getCurrentRegionText(){
        Select dropdownSelection = new Select(regionDropdownOptions);
        WebElement currentRegion = dropdownSelection.getFirstSelectedOption();
        return currentRegion.getText();
    }

    public UKProductsPage changeRegionToUK(String region){
        Select dropdownSelection = new Select(regionDropdownOptions);
        dropdownSelection.selectByVisibleText(region);
        return new UKProductsPage(getDriver());
    }

    public String getPriceAsText(){
       return weekendPrintPrice.getText();
    }

    public boolean isSelectButtonActive(){
        return selectButton.isEnabled();
    }

}
