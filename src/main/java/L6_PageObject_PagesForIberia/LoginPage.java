package L6_PageObject_PagesForIberia;

import L7_Allure.Pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    WebDriver driver;

    @FindBy(css = ".ibe-header-new__link:nth-child(2)")
    public WebElement openAuthMenu;

    @FindBy(id = "userMenuMobile")
    public WebElement inputLogin;

    @FindBy(id = "pinMenuMobile")
    public WebElement inputPassword;

    @FindBy(xpath = "//*[@id='ibplus-loginformMobile']/button[1]")
    public WebElement buttonSubmit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Auth Menu ")
    public L6_PageObject_PagesForIberia.LoginPage openAuthMenu() {
        openAuthMenu.click();
        return this;
    }

    @Step("Input login ")
    public L6_PageObject_PagesForIberia.LoginPage inputLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    @Step("Input password ")
    public L6_PageObject_PagesForIberia.LoginPage inputPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Submit form ")
    public L6_PageObject_PagesForIberia.LoginPage buttonSubmit() {
        buttonSubmit.click();
        return this;
    }
}
