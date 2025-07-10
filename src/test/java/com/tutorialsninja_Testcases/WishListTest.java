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
import tutorialsninja_Pages.SearchPage;

public class WishListTest extends Base {

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
        page.navigate(prop.getProperty("url"));
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        searchPage = new SearchPage(page);
        homePage.clickMyAccount();
        homePage.clickLoginOption();
        loginPage.validLogin();
        searchPage.existingProduct();
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        playwright.close();
    }

    @Test(priority = 1)
    public void verifyAddProductIntoWishListPage() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyProductDisplayedInWishListPage() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("text=wish list");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyAddProductIntoWishListUsingSubCategorySection() {
        page.locator("//li[@class='dropdown']/a[@class='dropdown-toggle' and contains(text(),'Desktops')]").hover();
        page.click("text=Show AllDesktops");
        page.click("(//a[contains(text(),'Mac (1)')])[2]");
        page.click("//button[@data-original-title='Add to Wish List']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("text=wish list");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyProductDisplayedInWishListPageUsingHeaderOption() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(),'Wish List')]");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
        softAssert.assertAll();
    }


    @Test(priority = 5)
    public void verifyProductDisplayedInWishListPageUsingFooterOption() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("text=Wish List");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
        softAssert.assertAll();
    }


    @Test(priority = 6)
    public void VerifyRemoveProductFromWishList() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("text=Wish List");
        page.click("//a[@data-original-title='Remove']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void verifyAddProductIntoCartFromWishListPage() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("text=Wish List");
        page.click("//button[@data-original-title='Add to Cart']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        softAssert.assertAll();
    }


}
