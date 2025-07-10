package com.tutorialsninja_Testcases;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.tutorialsninja_Base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Pages.LoginPage;

public class MyAccountInformationTest extends Base {

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
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit your account information");
        page.locator("//h1[text()='My Account Information']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyNavigateToTheMyAccountInformationPageUsingRightColumnOption() {
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit Account");
        page.locator("//h1[text()='My Account Information']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyNavigateToTheMyAccountInformationPageFromSiteMapPage(){
        page.click("text=Site Map");
        page.click("text=Account Information");
        page.locator("//h1[text()='My Account Information']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void verifyAllTheFieldsAreEmptyFieldsInAccountInformationPage(){
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit your account information");
        page.fill("#input-firstname","");
        page.fill("#input-lastname","");
        page.fill("#input-email","");
        page.fill("#input-telephone","");
        page.click("//input[@type='submit']");
        page.locator("text=First Name must be between 1 and 32 characters!").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Assert.assertTrue(page.locator("text=Last Name must be between 1 and 32 characters!").isVisible());
        Assert.assertTrue(page.locator("text=E-Mail Address does not appear to be valid!").isVisible());
        Assert.assertTrue(page.locator("text=Telephone must be between 3 and 32 characters!").isVisible());
        softAssert.assertAll();
    }


    @Test(priority = 5)
    public void verifyTheFiledInAccountInformationPageMarkedAsMandatory(){
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit your account information");
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        Assert.assertTrue(page.locator("//label[@for='input-firstname']").innerText().contains("*"));
        softAssert.assertAll();
    }



}
