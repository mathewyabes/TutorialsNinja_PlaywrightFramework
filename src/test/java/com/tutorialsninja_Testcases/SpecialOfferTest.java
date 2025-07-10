package com.tutorialsninja_Testcases;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.tutorialsninja_Base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Pages.SpecialOfferPage;

public class SpecialOfferTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;
    public SpecialOfferPage specialOfferPage;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(prop.getProperty("url"));
        page.click("text=Specials");
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void navigateToTheSpecialOfferPage() {
        specialOfferPage = new SpecialOfferPage(page);
        specialOfferPage.verifyNavigateSpecialOffer();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void navigateToTheSiteMapPage() {
        specialOfferPage = new SpecialOfferPage(page);
        specialOfferPage.verifyNavigateSiteMap();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void VerifyTheProductDisplayedInListView() {
        specialOfferPage = new SpecialOfferPage(page);
        specialOfferPage.verifyListView();
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void VerifyTheProductDisplayedInGridView() {
        specialOfferPage = new SpecialOfferPage(page);
        specialOfferPage.verifyGridView();
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void VerifyProductComparePage() {
        specialOfferPage = new SpecialOfferPage(page);
        specialOfferPage.verifyProductCompare();
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void verifyAddProductForComparison() {
        specialOfferPage = new SpecialOfferPage(page);
        specialOfferPage.verifyAddProduct();
        softAssert.assertAll();
    }


}
