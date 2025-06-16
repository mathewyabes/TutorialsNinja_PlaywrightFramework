package com.tutorialsninja_Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tutorialsninja_Base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Utils.Utilities;

public class RegisterTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;


    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        homePage = new HomePage(page);
        page.navigate(prop.getProperty("url"));
        homePage.clickMyAccount();
        homePage.clickRegisterOption();
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void verifyRegisterAccountWithMandatoryFields() {
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

    @Test(priority = 2)
    public void verifyRegisteringAnAccountByProvidingAllTheFields() {
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

    @Test(priority = 3)
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {
        page.locator("//input[@type='submit']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='First Name must be between 1 and 32 characters!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='Last Name must be between 1 and 32 characters!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='E-Mail Address does not appear to be valid!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='Telephone must be between 3 and 32 characters!']").isVisible());
        Assert.assertTrue(page.locator("//div[text()='Password must be between 4 and 20 characters!']").isVisible());
    }

    @Test(priority = 4)
    public void verifyRegisterAccountEnteringDifferentPassword() {
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

    @Test(priority = 5)
    public void verifyRegisteringAnAccountExistingAccount() {
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

    @Test(priority = 6)
    public void verifyRegisterAccountByUsingInvalidEmail() {
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
