package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LogoutPage {

    public Properties prop;
    public Page page;
    public Locator emailTextField;
//    public Locator passwordTextField;
//    public Locator loginButton;
    public Locator myAccountDropdown;
    public Locator logoutLink;
    public Locator continueButton;
    public Locator rightColumnLogoutLink;

    public LogoutPage(Page page){
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
        try {
            FileInputStream fis = new FileInputStream(propfile);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.page = page;
        this.emailTextField = page.locator("#input-email");
//        this.passwordTextField = page.locator("#input-password");
//        this.loginButton = page.locator("//input[@value='Login']");
        this.myAccountDropdown = page.locator("//span[text()='My Account']");
        this.logoutLink = page.locator("(//a[text()='Logout'])[1]");
        this.continueButton = page.locator("text='Continue'");
        this.rightColumnLogoutLink = page.locator("(//a[text()='Logout'])[2]");
    }

    public void logoutUsingDropdown(){
        myAccountDropdown.click();
        logoutLink.click();
        Assert.assertTrue(page.url().contains("logout"));
        continueButton.click();
    }

    public void logoutUsingRightColumnOption(){
        rightColumnLogoutLink.click();
        Assert.assertTrue(page.url().contains("logout"));
        continueButton.click();
    }
}
