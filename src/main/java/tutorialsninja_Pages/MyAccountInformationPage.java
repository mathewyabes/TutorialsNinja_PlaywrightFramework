package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class MyAccountInformationPage {

    public Properties prop;
    public Page page;

    public MyAccountInformationPage(Page page) {
        this.page = page;
    }

    public void navigateToTheAccountInformationPage(){
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit your account information");
        page.locator("//h1[text()='My Account Information']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void navigateToTheAccountInformationPageUsingRightColumnOption(){
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit Account");
        page.locator("//h1[text()='My Account Information']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void navigateAccountInformationPageFromSiteInformationPage(){
        page.click("text=Site Map");
        page.click("text=Account Information");
        page.locator("//h1[text()='My Account Information']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyEmptyFieldsOfAccountInformationPage(){
        page.click(" //span[text()='My Account']");
        page.click("text=My Account");
        page.click("text=Edit your account information");
        page.fill("#input-firstname","");
        page.fill("#input-lastname","");
        page.fill("#input-email","");
        page.fill("#input-telephone","");
        page.click("//input[@type='submit']");
        page.locator("text=First Name must be between 1 and 32 characters!").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Assert.assertTrue(page.locator("text=Last Name must be between 1 and 32 characters!").isVisible());
        Assert.assertTrue(page.locator("text=E-Mail Address does not appear to be valid!").isVisible());
        Assert.assertTrue(page.locator("text=Telephone must be between 3 and 32 characters!").isVisible());
    }
}
