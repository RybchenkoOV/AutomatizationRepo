package L6_PageObject_PagesForIberia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class BasePage {
        WebDriver driver;
        WebDriverWait webDriverWait;

        public BasePage(WebDriver driver) {
            this.driver = driver;
            webDriverWait = new WebDriverWait(driver, 5);
            PageFactory.initElements(driver, this);
        }
    }

