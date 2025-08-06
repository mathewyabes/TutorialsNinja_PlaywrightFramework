package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
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
    private final Locator firstName;
    private final Locator lastName;
    private final Locator email;
    private final Locator phoneNumber;
    private final Locator password;
    private final Locator confirmPassword;
    private final Locator agreeButton;
    private final Locator continueButton;
    private final Locator successMessage;
    private final Locator newsLetterButton;
    private final Locator errorMessage;
    private final Locator firstNameErrorMessage;
    private final Locator lastNameErrorMessage;
    private final Locator emailErrorMessage;
    private final Locator telephoneErrorMessage;
    private final Locator passwordErrorMessage;
    private final Locator passwordMissMatchErrorMessage;

    public RegisterPage(Page page) {
        this.page = page;
        this.firstName = page.locator("#input-firstname");
        this.lastName = page.locator("#input-lastname");
        this.email = page.locator("#input-email");
        this.phoneNumber = page.locator("#input-telephone");
        this.password = page.locator("#input-password");
        this.confirmPassword = page.locator("#input-confirm");
        this.agreeButton = page.locator("[name='agree']");
        this.continueButton = page.locator("//input[@value='Continue']");
        this.successMessage = page.locator("//div[@id='content']//h1[text()='Your Account Has Been Created!']");
        this.newsLetterButton = page.locator("//input[@name='newsletter' and @value='1']");
        this.errorMessage = page.locator("//div[@class='alert alert-danger alert-dismissible']");
        this.firstNameErrorMessage = page.locator("//div[text()='First Name must be between 1 and 32 characters!']");
        this.lastNameErrorMessage = page.locator("//div[text()='Last Name must be between 1 and 32 characters!']");
        this.emailErrorMessage = page.locator("//div[text()='E-Mail Address does not appear to be valid!']");
        this.telephoneErrorMessage = page.locator("//div[text()='Telephone must be between 3 and 32 characters!']");
        this.passwordErrorMessage = page.locator("//div[text()='Password must be between 4 and 20 characters!']");
        this.passwordMissMatchErrorMessage = page.locator("//div[@class='text-danger']");
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
        try (FileInputStream fis = new FileInputStream(propfile)) {
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData.properties");
        try (FileInputStream datafis = new FileInputStream(dataPropFile)) {
            dataProp.load(datafis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() {
        firstName.fill(dataProp.getProperty("firstName"));
        lastName.fill(dataProp.getProperty("lastName"));
        email.fill(Utilities.generateTimeStamp());
        phoneNumber.fill(dataProp.getProperty("telePhone"));
        password.fill(dataProp.getProperty("password"));
        confirmPassword.fill(dataProp.getProperty("confirmPassword"));
        agreeButton.click();
        continueButton.click();
        Assert.assertTrue(successMessage.isVisible());
    }

    public void registerAllField() {
        firstName.fill(dataProp.getProperty("firstName"));
        lastName.fill(dataProp.getProperty("lastName"));
        email.fill(Utilities.generateTimeStamp());
        phoneNumber.fill(dataProp.getProperty("telePhone"));
        password.fill(dataProp.getProperty("password"));
        confirmPassword.fill(dataProp.getProperty("confirmPassword"));
        newsLetterButton.click();
        agreeButton.click();
        continueButton.click();
        Assert.assertTrue(successMessage.isVisible());
    }

    public void withoutFilled() {
        continueButton.click();
        Assert.assertTrue(errorMessage.isVisible());
        Assert.assertTrue(firstNameErrorMessage.isVisible());
        Assert.assertTrue(lastNameErrorMessage.isVisible());
        Assert.assertTrue(emailErrorMessage.isVisible());
        Assert.assertTrue(telephoneErrorMessage.isVisible());
        Assert.assertTrue(passwordErrorMessage.isVisible());
    }

    public void differentPassword() {
        firstName.fill(dataProp.getProperty("firstName"));
        lastName.fill(dataProp.getProperty("lastName"));
        email.fill(Utilities.generateTimeStamp());
        phoneNumber.fill(dataProp.getProperty("telePhone"));
        password.fill(dataProp.getProperty("password"));
        confirmPassword.fill(prop.getProperty("invalidPassword"));
        newsLetterButton.click();
        agreeButton.click();
        continueButton.click();
        Assert.assertTrue(passwordMissMatchErrorMessage.isVisible());
    }

    public void existingAccount() {
        firstName.fill(dataProp.getProperty("firstName"));
        lastName.fill(dataProp.getProperty("lastName"));
        email.fill(prop.getProperty("validUserEmail"));
        phoneNumber.fill(dataProp.getProperty("telePhone"));
        password.fill(dataProp.getProperty("password"));
        confirmPassword.fill(dataProp.getProperty("confirmPassword"));
        newsLetterButton.click();
        agreeButton.click();
        continueButton.click();
        Assert.assertTrue(errorMessage.isVisible());
    }

    public void invalidEmail() {
        firstName.fill(dataProp.getProperty("firstName"));
        lastName.fill(dataProp.getProperty("lastName"));
        email.fill(prop.getProperty("invalidEmail"));
        phoneNumber.fill(dataProp.getProperty("telePhone"));
        password.fill(dataProp.getProperty("password"));
        confirmPassword.fill(dataProp.getProperty("confirmPassword"));
        newsLetterButton.click();
        agreeButton.click();
        continueButton.click();
        Assert.assertTrue(emailErrorMessage.isVisible());
    }

}
