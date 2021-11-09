package ft.pages;

import ft.common.BasePage;
import ft.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailsPage extends BasePage {

    //This xpath check for class o-stepped-progress__label, then will return the parent if 'step--current' class exist.
    @FindBy(xpath = "//*[contains(@class, 'o-stepped-progress__label') and" +
            " text()=\"Details\"]/parent::div[contains(@class, 'step--current')]")
    private WebElement detailsStep;

    @FindBy(xpath = "//legend[contains(text(),'Details')]")
    private WebElement detailsFormTitle;

    @FindBy(css = "div.ncf__package-change__content>p")
    private WebElement chosenProductElement;

    @FindBy(css = "fieldset.ncf__fieldset>legend.ncf__legend")
    private WebElement detailsText;

    @FindBy(xpath = "//button[@id=\"submitButton\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@id=\"email\"]")
    private WebElement emailAddress;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement password;

    @FindBy(xpath = "//input[@id=\"firstName\"]")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id=\"lastName\"]")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id=\"primaryTelephone\"]")
    private WebElement mobileNumber;

    @FindBy(xpath = "//input[@id=\"deliveryPostcode\"]")
    private WebElement deliveryPostcode;


    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getChosenProduct(){
        return chosenProductElement;
    }

    public DeliveryPage fillDetailsForm(){
        emailAddress.sendKeys("ft@".concat(Helper.generateRandomString()).concat(".com"));
        password.sendKeys("FT@TEST12");
        firstName.sendKeys("Deyan");
        lastName.sendKeys("Bahrin");
        mobileNumber.sendKeys("123456789");
        deliveryPostcode.sendKeys("SW15 1BN");
        submitButton.click();
        return new DeliveryPage(getDriver());
    }

    public boolean isCurrentStep(){
        return detailsStep.isDisplayed();
    }

    public boolean isDetailsTitleExist(){
        return detailsFormTitle.isDisplayed();
    }


}
