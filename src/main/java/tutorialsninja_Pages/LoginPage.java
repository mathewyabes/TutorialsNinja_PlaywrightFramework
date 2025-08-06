package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import tutorialsninja_Utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class LoginPage {

    public Properties prop;
    private final Page page;
    private final Locator userName;
    private final Locator password;
    private final Locator loginButton;
    private final Locator successHomePage;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");

        try (FileInputStream fis = new FileInputStream(propfile)){
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.page = page;
        this.userName = page.locator("#input-email");
        this.password = page.locator("#input-password");
        this.loginButton = page.locator("//input[@value='Login']");
        this.successHomePage = page.locator("//h2[text()='My Account']");
        this.errorMessage = page.locator("//div[@class='alert alert-danger alert-dismissible']");
    }

    public void validLogin() {
        userName.fill(prop.getProperty("validUserEmail"));
        password.fill(prop.getProperty("validPassword"));
        loginButton.click();
        Assert.assertTrue(successHomePage.isVisible());
    }

    public void invalidLogin() {
        userName.fill(prop.getProperty("invalidEmail"));
        password.fill(prop.getProperty("invalidPassword"));
        loginButton.click();
        Assert.assertTrue(errorMessage.isVisible());
    }

    public void invalidEmail() {
        userName.fill(prop.getProperty("invalidEmail"));
        password.fill(prop.getProperty("validPassword"));
        loginButton.click();
        Assert.assertTrue(errorMessage.isVisible());
    }

    public void invalidPassword() {
        userName.fill(prop.getProperty("validUserEmail"));
        password.fill(prop.getProperty("invalidPassword"));
        loginButton.click();
        Assert.assertTrue(errorMessage.isVisible());
    }

    public void emptyField() {
        loginButton.click();
        Assert.assertTrue(errorMessage.isVisible());
    }
}
