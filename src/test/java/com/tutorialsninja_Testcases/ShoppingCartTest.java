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
    }


    @Test(priority = 2)
    public void verifyNavigateShoppingCartPageUsingHeaderOption() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.navigateShoppingCartPageHeaderOption();
    }

    @Test(priority = 3)
    public void verifyNavigateToTheSiteMapPageFromShoppingPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.navigateToTheSightMap();
    }

    @Test(priority = 4)
    public void verifyTheCartWhenThereIsNoProductAdded() {
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.emptyCart();
    }

    @Test(priority = 5)
    public void verifyNavigateToTheShoppingCartPageUsingCartBlock() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.navigateToShoppingCartUsingCartBlock();
    }

    @Test(priority = 6)
    public void verifyWeightOfTheProductInShoppingCart() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.weightOfProduct();
    }

    @Test(priority = 7)
    public void verifyCouponCodeProvidingInvalidCoupon() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.invalidCoupon();
    }

    @Test(priority = 8)
    public void verifyThePageWithoutEnterCouponCode() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        page.click("text=Use Coupon Code");
        page.click("#button-coupon");
        page.locator("//div[contains(@class, 'alert') and contains(., 'Please enter a coupon code')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 9)
    public void verifyClosingTheWarningMessageInTheShoppingCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        page.click("text=Use Coupon Code");
        page.click("#button-coupon");
        page.locator("//div[contains(@class, 'alert') and contains(., 'Please enter a coupon code')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("//button[@type='button' and @class='close']");
        if (page.locator("//div[contains(@class, 'alert') and contains(., 'Please enter a coupon code')]").isVisible()) {
            System.out.println("The error Message is not closed");
        } else {
            System.out.println("The error Message is closed");
        }
    }

    @Test(priority = 10)
    public void verifyFillAllTheDetailsInEstimateAndTaxes() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.click("text=Estimate Shipping & Taxes ");
        page.selectOption("#input-country", "99");
        ;
        page.selectOption("#input-zone", "1503");
        page.locator("#input-postcode").fill("627853");
        page.click("#button-quote");
        page.click("//input[@name='shipping_method']");
        page.click("#button-shipping");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 11)
    public void verifyWithoutEnteringTheDetailsInTheTaxes() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.click("text=Estimate Shipping & Taxes ");
        page.dblclick("#button-quote");
//        page.locator("//div[text()='Please select a region / state!']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.locator("//div[text()='Postcode must be between 2 and 10 characters!']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 12)
    public void verifyEnteringInvalidGiftCertificateInTheShoppingCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.click("text=Use Gift Certificate ");
        page.fill("#input-voucher", "1234567890");
        page.click("#button-voucher");
        page.locator("//i[@class='fa fa-exclamation-circle']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 13)
    public void verifyWithoutEnteringTheGiftCode() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.click("text=Use Gift Certificate ");
        page.click("#button-voucher");
        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 14)
    public void verifyTheContinueShoppingLink() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.click("text=Continue Shopping");
        page.locator("//a[text()='Qafox.com']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 15)
    public void verifyRemoveTheProductCartPage() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.click("//button[@class='btn btn-danger']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 16)
    public void verifyEditTheQuantity() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "2");
        page.click("//button[@type='submit']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 17)
    public void verifyEnterNegativeValesAndClickUpdateButton() {
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "-2");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 18)
    public void verifyNotAvailableProductNotification(){
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 19)
    public void verifyEnterZeroAndClickUpdateButton(){
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "0");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Test(priority = 20)
    public void verifyEnterNumericalValuesAndClickUpdateButton(){
        searchPage = new SearchPage(page);
        searchPage.existingProduct();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.shoppingCartPage();
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "asdf");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

}
