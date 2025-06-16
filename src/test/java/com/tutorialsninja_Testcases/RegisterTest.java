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
import tutorialsninja_Pages.RegisterPage;


public class RegisterTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;
    public RegisterPage registerPage =new RegisterPage(page);;


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
        registerPage = new RegisterPage(page);
        registerPage.register();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyRegisteringAnAccountByProvidingAllTheFields() {
        registerPage = new RegisterPage(page);
        registerPage.registerAllField();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {
        registerPage = new RegisterPage(page);
        registerPage.withoutFilled();
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyRegisterAccountEnteringDifferentPassword() {
        registerPage = new RegisterPage(page);
        registerPage.differentPassword();
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void verifyRegisteringAnAccountExistingAccount() {
        registerPage = new RegisterPage(page);
        registerPage.existingAccount();
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void verifyRegisterAccountByUsingInvalidEmail() {
        registerPage = new RegisterPage(page);
        registerPage.invalidEmail();
        softAssert.assertAll();
    }
}
