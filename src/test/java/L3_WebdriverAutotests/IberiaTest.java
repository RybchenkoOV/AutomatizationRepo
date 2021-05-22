package L3_WebdriverAutotests;


import L4_Assertions.TriangleAreaTest;
import L6_PageObject.LoginPage;
import L6_PageObject.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class IberiaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    LoginPage loginPage;
    RegisterPage registerPage;

    private static final String IBERIA_URL = "https://www.iberia.com";
    private static Logger logger = LoggerFactory.getLogger(TriangleAreaTest.class);

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver,5);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        driver.get(IBERIA_URL);
        driver.manage().deleteAllCookies();
        Waiters.waitForVisibleById(driver,"onetrust-accept-btn-handler");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    @Test
    void loginTest() {
        loginPage.openAuthMenu.click();
        loginPage.inputLogin.sendKeys("orbs@qa.team");
        loginPage.inputPassword.sendKeys("Q!W@E#r4");
        loginPage.buttonSubmit.click();
    }

    @Test
    void dublicateRegister() throws InterruptedException {
        registerPage.menuIcon.click();
        registerPage.goToRegister.click();
        registerPage.nameField.sendKeys("Jhony");
        registerPage.surnameField.sendKeys("Rocketfingers");
        registerPage.emailField.sendKeys("orbs@qa.team");
        registerPage.passwordField.sendKeys("Q!W@E#r4");
        registerPage.checkAdultField.click();
        registerPage.checkAdultField.click();
        registerPage.checkConditionsField.click();

        WebElement element = driver.findElement(By.xpath("//p[@class='ib-msg-alert__text ng-binding']"));
        Assert.assertEquals(element.getText(),
                "The registration could not be completed. Please try again in a few minutes. " +
                        "If the problem persists, contact our Customer Services. ");
        Thread.sleep(10000);
    }

    @AfterEach
    void closeBrowser() {
    driver.quit();
    }
}

