package L3_WebdriverAutotests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class IberiaTest {

    private static WebDriver driver;
    private static final String IBERIA_URL = "https://www.iberia.com";

    @Before
    public void preConditions() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "//driver/chromedriver.exe");
        System.out.println("---TEST STARTS---");
        driver = new ChromeDriver();
    }

    @Test
    public void errorInValidLoginTest() throws InterruptedException {
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.get(IBERIA_URL);
        driver.manage().window().setSize(new Dimension(945,1020));
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.cssSelector(".ibe-header-new__link:nth-child(2)")).click();
        driver.findElement(By.id("userMenuMobile")).sendKeys("orbs@qa.team");
        Thread.sleep(1000);
        driver.findElement(By.id("pinMenuMobile")).sendKeys("QWERT1");
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(By.xpath("//*[@id=\"ibplus-loginformMobile\"]/button[1]")))
                .click()
                .build()
                .perform();
        Thread.sleep(4000); // Тут мы должны были зайти, то не заходим - глюк у Иберии.
    }

    @Test
    public void cantRegisterDuplicateUserTest() throws InterruptedException {
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.get(IBERIA_URL);
        driver.manage().window().setSize(new Dimension(1936,1056));
        Thread.sleep(1000);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        driver.findElement(By.xpath("//*[@id=\"publicUser\"]/a[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"top-login\"]/fieldset/p[3]/a")).click();

        driver.findElement(By.id("name")).sendKeys("Jhony");
        Thread.sleep(1000);
        driver.findElement(By.id("surname1")).sendKeys("Rocketfingers");
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("orbs@qa.team");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("QWERT1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[@for='is_adult']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[@for='conditionsCheck']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Создать пользователя']")).click();


        String error = driver.findElement(By.xpath("//span[@class='ib-alert ib-alert--error ng-binding ng-scope']"))
                .getText();

        Assert.assertTrue(error.equals("К сожалению, невозможно использовать этот адрес электронной почты. " +
                "Повторите попытку."));

    }
    @After
    public void postConditions() {
        driver.quit();
        System.out.println("---TEST ENDS---");
    }


}
