package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ShoppingCartPage {

    public Properties prop;
    public Properties dataProp;
    public Page page;
    private Locator addToCartButton;
    private Locator shoppingCartLink;
    private Locator headerShoppingCartLink;
    private Locator siteMapLink;
    private Locator siteMapVerify;
    private Locator emptyCart;
    private Locator totalCart;
    private Locator couponCodeButton;
    private Locator couponInput;
    private Locator buttonCoupon;
    private Locator invalidCouponErrorMessage;
    private Locator couponCodeErrorMessage;
    private Locator closeErrorMessage;
    private Locator shipping;

    public ShoppingCartPage(Page page) {
        this.page = page;
        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData.properties");
        try (FileInputStream datafis = new FileInputStream(dataPropFile)) {
            dataProp.load(datafis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.addToCartButton = page.locator("//span[text()='Add to Cart']");
        this.shoppingCartLink = page.locator("//a[text()='shopping cart']");
        this.headerShoppingCartLink = page.locator("//span[text()='Shopping Cart']");
        this.siteMapLink = page.locator("text=Site Map");
        this.siteMapVerify = page.locator("//h1[text()='Site Map']");
        this.emptyCart = page.locator("//h1[text()='Shopping Cart']");
        this.totalCart = page.locator("#cart-total");
        this.couponCodeButton = page.locator("text=Use Coupon Code");
        this.couponInput = page.locator("#input-coupon");
        this.buttonCoupon = page.locator("#button-coupon");
        this.invalidCouponErrorMessage = page.locator("//div[contains(@class, 'alert') and contains(., 'Coupon is either invalid')]");
        this.couponCodeErrorMessage = page.locator("//div[contains(@class, 'alert') and contains(., 'Please enter a coupon code')]");
        this.closeErrorMessage = page.locator("//button[@type='button' and @class='close']");
    }

    public void shoppingCartPage() {
        addToCartButton.click();
        shoppingCartLink.click();
        Assert.assertTrue(page.title().toLowerCase().contains("shopping cart"));
    }

    public void navigateShoppingCartPageHeaderOption() {
        addToCartButton.click();
        headerShoppingCartLink.click();
        Assert.assertTrue(page.title().toLowerCase().contains("shopping cart"));
    }

    public void navigateToTheSightMap() {
        addToCartButton.click();
        shoppingCartLink.click();
        siteMapLink.click();
        siteMapVerify.isVisible();
    }

    public void emptyCart() {
        headerShoppingCartLink.click();
        emptyCart.isVisible();
    }

    public void navigateToShoppingCartUsingCartBlock() {
        addToCartButton.click();
        shoppingCartLink.click();
        totalCart.click();
        Assert.assertTrue(page.title().toLowerCase().contains("shopping cart"));
    }

    public void weightOfProduct() {
        addToCartButton.click();
        shoppingCartLink.click();
        Assert.assertTrue(page.locator("//h1[contains(text(), 'kg')]").isVisible());
    }

    public void invalidCoupon() {
        addToCartButton.click();
        shoppingCartLink.click();
        couponCodeButton.click();
        couponInput.fill(dataProp.getProperty("invalidCouponCode"));
        buttonCoupon.click();
        invalidCouponErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterCoupon() {
        addToCartButton.click();
        shoppingCartLink.click();
        couponCodeButton.click();
        buttonCoupon.click();
        couponCodeErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


    public void closingTheWarningMessage() {
        addToCartButton.click();
        shoppingCartLink.click();
        couponCodeButton.click();
        buttonCoupon.click();
        couponCodeErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        closeErrorMessage.click();
        if (couponCodeErrorMessage.isVisible()) {
            System.out.println("The error Message is not closed");
        } else {
            System.out.println("The error Message is closed");
        }
    }

    public void fillAllDetailsInTaxSection() {
        page.click("text=Estimate Shipping & Taxes ");
        page.selectOption("#input-country", "99");
        page.selectOption("#input-zone", "1503");
        page.locator("#input-postcode").fill("627853");
        page.click("#button-quote");
        page.click("//input[@name='shipping_method']");
        page.click("#button-shipping");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterInTaxSection() {
        page.click("text=Estimate Shipping & Taxes ");
        page.dblclick("#button-quote");
//        page.locator("//div[text()='Please select a region / state!']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.locator("//div[text()='Postcode must be between 2 and 10 characters!']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void invalidGift() {
        page.click("text=Use Gift Certificate ");
        page.fill("#input-voucher", "1234567890");
        page.click("#button-voucher");
        page.locator("//i[@class='fa fa-exclamation-circle']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterGift() {
        page.click("text=Use Gift Certificate ");
        page.click("#button-voucher");
        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void continueShopping() {
        page.click("text=Continue Shopping");
        page.locator("//a[text()='Qafox.com']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


    public void removeProductInCart() {
        page.click("//button[@class='btn btn-danger']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void editTheQuantity() {
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "2");
        page.click("//button[@type='submit']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void enterNegativeValue() {
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "-2");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void notAvailableProduct() {
        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void enterZero() {
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "0");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void numericalValues() {
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "asdf");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
}
