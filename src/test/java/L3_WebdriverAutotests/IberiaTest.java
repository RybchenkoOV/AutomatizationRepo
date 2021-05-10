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


public class IberiaTest {

    private static WebDriver driver;
    private static final String IBERIA_URL = "https://www.iberia.com";
    private static Logger logger = LoggerFactory.getLogger(TriangleAreaTest.class);

    @BeforeEach
    public void preConditions() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "//driver/chromedriver.exe");
        System.out.println("---TEST STARTS---");
        driver = new ChromeDriver();
        driver.get(IBERIA_URL);
        driver.manage().deleteAllCookies();
        Waiters.waitForVisibleById(driver,"onetrust-accept-btn-handler");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    @Test
    @DisplayName("Ошибка при валидном входе в систему")
    public void errorInValidLoginTest() throws InterruptedException {
        logger.debug("log");
        driver.manage().window().setSize(new Dimension(945,1020));

        Waiters.waitForVisibleByCSS(driver,".ibe-header-new__link:nth-child(2)");
        driver.findElement(By.cssSelector(".ibe-header-new__link:nth-child(2)")).click();

        Waiters.waitForVisibleById(driver, "userMenuMobile");
        driver.findElement(By.id("userMenuMobile")).sendKeys("orbs@qa.team");

        Waiters.waitForVisibleById(driver, "pinMenuMobile");
        driver.findElement(By.id("pinMenuMobile")).sendKeys("Q!W@E#r4");
        Thread.sleep(1000); // Здесно нужно подождать, потому что видимо в системе стоит защита от роботов

        Actions actions = new Actions(driver);
        Waiters.waitForVisibleByXpath(driver, "//*[@id=\"ibplus-loginformMobile\"]/button[1]");
        actions
                .moveToElement(driver.findElement(By.xpath("//*[@id=\"ibplus-loginformMobile\"]/button[1]")))
                .click()
                .build()
                .perform();

        Waiters.waitForVisibleByXpath(driver,
                "//a[@class='ibe-header-new__logged-link icon-editar_perfil logged-responsive collapsed']");
        WebElement enterAccount = driver.findElement(By
                .xpath("//a[@class='ibe-header-new__logged-link icon-editar_perfil logged-responsive collapsed']"));

        /** Проверяем, есть ли вход в аккаунт. Если значок появился, то идем по проверкам дальше */
        Assert.assertTrue(enterAccount.isDisplayed());
        driver.findElement(By
                .xpath("//a[@class='ibe-header-new__logged-link icon-editar_perfil logged-responsive collapsed']")).click();
        Waiters.waitForVisibleById(driver,"//p[@id='loggedCardId-NewFormId']");
        String card = driver.findElement(By.id("//p[@id='loggedCardId-NewFormId']")).getText();
        Assert.assertEquals(card, "13947551");
    }

    @Test
    @DisplayName("Нельзя повторно зарегистрировать пользователя")
    public void cantRegisterDuplicateUserTest() {
        logger.debug("log");
        driver.manage().window().setSize(new Dimension(1936,1056));

        Waiters.waitForVisibleByXpath(driver, "//*[@id=\"publicUser\"]/a[1]/span");
        driver.findElement(By.xpath("//*[@id=\"publicUser\"]/a[1]/span")).click();

        Waiters.waitForVisibleByXpath(driver, "//*[@id=\"top-login\"]/fieldset/p[3]/a");
        driver.findElement(By.xpath("//*[@id=\"top-login\"]/fieldset/p[3]/a")).click();

        driver.findElement(By.id("name")).sendKeys("Jhony");
        driver.findElement(By.id("surname1")).sendKeys("Rocketfingers");
        driver.findElement(By.id("email")).sendKeys("orbs@qa.team");
        driver.findElement(By.id("password")).sendKeys("Q!W@E#r4");

        Waiters.waitForVisibleByXpath(driver, "//label[@for='is_adult']");
        WebElement element = driver.findElement(By.xpath("//label[@for='is_adult']"));
        element.click();
        element.click(); // Забавный тут баг - приходится 2 раза кликать

        Waiters.waitForVisibleByXpath(driver, "//label[@for='conditionsCheck']");
        driver.findElement(By.xpath("//label[@for='conditionsCheck']")).click();

        Waiters.waitForVisibleByXpath(driver,"//button[text()='Создать пользователя']");
        driver.findElement(By.xpath("//button[text()='Создать пользователя']")).click();

        Waiters.waitForVisibleByXpath(driver,"//div[@class='ib-msg-alert ib-msg-alert--danger icon-mas_2 alert']");
        WebElement div = driver.findElement(By.xpath("//div[@class='ib-msg-alert ib-msg-alert--danger icon-mas_2 alert']"));
        Assert.assertEquals(div.getCssValue("background-color"), "rgba(168, 25, 26, 1)");

    }


    @Test
    @DisplayName("Проверка вылетов")
    public void checkFlightsTest() {
        logger.debug("log");
        driver.manage().window().setSize(new Dimension(1200,800));

        Waiters.waitForPresenceByXpath(driver,"//input[@id='flight_origin1']");
        driver.findElement(By.xpath("//input[@id='flight_origin1']")).sendKeys("Москва");

        Waiters.waitForPresenceByXpath(driver,"//input[@id='flight_destiny1']");
        driver.findElement(By.xpath("//input[@id='flight_destiny1']")).sendKeys("Тенерифе");

        Waiters.waitForPresenceByXpath(driver, "//label[text()='Дата Туда']");
        driver.findElement(By.xpath("//label[text()='Дата Туда']")).click();
        Waiters.waitForPresenceByXpath(driver, "//*[@id=\"dp1620654409435\"]/div/div[1]/table/tbody/tr[5]/td[3]");

        Actions actions = new Actions(driver);

        WebElement date1 = driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']"));
        actions
                .moveToElement(date1)
                .click()
                .build()
                .perform();

        Waiters.waitForPresenceByXpath(driver, "//label[text()='Дата Обратно']");
        driver.findElement(By.xpath("//label[text()='Дата Обратно']")).click();
        Waiters.waitForPresenceByXpath(driver, "//*[@id=\"dp1620654409435\"]/div/div[2]/table/tbody/tr[2]/td[6]");
        driver.findElement(By.xpath("//*[@id=\"dp1620654409435\"]/div/div[2]/table/tbody/tr[2]/td[6]")).click();

        driver.findElement(By.xpath("//button[@id='buttonSubmit1']")).click();

        driver.findElement(By.xpath("//button[@id='flight_passengers1']")).click();
        Waiters.waitForPresenceAllByXpath(driver,"//ul[@class='ibe-people-counter__list']");

        Waiters.waitForPresenceByXpath(driver,"//span[text()='невозможно отобразит рейсы']");
        String elTexto = driver.findElement(By.xpath("//span[text()='невозможно отобразит рейсы']")).getText();

        Assert.assertEquals(elTexto, "невозможно отобразить рейсы"); // Тут ошибка локализации в переводе

    }


    @AfterEach
    public void postConditions() {
        driver.quit();
        System.out.println("---TEST ENDS---");
    }


}
