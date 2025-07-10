package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class ShoppingCartPage {

    public Properties prop;
    public Page page;

    public ShoppingCartPage(Page page){
        this.page=page;
    }

    public void shoppingCartPage(){
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        Assert.assertTrue(page.title().toLowerCase().contains("shopping cart"));
    }

    public void navigateShoppingCartPageHeaderOption(){
        page.click("//span[text()='Add to Cart']");
        page.click("//span[text()='Shopping Cart']");
        Assert.assertTrue(page.title().toLowerCase().contains("shopping cart"));
    }

    public void navigateToTheSightMap(){
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        page.click("text=Site Map");
        Assert.assertTrue(page.locator("//h1[text()='Site Map']").isVisible());
    }

    public void emptyCart(){
        page.click("//span[text()='Shopping Cart']");
        Assert.assertTrue(page.locator("//span[text()='Shopping Cart']").isVisible());
    }

    public void navigateToShoppingCartUsingCartBlock(){
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        page.click("#cart-total");
        Assert.assertTrue(page.title().toLowerCase().contains("shopping cart"));
    }

    public void weightOfProduct(){
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        Assert.assertTrue(page.locator("//h1[contains(text(), 'kg')]").isVisible());
    }

    public void invalidCoupon(){
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        page.click("text=Use Coupon Code");
        page.fill("#input-coupon" ,"1234567890");
        page.click("#button-coupon");
        page.locator("//div[contains(@class, 'alert') and contains(., 'Coupon is either invalid')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterCoupon(){
        page.click("//span[text()='Add to Cart']");
        page.click("//a[text()='shopping cart']");
        page.click("text=Use Coupon Code");
        page.click("#button-coupon");
        page.locator("//div[contains(@class, 'alert') and contains(., 'Please enter a coupon code')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


    public void closingTheWarningMessage(){
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

    public void fillAllDetailsInTaxSection(){
        page.click("text=Estimate Shipping & Taxes ");
        page.selectOption("#input-country", "99");
        page.selectOption("#input-zone", "1503");
        page.locator("#input-postcode").fill("627853");
        page.click("#button-quote");
        page.click("//input[@name='shipping_method']");
        page.click("#button-shipping");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterInTaxSection(){
        page.click("text=Estimate Shipping & Taxes ");
        page.dblclick("#button-quote");
//        page.locator("//div[text()='Please select a region / state!']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.locator("//div[text()='Postcode must be between 2 and 10 characters!']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void invalidGift(){
        page.click("text=Use Gift Certificate ");
        page.fill("#input-voucher", "1234567890");
        page.click("#button-voucher");
        page.locator("//i[@class='fa fa-exclamation-circle']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void withoutEnterGift(){
        page.click("text=Use Gift Certificate ");
        page.click("#button-voucher");
        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void continueShopping(){
        page.click("text=Continue Shopping");
        page.locator("//a[text()='Qafox.com']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


    public void removeProductInCart(){
        page.click("//button[@class='btn btn-danger']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void editTheQuantity(){
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "2");
        page.click("//button[@type='submit']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void enterNegativeValue(){
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "-2");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void notAvailableProduct(){
        page.locator("//div[@class='alert alert-danger alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void enterZero(){
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "0");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void numericalValues(){
        page.fill("(//input[@type='text' and @class='form-control'])[1]", "asdf");
        page.click("//button[@type='submit']");
        page.locator("//h1[text()='Shopping Cart']/following-sibling::p").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
}
