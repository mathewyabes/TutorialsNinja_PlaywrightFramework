package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class WishListPage {
    public Properties prop;
    public Page page;
    private final Locator addWishListButton;
    private final Locator successAlertMessage;
    private final Locator wishListLink;
    private final Locator productVerify;
    private final Locator dropdownLink;
    private final Locator showProduct;
    private final Locator product;
    private final Locator headerWishListButton;
    private final Locator removeProduct;
    private final Locator addToCart;

    public WishListPage(Page page) {
        this.page = page;
        this.addWishListButton = page.locator("//button[@data-original-title='Add to Wish List']");
        this.successAlertMessage = page.locator("//div[@class='alert alert-success alert-dismissible']");
        this.wishListLink = page.locator("//a[text()='wish list']");
        this.productVerify = page.locator("(//a[text()='iMac'])[1]");
        this.dropdownLink = page.locator("//li[@class='dropdown']/a[@class='dropdown-toggle' and contains(text(),'Desktops')]");
        this.showProduct = page.locator("text=Show AllDesktops");
        this.product = page.locator("(//a[contains(text(),'Mac (1)')])[2]");
        this.headerWishListButton = page.locator("//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(),'Wish List')]");
        this.removeProduct = page.locator("(//a[@data-original-title='Remove'])[1]");
        this.addToCart = page.locator("(//button[@data-original-title='Add to Cart'])[1]");
    }

    public void addProductInWishlist() {
        addWishListButton.click();
        successAlertMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void productDisplayedInWishListPage() {
        addWishListButton.click();
        successAlertMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        wishListLink.click();
        productVerify.isVisible();
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("text=wish list");
//        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void addProductToWishlistUsingSubCategorySection() {
        dropdownLink.hover();
        showProduct.click();
        product.click();
        addWishListButton.click();
        successAlertMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        wishListLink.click();
        productVerify.isVisible();
//        page.locator("//li[@class='dropdown']/a[@class='dropdown-toggle' and contains(text(),'Desktops')]").hover();
//        page.click("text=Show AllDesktops");
//        page.click("(//a[contains(text(),'Mac (1)')])[2]");
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("text=wish list");
//        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void verifyWishlistPageUsingHeaderOption() {
        addWishListButton.click();
        headerWishListButton.click();
        productVerify.isVisible();
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.click("//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(),'Wish List')]");
//        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void verifyWishlistPageUsingFooterOption() {
        addWishListButton.click();
        wishListLink.click();
        productVerify.isVisible();
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.click("text=Wish List");
//        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void removeProductFromWishlist() {
        addWishListButton.click();
        wishListLink.click();
        removeProduct.click();
        successAlertMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.click("text=Wish List");
//        page.click("//a[@data-original-title='Remove']");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void addProductIntoCartFromWishlistPage() {
        addWishListButton.click();
        wishListLink.click();
        addToCart.click();
        successAlertMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("//button[@data-original-title='Add to Wish List']");
//        page.click("text=Wish List");
//        page.click("//button[@data-original-title='Add to Cart']");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


}
