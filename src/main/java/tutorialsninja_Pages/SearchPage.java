package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SearchPage {

    public Properties prop;
    public Page page;
    public Properties dataProp;
    private final Locator searchField;
    private final Locator searchIcon;
    private final Locator correctProduct;
    private final Locator unAvaliableProduct;

    public SearchPage(Page page) {
        this.page = page;
        this.searchField = page.locator("//input[@name='search']");
        this.searchIcon = page.locator("//button[@type='button' and @class='btn btn-default btn-lg']");
        this.correctProduct = page.locator("//div[@class='caption']");
        this.unAvaliableProduct = page.locator("//p[text()='There is no product that matches the search criteria.']");
        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData.properties");
        try (FileInputStream datafis = new FileInputStream(dataPropFile)) {
            dataProp.load(datafis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void existingProduct() {
        searchField.fill(dataProp.getProperty("existingProductName"));
        searchIcon.click();
        Assert.assertTrue(correctProduct.isVisible());
    }

    public void nonExistingProduct() {
        searchField.fill(dataProp.getProperty("nonExistingProductName"));
        searchIcon.click();
        Assert.assertTrue(unAvaliableProduct.isVisible());
    }

    public void emptyField() {
        searchIcon.click();
        Assert.assertTrue(unAvaliableProduct.isVisible());
    }
}
