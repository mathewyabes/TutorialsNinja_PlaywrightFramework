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
import tutorialsninja_Utils.Utilities;


public class Login extends Base {
    public Playwright playwright;
    public Browser browser;
    public Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(prop.getProperty("url"));
        page.locator("//span[text()='My Account']").click();
        page.locator("text=Login").click();
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() {
        page.locator("#input-email").fill(prop.getProperty("validUserEmail"));
        page.locator("#input-password").fill(prop.getProperty("validPassword"));
        page.locator("//input[@value='Login']").click();
        if (page.locator("//h2[text()='My Account']").isVisible()) {
            System.out.println("User successfully login in to the application");
        } else {
            System.out.println("User Failed to login to the application ");
        }
    }

    @Test(priority = 2)
    public void loginWithInvalidCredentials() {
        page.locator("#input-email").fill(Utilities.generateTimeStamp());
        page.locator("#input-password").fill(prop.getProperty("invalidPassword"));
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAddressAndValidPassword() {
        page.locator("#input-email").fill(Utilities.generateTimeStamp());
        page.locator("#input-password").fill(prop.getProperty("validPassword"));
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAddressAndInvalidPassword() {
        page.locator("#input-email").fill(prop.getProperty("validUserEmail"));
        page.locator("#input-password").fill(prop.getProperty("invalidPassword"));
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());
    }

    @Test(priority = 5)
    public void verifyLoginWithoutEnteringTheCredentials() {
        page.locator("//input[@value='Login']").click();
        Assert.assertTrue(page.locator("//div[@class='alert alert-danger alert-dismissible']").isVisible());

    }

}
