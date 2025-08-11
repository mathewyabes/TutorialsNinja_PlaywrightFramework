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
    private final Locator addToCartButton;
    private final Locator shoppingCartLink;
    private final Locator headerShoppingCartLink;
    private final Locator siteMapLink;
    private final Locator siteMapVerify;
    private final Locator emptyCart;
    private final Locator totalCart;
    private final Locator couponCodeButton;
    private final Locator couponInput;
    private final Locator buttonCoupon;
    private final Locator invalidCouponErrorMessage;
    private final Locator couponCodeErrorMessage;
    private final Locator closeErrorMessage;
    private final Locator shipping;
    private final Locator countryCode;
    private final Locator zoneCode;
    private final Locator postCode;
    private final Locator quoteButton;
    private final Locator shippingMethodButton;
    private final Locator shippingButton;
    private final Locator successAlertMessage;
    private final Locator postcodeErrorMessage;
    private final Locator stateErrorMessage;
    private final Locator giftLink;
    private final Locator voucherField;
    private final Locator voucherButton;
    private final Locator giftErrorMessage;
    private final Locator errorMessage;
    private final Locator continueShoppingLink;
    private final Locator shoppingPageVerify;
    private final Locator removeButton;
    private final Locator cartEmpty;
    private final Locator editCartField;
    private final Locator submitButton;

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
        this.shipping = page.locator("text=Estimate Shipping & Taxes ");
        this.countryCode = page.locator("#input-country");
        this.zoneCode = page.locator("#input-zone");
        this.postCode = page.locator("#input-postcode");
        this.quoteButton = page.locator("#button-quote");
        this.shippingMethodButton =page.locator("//input[@name='shipping_method']");
        this.shippingButton = page.locator("#button-shipping");
        this.successAlertMessage = page.locator("//div[@class='alert alert-success alert-dismissible']");
        this.postcodeErrorMessage = page.locator("//div[text()='Postcode must be between 2 and 10 characters!']");
        this.stateErrorMessage = page.locator("//div[text()='Please select a region / state!']");
        this.giftLink = page.locator("text=Use Gift Certificate ");
        this.voucherField = page.locator("#input-voucher");
        this.voucherButton = page.locator("#button-voucher");
        this.giftErrorMessage = page.locator("//i[@class='fa fa-exclamation-circle']");
        this.errorMessage = page.locator("//div[@class='alert alert-danger alert-dismissible']");
        this.continueShoppingLink = page.locator("text=Continue Shopping");
        this.shoppingPageVerify = page.locator("//a[text()='Qafox.com']");
        this.removeButton = page.locator("//button[@class='btn btn-danger']");
        this.cartEmpty = page.locator("//h1[text()='Shopping Cart']/following-sibling::p");
        this.editCartField = page.locator("(//input[@type='text' and @class='form-control'])[1]");
        this.submitButton = page.locator("//button[@type='submit']");
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
        shipping.click();
        countryCode.selectOption("99");
        zoneCode.selectOption("1503");
        postCode.fill("627853");
        quoteButton.click();
        shippingMethodButton.click();
        shippingButton.click();
        successAlertMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterInTaxSection() {
        shipping.click();
        quoteButton.dblclick();
        postcodeErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        stateErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void invalidGift() {
        giftLink.click();
        voucherField.fill("1234567890");
        voucherButton.click();
        giftErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("text=Use Gift Certificate ");
//        page.fill("#input-voucher", "1234567890");
//        page.click("#button-voucher");
//        page.locator("//i[@class='fa fa-exclamation-circle']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterGift() {
        giftLink.click();
        voucherButton.click();
        errorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("text=Use Gift Certificate ");
//        page.click("#button-voucher");
//        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void continueShopping() {
        continueShoppingLink.click();
        shoppingPageVerify.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("text=Continue Shopping");
//        page.locator("//a[text()='Qafox.com']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void removeProductInCart() {
        removeButton.click();
        cartEmpty.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("//button[@class='btn btn-danger']");
//        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void editTheQuantity() {
        editCartField.fill("2");
        submitButton.click();
        successAlertMessage.click();
//        page.fill("(//input[@type='text' and @class='form-control'])[1]", "2");
//        page.click("//button[@type='submit']");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void enterNegativeValue() {
        editCartField.fill("-2");
        submitButton.click();
        cartEmpty.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.fill("(//input[@type='text' and @class='form-control'])[1]", "-2");
//        page.click("//button[@type='submit']");
//        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void notAvailableProduct() {
        errorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void enterZero() {
        editCartField.fill("0");
        submitButton.click();
        cartEmpty.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.fill("(//input[@type='text' and @class='form-control'])[1]", "0");
//        page.click("//button[@type='submit']");
//        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void alphabeticCharacters() {
        editCartField.fill("asdf");
        submitButton.click();
        cartEmpty.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.fill("(//input[@type='text' and @class='form-control'])[1]", "asdf");
//        page.click("//button[@type='submit']");
//        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
}
