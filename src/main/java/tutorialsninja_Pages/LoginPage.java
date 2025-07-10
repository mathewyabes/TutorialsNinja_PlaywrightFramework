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

    public LoginPage(Page page) {
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propfile);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.page = page;
        this.userName = page.locator("#input-email");
    }

    public void validLogin() {
        page.fill("#input-email", prop.getProperty("validUserEmail"));
        page.fill("#input-password", prop.getProperty("validPassword"));
        page.click("//input[@value='Login']");
        Assert.assertTrue(page.isVisible("//h2[text()='My Account']"));
    }

    public void invalidLogin() {
        page.locator("#input-email").fill(prop.getProperty("invalidEmail"));
        page.locator("#input-password").fill(prop.getProperty("invalidPassword"));
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    public void invalidEmail() {
        page.locator("#input-email").fill(prop.getProperty("invalidEmail"));
        page.locator("#input-password").fill(prop.getProperty("validPassword"));
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    public void invalidPassword() {
        page.locator("#input-email").fill(prop.getProperty("validUserEmail"));
        page.locator("#input-password").fill(prop.getProperty("invalidPassword"));
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    public void emptyField() {
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }
}
