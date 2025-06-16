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
        page.locator("[name='agree'']");
        page.locator("//input[@type='submit']");
        Assert.assertTrue(page.locator("#content").isVisible());
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
        page.locator("['name=newsletter']");
        page.locator("[name='agree'']");
        page.locator("//input[@type='submit']");
        Assert.assertTrue(page.locator("#content").isVisible());
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

    public void verifyRegisterAccountEnteringDifferentPassword(){
        
    }
}
