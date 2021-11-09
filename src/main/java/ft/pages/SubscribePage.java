package ft.pages;

import ft.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubscribePage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(),'Choose your subscription')]")
    private WebElement chooseSubscribtionText;

    @FindBy(css = "div.o-subs-card__container>div")
    private List<WebElement> subscriptionProducts;

    @FindBy(xpath = "//a[@data-trackable=\"weekend\"]")
    private WebElement weekendPrintButton;

    public SubscribePage(WebDriver driver) {
        super(driver);
    }

    public boolean hasSelectButton(WebElement subscriptionProduct) {
        return subscriptionProduct.findElement(By.cssSelector("a.o-subs-card__select-button")).isEnabled();
    }

    public WebElement getSubscriptionProductByTitle(String title){
            WebElement element = subscriptionProducts.stream()
                    .filter(x -> x.findElement(By.cssSelector("h3"))
                            .getText().equals(title)).findFirst().orElse(null);
            if(element == null){
                throw new NullPointerException(title + "doesnt exists!");
            }
        return element;
    }

    public String getSubscriptionPrice(String product){
        WebElement element = getSubscriptionProductByTitle(product);
        return element.findElement(By.cssSelector("div.o-subs-card__charge-value ")).getText();
    }

    public String getSubscriptionPromoPrice(String product){
        WebElement element = getSubscriptionProductByTitle(product);
        return element.findElement(By.cssSelector("div.o-subs-card__promo>div.o-subs-card__charge-value ")).getText();
    }


    public WeekendPrintPage selectWeekendPrint(){
        weekendPrintButton.click();
        return new WeekendPrintPage(getDriver());
    }

    public WebElement getWeekendPrintButton(){
        return weekendPrintButton;
    }
}
