package com.tutorialsninja_Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tutorialsninja_Base.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Pages.LoginPage;


@Slf4j
public class LoginTest extends Base {
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
        page.navigate(prop.getProperty("url"));
        homePage = new HomePage(page);
        homePage.clickMyAccount();
        homePage.clickLoginOption();
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() {
        loginPage = new LoginPage(page);
        loginPage.validLogin();
    }

    @Test(priority = 2)
    public void loginWithInvalidCredentials() {
        loginPage = new LoginPage(page);
        loginPage.invalidLogin();
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAddressAndValidPassword() {
        loginPage = new LoginPage(page);
        loginPage.invalidEmail();
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAddressAndInvalidPassword() {
        loginPage = new LoginPage(page);
        loginPage.invalidPassword();
    }

    @Test(priority = 5)
    public void verifyLoginWithoutEnteringTheCredentials() {
       loginPage = new LoginPage(page);
       loginPage.emptyField();
    }

}
