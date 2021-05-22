package L6_PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this );
    }

    @FindBy(css = ".ibe-header-new__link:nth-child(2)")
    public WebElement openAuthMenu;

    @FindBy(id = "userMenuMobile")
    public WebElement inputLogin;

    @FindBy(id = "pinMenuMobile")
    public WebElement inputPassword;

    @FindBy(xpath = "//*[@id='ibplus-loginformMobile']/button[1]")
    public WebElement buttonSubmit;
}
