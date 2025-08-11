package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class SpecialOfferPage {

    public Properties prop;
    public Page page;
    private Locator specialOfferLink;
    private Locator siteMapLink;
    private Locator sitemapPageVerify;
    private Locator listViewLink;
    private Locator activeButton;
    private Locator gridView;
    private Locator compareTotal;
    private Locator comparisonLink;
    private Locator compareProduct;
    private Locator successNotification;
    private Locator productComparison;
    private Locator productVerify;

    public SpecialOfferPage(Page page){
        this.page=page;
        this.specialOfferLink = page.locator("//h2[text()='Special Offers']");
        this.siteMapLink = page.locator("text=Site Map");
        this.sitemapPageVerify = page.locator("//h1[text()='Site Map']");
        this.listViewLink = page.locator("#list-view");
        this.activeButton = page.locator("button.btn.active");
        this.gridView = page.locator("#grid-view");
        this.compareTotal = page.locator("#compare-total");
        this.comparisonLink = page.locator("//h1[text()='Product Comparison']");
        this.compareProduct = page.locator("(//button[@data-original-title='Compare this Product'])[1]");
        this.successNotification = page.locator("//div[@class='alert alert-success alert-dismissible']");
        this.productComparison = page.locator("text=product comparison");
        this.productVerify = page.locator("//strong[text()='Apple Cinema 30\"']");
    }

    public void verifyNavigateSpecialOffer(){
        specialOfferLink.isVisible();
//        Assert.assertTrue(page.locator("//h2[text()='Special Offers']").isVisible());
    }

    public void verifyNavigateSiteMap(){
        siteMapLink.click();
        sitemapPageVerify.isVisible();
//        page.click("text=Site Map");
//        Assert.assertTrue(page.locator("//h1[text()='Site Map']").isVisible());
    }

    public void verifyListView(){
        listViewLink.click();
//        page.click("#list-view");
        Assert.assertEquals(page.locator("button.btn.active").getAttribute("id"), "list-view");
    }

    public void verifyGridView(){
        gridView.click();
//        page.click("#grid-view");
        Assert.assertEquals(page.locator("button.btn.active").getAttribute("id"), "grid-view");
    }

    public void verifyProductCompare(){
        compareTotal.click();
        comparisonLink.isEnabled();
//        page.click("#compare-total");
//        Assert.assertTrue(page.locator("//h1[text()='Product Comparison']").isEnabled());
    }

    public void verifyAddProduct(){
        compareProduct.click();
        successNotification.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        productComparison.click();
        productVerify.isVisible();
//        page.click("(//button[@data-original-title='Compare this Product'])[1]");
//        page.locator("//div[@class='alert alert-success alert-dismissible']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        page.click("text=product comparison");
//        Assert.assertTrue(page.locator("//strong[text()='Apple Cinema 30\"']").isVisible());
    }

}
