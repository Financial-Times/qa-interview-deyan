package ft.pages;

import ft.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FtHomePage extends BasePage {

    @FindBy(xpath = "//a[@id='o-header-link-0']")
    private WebElement homeButton;


    @FindBy(xpath = "//nav[@id='o-header-nav-desktop']//a[contains(@data-trackable, \"Subscribe\")]")
    private WebElement subscribeButton;

    public FtHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened(){
        String ariaLabelTag = homeButton.getAttribute("aria-label");
        return ariaLabelTag.equals("Home, current page");
    }

    public SubscribePage clickOnSubscribeButton(){
        subscribeButton.click();
        return new SubscribePage(getDriver());
    }

    public String getTitle(){
       return getDriver().getTitle();
    }
}
