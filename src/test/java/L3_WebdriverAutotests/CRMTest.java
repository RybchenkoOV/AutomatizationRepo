package L3_WebdriverAutotests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class CRMTest
{
    private static WebDriver driver;
    private static final String CRM_URL = "https://crm.geekbrains.space/user/login";


    @Before
    public void preConditions() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "//driver/chromedriver.exe");
        System.out.println("---TEST STARTS---");
        driver = new ChromeDriver();
    }

    @Test
    public void CRMLoginTest() {
        driver.get(CRM_URL);
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
    public void CRMSaveSalesTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        CRMLoginTest();
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(By.xpath("//span[@class='title' and text()='Продажи']")))
                .moveToElement(driver.findElement(By.xpath("//span[@class='title' and text()='Счета']")))
                .click()
                .build()
                .perform();
        actions
                .moveToElement(driver.findElement(By.xpath("//div[@class='btn btn-link dropdown-toggle']")))
                .click()
                .moveToElement(driver.findElement(By.xpath("//a[text()='Сохранить как']")))
                .click();
        driver.findElement(By.id("gridViewName")).sendKeys("TestFile");
        driver.findElement(By.xpath("//a[text()='Сохранить']")).click();
    }

    @After
    public void postConditions() {
        driver.quit();
        System.out.println("---TEST ENDS---");
    }
}
