package L3_WebdriverAutotests.IberiaTest;


import L6_PageObject_PagesForIberia.LoginPage;
import L6_PageObject_PagesForIberia.RegisterPage;
import L7_Allure.Listeners.PowerLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseTestLogic {
    EventFiringWebDriver driver;
    WebDriverWait webDriverWait;
    L6_PageObject_PagesForIberia.LoginPage loginPage;
    L6_PageObject_PagesForIberia.RegisterPage registerPage;

    @BeforeAll
    static void setAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setWeb() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new PowerLogger());
        webDriverWait = new WebDriverWait(driver, 5);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @AfterEach
    void systemClear() {
        List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
        driver.manage().logs().get(LogType.BROWSER).getAll().forEach(System.out::println);
        driver.quit();
    }
}
