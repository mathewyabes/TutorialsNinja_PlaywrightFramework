package com.tutorialsninja_Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tutorialsninja_Base.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Pages.LoginPage;
import tutorialsninja_Pages.SearchPage;

@Slf4j
public class SearchTest extends Base {

    public Playwright playwright;
    public Page page;
    public Browser browser;
    public HomePage homePage;
    public LoginPage loginPage;
    public SearchPage searchPage;

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
    public void verifySearchExistingProductName() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifySearchNonExistingProductName() {
        searchPage = new SearchPage(page);
        searchPage.nonExistingProduct();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifySearchWithoutEnteringName() {
        searchPage = new SearchPage(page);
        searchPage.emptyField();
        softAssert.assertAll();
    }
}
