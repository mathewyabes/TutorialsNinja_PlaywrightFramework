package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class SpecialOfferPage {

    public Properties prop;
    public Page page;

    public SpecialOfferPage(Page page){
        this.page=page;
    }

    public void verifyNavigateSpecialOffer(){
        Assert.assertTrue(page.locator("//h2[text()='Special Offers']").isVisible());
    }

    public void verifyNavigateSiteMap(){
        page.click("text=Site Map");
        Assert.assertTrue(page.locator("//h1[text()='Site Map']").isVisible());
    }

    public void verifyListView(){
        page.click("#list-view");
        Assert.assertEquals(page.locator("button.btn.active").getAttribute("id"), "list-view");
    }

    public void verifyGridView(){
        page.click("#grid-view");
        Assert.assertEquals(page.locator("button.btn.active").getAttribute("id"), "grid-view");
    }

    public void verifyProductCompare(){
        page.click("#compare-total");
        Assert.assertTrue(page.locator("//h1[text()='Product Comparison']").isEnabled());
    }

    public void verifyAddProduct(){
        page.click("(//button[@data-original-title='Compare this Product'])[1]");
        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("text=product comparison");
        Assert.assertTrue(page.locator("//strong[text()='Apple Cinema 30\"']").isVisible());
    }

}
