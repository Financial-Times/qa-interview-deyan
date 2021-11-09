package ft.pages;

import ft.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class UKProductsPage extends BasePage {

    @FindBy(css = "section.ft-products-subs>div")
    private List<WebElement> regionSubscriptionProducts;

    public UKProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasSelectButton(WebElement element){
        return element.findElement(By.linkText("Select")).isEnabled();
    }

    public WebElement getProductPrice(String product){
        WebElement priceForProduct = getSingleProduct(product)
                .findElement(By.cssSelector("div.subscription-grid-item>div.subscription-grid-item__pricing"));
        Assert.assertTrue(priceForProduct.isDisplayed());
        return priceForProduct;
    }


    public WebElement getSingleProduct(String productTitle){
        WebElement element = regionSubscriptionProducts.stream()
                .filter(x -> x.findElement(By.cssSelector("h4"))
                        .getText().equals(productTitle)).findFirst().orElse(null);
        if(element == null){
            throw new NullPointerException(productTitle + "doesn't exist!");
        }
        return element;
    }

    public DetailsPage selectUKProduct(String UkProduct){
        getSingleProduct(UkProduct).findElement(By.linkText("Select")).click();
        return new DetailsPage(getDriver());
    }

}
