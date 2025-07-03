package tutorialsninja_Pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.util.Properties;

public class SearchPage {

    public Properties prop;
    public Page page;

    public SearchPage(Page page){
        this.page = page;
    }
    public void existingProduct(){
        page.locator("//input[@name='search']").fill("imac");
        page.locator("//button[@type='button' and @class='btn btn-default btn-lg']").click();
        Assert.assertTrue(page.locator("//div[@class='caption']").isVisible());
    }

    public void nonExistingProduct(){
        page.locator("//input[@name='search']").fill("Fitbit");
        page.locator("//button[@type='button' and @class='btn btn-default btn-lg']").click();
        Assert.assertTrue(page.locator("//p[text()='There is no product that matches the search criteria.']").isVisible());
    }

    public void emptyField(){
        page.locator("//button[@type='button' and @class='btn btn-default btn-lg']").click();
        Assert.assertTrue(page.locator("//p[text()='There is no product that matches the search criteria.']").isVisible());
    }
}
