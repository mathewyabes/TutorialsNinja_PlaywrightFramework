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
import tutorialsninja_Pages.LoginPage;

public class LogoutTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;
    public LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        homePage = new HomePage(page);
        page.navigate(prop.getProperty("url"));
        homePage.clickMyAccount();
        homePage.clickLoginOption();
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void verifyLogoutUsingDropdown() {
        page.locator("#input-email").fill(prop.getProperty("validUserEmail"));
        page.locator("#input-password").fill(prop.getProperty("validPassword"));
        page.locator("//input[@value='Login']").click();
        page.locator("//span[text()='My Account']").click();
        page.locator("(//a[text()='Logout'])[1]").click();
        Assert.assertTrue(page.url().contains("logout"));
        page.locator("text='Continue'").click();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyLogoutUsingRightColoumnOptions() {
        page.locator("#input-email").fill(prop.getProperty("validUserEmail"));
        page.locator("#input-password").fill(prop.getProperty("validPassword"));
        page.locator("//input[@value='Login']").click();
        page.locator("//span[text()='My Account']").click();
        page.locator("(//a[text()='Logout'])[2]").click();
        Assert.assertTrue(page.url().contains("logout"));
        page.locator("text='Continue'").click();
        softAssert.assertAll();
    }

}
