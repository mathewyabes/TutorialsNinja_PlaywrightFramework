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
import tutorialsninja_Pages.LogoutPage;

public class LogoutTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;
    public LoginPage loginPage;
    public LogoutPage logoutPage;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        page.navigate(prop.getProperty("url"));
        homePage.clickMyAccount();
        homePage.clickLoginOption();
        loginPage.validLogin();
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void verifyLogoutUsingDropdown() {
        logoutPage = new LogoutPage(page);
        logoutPage.logoutUsingDropdown();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyLogoutUsingRightColumnOptions() {
        logoutPage = new LogoutPage(page);
        logoutPage.logoutUsingRightColumnOption();
        softAssert.assertAll();
    }

}
