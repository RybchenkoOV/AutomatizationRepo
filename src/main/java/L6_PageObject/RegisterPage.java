package L6_PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this );
    }

    @FindBy(xpath = "//div[@class='ibe-header-new__item ibe-header-new__login']")
    public WebElement menuIcon;

    @FindBy(xpath = "//*[@id='ibplus-loginformMobile']/p[3]/a")
    public WebElement goToRegister;

    @FindBy(id = "name")
    public WebElement nameField;

    @FindBy(id = "surname1")
    public WebElement surnameField;

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//label[@for='is_adult']")
    public WebElement checkAdultField;

    @FindBy(xpath = "//label[@for='conditionsCheck']")
    public WebElement checkConditionsField;

    @FindBy(xpath = "//div[@class='ib-msg-alert ib-msg-alert--danger icon-mas_2 alert']")
    public WebElement submitForm;

}




//
//        driver.findElement(By.id("name")).sendKeys("Jhony");
//        driver.findElement(By.id("surname1")).sendKeys("Rocketfingers");
//        driver.findElement(By.id("email")).sendKeys("orbs@qa.team");
//        driver.findElement(By.id("password")).sendKeys("Q!W@E#r4");
//
//        Waiters.waitForVisibleByXpath(driver, "//label[@for='is_adult']");
//        WebElement element = driver.findElement(By.xpath("//label[@for='is_adult']"));
//        element.click();
//        element.click(); // Забавный тут баг - приходится 2 раза кликать
//
//        Waiters.waitForVisibleByXpath(driver, "//label[@for='conditionsCheck']");
//        driver.findElement(By.xpath("//label[@for='conditionsCheck']")).click();
//
//        Waiters.waitForVisibleByXpath(driver,"//button[text()='Создать пользователя']");
//        driver.findElement(By.xpath("//button[text()='Создать пользователя']")).click();
//
//        Waiters.waitForVisibleByXpath(driver,"//div[@class='ib-msg-alert ib-msg-alert--danger icon-mas_2 alert']");
//        WebElement div = driver.findElement(By.xpath("//div[@class='ib-msg-alert ib-msg-alert--danger icon-mas_2 alert']"));
//        Assert.assertEquals(div.getCssValue("background-color"), "rgba(168, 25, 26, 1)");