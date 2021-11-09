package ft.utils;

import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final int IMPLICIT_WAIT_TIMEOUT = 5;
    private static WebDriver driver;

    public static WebDriver getDriver(Browser browser) {
                switch (browser) {
                    case FIREFOX:
                        driver = new FirefoxDriver();
                        break;
                    case CHROME:
                        driver = new ChromeDriver();
                        break;
                    case IE10:
                        driver = new InternetExplorerDriver();
                        break;
                    case SAFARI:
                        driver = new SafariDriver();
                        break;
                    default:
                        throw new IllegalStateException("Unsupported browser type");
                }
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        return driver;
    }
    /**
     * Finishes browser
     */
    public static void finishTest() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
