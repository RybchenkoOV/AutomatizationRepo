package L3_WebdriverAutotests.IberiaTest;
import L3_WebdriverAutotests.Waiters;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

public class IberiaTest extends BaseTestLogic {
    public final String SITE_URL = "https://www.iberia.com/ru/";

    @BeforeEach
    public void goToPage() {
        driver.get(SITE_URL);
        driver.manage().deleteAllCookies();
        Waiters.waitForVisibleById(driver,"onetrust-accept-btn-handler");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    @Test
    @DisplayName("Check successful system login ")
    @Feature("Check Auth Feature ")
    void IberiaLoginTest() {
        loginPage.openAuthMenu();
        loginPage.inputLogin("orbs@qa.team");
        loginPage.inputPassword("Q!W@E#r4");
        loginPage.buttonSubmit();
        WebElement greetings = driver.findElement(
                By.xpath("//span[@class='ibe-header-new__logged-txt ibe-header-new__logged-txt--" +
                        "underline hidden-xs hidden-sm hidden-md']"));
        Assertions.assertEquals(greetings.getText(),"Добро Пожаловать!");
    }

    @Test
    @DisplayName("Check successful system login ")
    @Feature("Check Auth Feature ")
    void CheckDuplicateRegisterTest() {
        registerPage.menuIcon();
        registerPage.goToRegister();
        registerPage.nameField("Jhony");
        registerPage.surnameField("Rocketfingers");
        registerPage.emailField("orbs@qa.team");
        registerPage.passwordField("Q!W@E#r4");
        registerPage.checkAdultField();
        registerPage.checkConditionsField();
        registerPage.submitForm();

        WebElement greetings = driver.findElement(
                By.xpath("//span[@class='ibe-header-new__logged-txt ibe-header-new__logged-txt--" +
                        "underline hidden-xs hidden-sm hidden-md']"));
        Assertions.assertEquals("Добро Пожаловать!", greetings.getText());
    }
}


