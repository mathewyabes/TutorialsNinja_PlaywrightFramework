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
import tutorialsninja_Pages.SearchPage;
import tutorialsninja_Pages.WishListPage;

public class WishListTest extends Base {

    public Playwright playwright;
    public Page page;
    public Browser browser;
    public HomePage homePage;
    public LoginPage loginPage;
    public SearchPage searchPage;
    public WishListPage wishListPage;


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
        wishListPage = new WishListPage(page);
        wishListPage.addProductInWishlist();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyProductDisplayedInWishListPage() {
        wishListPage = new WishListPage(page);
        wishListPage.productDisplayedInWishListPage();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyAddProductIntoWishListUsingSubCategorySection() {
        wishListPage = new WishListPage(page);
        wishListPage.addProductToWishlistUsingSubCategorySection();
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyProductDisplayedInWishListPageUsingHeaderOption() {
        wishListPage = new WishListPage(page);
        wishListPage.verifyWishlistPageUsingHeaderOption();
        softAssert.assertAll();
    }


    @Test(priority = 5)
    public void verifyProductDisplayedInWishListPageUsingFooterOption() {
        wishListPage = new WishListPage(page);
        wishListPage.verifyWishlistPageUsingFooterOption();
        softAssert.assertAll();
    }


    @Test(priority = 6)
    public void VerifyRemoveProductFromWishList() {
        wishListPage = new WishListPage(page);
        wishListPage.removeProductFromWishlist();
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void verifyAddProductIntoCartFromWishListPage() {
        wishListPage = new WishListPage(page);
        wishListPage.addProductIntoCartFromWishlistPage();
        softAssert.assertAll();
    }


}
