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




}
