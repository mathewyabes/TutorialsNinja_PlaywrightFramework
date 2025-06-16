package tutorialsninja_Pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import tutorialsninja_Utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class RegisterPage {
    public Properties prop;
    public Properties dataProp;
    private final Page page;

    public RegisterPage(Page page) {
        this.page = page;
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propfile);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData.properties");
        try {
            FileInputStream datafis = new FileInputStream(dataPropFile);
            dataProp.load(datafis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() {
        page.locator("#input-firstname").fill(dataProp.getProperty("firstName"));
        page.locator("#input-lastname").fill(dataProp.getProperty("lastName"));
        page.locator("#input-email").fill(Utilities.generateTimeStamp());
        page.locator("#input-telephone").fill(dataProp.getProperty("telePhone"));
        page.locator("#input-password").fill(dataProp.getProperty("password"));
        page.locator("#input-confirm").fill(dataProp.getProperty("confirmPassword"));
        page.locator("[name='agree']").click();
        page.locator("//input[@value='Continue']").click();
        System.out.println("The user successfully Enter their details");
        Assert.assertTrue(page.locator("//div[@id='content']//h1[text()='Your Account Has Been Created!']").isVisible());
        System.out.println("The User Register their Successfully");
    }

    public void registerAllField() {
        page.locator("#input-firstname").fill(dataProp.getProperty("firstName"));
        page.locator("#input-lastname").fill(dataProp.getProperty("lastName"));
        page.locator("#input-email").fill(Utilities.generateTimeStamp());
        page.locator("#input-telephone").fill(dataProp.getProperty("telePhone"));
        page.locator("#input-password").fill(dataProp.getProperty("password"));
        page.locator("#input-confirm").fill(dataProp.getProperty("confirmPassword"));
        page.locator("//input[@name='newsletter' and @value='1']").click();
        page.locator("[name='agree']").click();
        page.locator("//input[@type='submit']").click();
        Assert.assertTrue(page.locator("//div[@id='content']//h1[text()='Your Account Has Been Created!']").isVisible());
        System.out.println("The User Register their Successfully");
    }

    public void withoutFilled() {
        page.locator("//input[@type='submit']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='First Name must be between 1 and 32 characters!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='Last Name must be between 1 and 32 characters!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='E-Mail Address does not appear to be valid!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='Telephone must be between 3 and 32 characters!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='Password must be between 4 and 20 characters!']").isVisible());
    }

    public void differentPassword() {
        page.locator("#input-firstname").fill(dataProp.getProperty("firstName"));
        page.locator("#input-lastname").fill(dataProp.getProperty("lastName"));
        page.locator("#input-email").fill(Utilities.generateTimeStamp());
        page.locator("#input-telephone").fill(dataProp.getProperty("telePhone"));
        page.locator("#input-password").fill(dataProp.getProperty("password"));
        page.locator("#input-confirm").fill(prop.getProperty("invalidPassword"));
        page.locator("['name=newsletter']");
        page.locator("[name='agree'']");
        page.locator("//input[@type='submit']").click();
        Assert.assertTrue(page.locator("//div[@class='text-danger']").isVisible());
    }

    public void existingAccount() {
        page.locator("#input-firstname").fill(dataProp.getProperty("firstName"));
        page.locator("#input-lastname").fill(dataProp.getProperty("lastName"));
        page.locator("#input-email").fill(prop.getProperty("validUserEmail"));
        page.locator("#input-telephone").fill(dataProp.getProperty("telePhone"));
        page.locator("#input-password").fill(dataProp.getProperty("password"));
        page.locator("#input-confirm").fill(dataProp.getProperty("confirmPassword"));
        page.locator("//input[@name='newsletter' and @value='1']").click();
        page.locator("[name='agree']").click();
        page.locator("//input[@type='submit']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    public void invalidEmail() {
        page.locator("#input-firstname").fill(dataProp.getProperty("firstName"));
        page.locator("#input-lastname").fill(dataProp.getProperty("lastName"));
        page.locator("#input-email").fill(prop.getProperty("invalidEmail"));
        page.locator("#input-telephone").fill(dataProp.getProperty("telePhone"));
        page.locator("#input-password").fill(dataProp.getProperty("password"));
        page.locator("#input-confirm").fill(dataProp.getProperty("confirmPassword"));
        page.locator("//input[@name='newsletter' and @value='1']").click();
        page.locator("[name='agree']").click();
        page.locator("//input[@type='submit']").click();
        Assert.assertTrue(page.locator("//div[text()='E-Mail Address does not appear to be valid!']").isVisible());
    }

}
