package com.tutorialsninja_Testcases;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.tutorialsninja_Base.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tutorialsninja_Pages.HomePage;
import tutorialsninja_Pages.LoginPage;
import tutorialsninja_Pages.SearchPage;
import tutorialsninja_Pages.ShoppingCartPage;

@Slf4j
public class ShoppingCartTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public HomePage homePage;
    public LoginPage loginPage;
    public SearchPage searchPage;
    public ShoppingCartPage shoppingCartPage;

    @BeforeMethod
    public void setUp() {
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
    public void verifyNavigateToTheShoppingCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void verifyNavigateShoppingCartPageUsingHeaderOption() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.navigateShoppingCartPageHeaderOption();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyNavigateToTheSiteMapPageFromShoppingPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.navigateToTheSightMap();
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyTheCartWhenThereIsNoProductAdded() {
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.emptyCart();
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void verifyNavigateToTheShoppingCartPageUsingCartBlock() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.navigateToShoppingCartUsingCartBlock();
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void verifyWeightOfTheProductInShoppingCart() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.weightOfProduct();
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void verifyCouponCodeProvidingInvalidCoupon() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.invalidCoupon();
        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void verifyThePageWithoutEnterCouponCode() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.withoutEnterCoupon();
        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void verifyClosingTheWarningMessageInTheShoppingCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.closingTheWarningMessage();
        softAssert.assertAll();
    }

    @Test(priority = 10)
    public void verifyFillAllTheDetailsInEstimateAndTaxes() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.fillAllDetailsInTaxSection();
        softAssert.assertAll();
    }

    @Test(priority = 11)
    public void verifyWithoutEnteringTheDetailsInTheTaxes() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.withoutEnterInTaxSection();
        softAssert.assertAll();
    }

    @Test(priority = 12)
    public void verifyEnteringInvalidGiftCertificateInTheShoppingCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.invalidGift();
        softAssert.assertAll();
    }

    @Test(priority = 13)
    public void verifyWithoutEnteringTheGiftCode() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.withoutEnterGift();
        softAssert.assertAll();
    }

    @Test(priority = 14)
    public void verifyTheContinueShoppingLink() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.continueShopping();
        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void verifyRemoveTheProductCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.removeProductInCart();
        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void verifyEditTheQuantity() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.editTheQuantity();
        softAssert.assertAll();
    }

    @Test(priority = 17)
    public void verifyEnterNegativeValesAndClickUpdateButton() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.enterNegativeValue();
        softAssert.assertAll();
    }

    @Test(priority = 18)
    public void verifyNotAvailableProductNotification() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.notAvailableProduct();
        softAssert.assertAll();
    }

    @Test(priority = 19)
    public void verifyEnterZeroAndClickUpdateButton() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.enterZero();
        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void verifyEnterNumericalValuesAndClickUpdateButton() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        shoppingCartPage.numericalValues();
        softAssert.assertAll();
    }

}
