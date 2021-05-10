package L3_WebdriverAutotests;

import L4_Assertions.TriangleAreaTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class CRMTest {
    private static WebDriver driver;
    private static final String CRM_URL = "https://crm.geekbrains.space/user/login";
    private static Logger logger = LoggerFactory.getLogger(TriangleAreaTest.class);

    @BeforeEach
    public void preConditions() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "//driver/chromedriver.exe");
        System.out.println("---TEST STARTS---");
        driver = new ChromeDriver();
        driver.get(CRM_URL);
    }

    @Test
    @DisplayName("Проверка успешного Логина в систему")
    public void CRMLoginTest() {
        logger.debug("test-info");
        WebElement login = driver.findElement(By.id("prependedInput"));
        WebElement password = driver.findElement(By.id("prependedInput2"));
        WebElement submit = driver.findElement(By.id("_submit"));
        login.isDisplayed();
        login.sendKeys("Applanatest1");
        password.isDisplayed();
        password.sendKeys("Student2020!");
        submit.isDisplayed();
        submit.click();
        String urlToAssert = driver.getCurrentUrl();
        Assert.assertTrue(urlToAssert.equals("https://crm.geekbrains.space/"));
    }

    @Test
    @DisplayName("Проверка ошибки дубликата имени и цвета надписи")
    public void CRMSaveSalesTest() {
        logger.debug("test-info");
        CRMLoginTest();
        Actions actions = new Actions(driver);

        Waiters.waitForVisibleByXpath(driver,"//span[@class='title' and text()='Продажи']");
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='title' and text()='Продажи']")))
                .build()
                .perform();

        Waiters.waitForVisibleByXpath(driver,"//span[@class='title' and text()='Счета']");
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='title' and text()='Счета']")))
                .click()
                .build()
                .perform();

        Waiters.waitForVisibleByXpath(driver, "//div[@class='btn btn-link dropdown-toggle']");
        actions
                .moveToElement(driver.findElement(By.xpath("//div[@class='btn btn-link dropdown-toggle']")))
                .click()
                .build()
                .perform();

        Waiters.waitForVisibleByXpath(driver,"//a[text()='Сохранить как']");
        actions
                .moveToElement(driver.findElement(By.xpath("//a[text()='Сохранить как']")))
                .click()
                .build()
                .perform();
        driver.findElement(By.id("gridViewName")).sendKeys("TestFile");
        driver.findElement(By.xpath("//a[text()='Сохранить']")).click();

        Waiters.waitForVisibleByXpath(driver,"//span[text()='This name already exists.']");
        WebElement element = driver.findElement(By.xpath("//span[text()='This name already exists.']"));
        Assert.assertEquals(element.getText(), "This name already exists.");
        Assert.assertEquals(element.getCssValue("color"), "rgba(200, 23, 23, 1)");
    }

    @AfterEach
    public void postConditions() {
        driver.quit();
        System.out.println("---TEST ENDS---");
    }


}

