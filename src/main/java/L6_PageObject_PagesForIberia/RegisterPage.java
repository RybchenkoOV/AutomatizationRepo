package L6_PageObject_PagesForIberia;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage{

    WebDriver driver;

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

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click the Menu Icon ")
    public RegisterPage menuIcon() {
        menuIcon.click();
        return this;
    }

    @Step("Go to Register ")
    public RegisterPage goToRegister() {
        goToRegister.click();
        return this;
    }

    @Step("Input Name ")
    public RegisterPage nameField(String name) {
        nameField.sendKeys(name);
        return this;
    }

    @Step("Input Surname ")
    public RegisterPage surnameField(String surname) {
        surnameField.sendKeys(surname);
        return this;
    }

    @Step("Input Email ")
    public RegisterPage emailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("Input Password ")
    public RegisterPage passwordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Checkbox isAdult ")
    public RegisterPage checkAdultField() {
        checkAdultField.click();
        checkAdultField.click();
        return this;
    }

    @Step("Checkbox Conditions ")
    public RegisterPage checkConditionsField() {
        checkConditionsField.click();
        return this;
    }

    @Step("Submit form ")
    public RegisterPage submitForm() {
        submitForm.click();
        return this;
    }

}
