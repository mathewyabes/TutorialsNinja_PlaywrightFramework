package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class WishListPage {
    public Properties prop;
    public Page page;

    public WishListPage(Page page) {
        this.page = page;
    }

    public void addProductInWishlist() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void productDisplayedInWishListPage() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("text=wish list");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void addProductToWishlistUsingSubCategorySection() {
        page.locator("//li[@class='dropdown']/a[@class='dropdown-toggle' and contains(text(),'Desktops')]").hover();
        page.click("text=Show AllDesktops");
        page.click("(//a[contains(text(),'Mac (1)')])[2]");
        page.click("//button[@data-original-title='Add to Wish List']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("text=wish list");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void verifyWishlistPageUsingHeaderOption() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(),'Wish List')]");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void verifyWishlistPageUsingFooterOption() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("text=Wish List");
        Assert.assertTrue(page.locator("text=iMac").isVisible());
    }

    public void removeProductFromWishlist() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("text=Wish List");
        page.click("//a[@data-original-title='Remove']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void addProductIntoCartFromWishlistPage() {
        page.click("//button[@data-original-title='Add to Wish List']");
        page.click("text=Wish List");
        page.click("//button[@data-original-title='Add to Cart']");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


}
