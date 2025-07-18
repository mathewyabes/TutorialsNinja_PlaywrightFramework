package com.tutorialsninja_Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tutorialsninja_Base.Base;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Pages.LoginPage;
import tutorialsninja_Pages.MyAccountInformationPage;

public class MyAccountInformationTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;
    public LoginPage loginPage;
    public MyAccountInformationPage myAccountInformationPage;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(prop.getProperty("url"));
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
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
    public void verifyNavigateToTheMyAccountInformationPage() {
        myAccountInformationPage = new MyAccountInformationPage(page);
        myAccountInformationPage.navigateToTheAccountInformationPage();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyNavigateToTheMyAccountInformationPageUsingRightColumnOption() {
        myAccountInformationPage = new MyAccountInformationPage(page);
        myAccountInformationPage.navigateToTheAccountInformationPageUsingRightColumnOption();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyNavigateToTheMyAccountInformationPageFromSiteMapPage() {
        myAccountInformationPage = new MyAccountInformationPage(page);
        myAccountInformationPage.navigateAccountInformationPageFromSiteInformationPage();
        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void verifyAllTheFieldsAreEmptyFieldsInAccountInformationPage() {
        myAccountInformationPage = new MyAccountInformationPage(page);
        myAccountInformationPage.verifyEmptyFieldsOfAccountInformationPage();
        softAssert.assertAll();
    }

}
