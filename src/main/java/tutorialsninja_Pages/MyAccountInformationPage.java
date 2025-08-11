package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.util.Properties;

public class MyAccountInformationPage {

    public Properties prop;
    public Page page;
    private final Locator myAccountSection;
    private final Locator myAccountLink;
    private final Locator editAccountInformationLink;
    private final Locator accountInformationPageVerify;
    private final Locator editAccountLink;
    private final Locator siteMapLink;
    private final Locator accountInformationLink;
    private final Locator firstNameTextField;
    private final Locator lastNameTextField;
    private final Locator emailTextField;
    private final Locator telephoneTextField;
    private final Locator submitButton;
    private final Locator firstNameErrorMessage;
    private final Locator lastNameErrorMessage;
    private final Locator emailErrorMessage;
    private final Locator telephoneErrorMessage;

    public MyAccountInformationPage(Page page) {
        this.page = page;
        this.myAccountSection = page.locator("//span[text()='My Account']");
        this.myAccountLink = page.locator("(//a[text() = 'My Account'])[1]");
        this.editAccountInformationLink = page.locator("text=Edit your account information");
        this.accountInformationPageVerify = page.locator("//h1[text()='My Account Information']");
        this.editAccountLink = page.locator("text=Edit Account");
        this.siteMapLink = page.locator("text=Site Map");
        this.accountInformationLink = page.locator("text=Account Information");
        this.firstNameTextField = page.locator("#input-firstname");
        this.lastNameTextField = page.locator("#input-lastname");
        this.emailTextField = page.locator("#input-email");
        this.telephoneTextField = page.locator("#input-telephone");
        this.submitButton = page.locator("//input[@type='submit']");
        this.firstNameErrorMessage = page.locator("text=First Name must be between 1 and 32 characters!");
        this.lastNameErrorMessage = page.locator("text=Last Name must be between 1 and 32 characters!");
        this.emailErrorMessage = page.locator("text=E-Mail Address does not appear to be valid!");
        this.telephoneErrorMessage = page.locator("text=Telephone must be between 3 and 32 characters!");
    }

    public void navigateToTheAccountInformationPage(){
        myAccountSection.click();
        myAccountLink.click();
        editAccountInformationLink.click();
        accountInformationPageVerify.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void navigateToTheAccountInformationPageUsingRightColumnOption(){
        myAccountSection.click();
        myAccountLink.click();
        editAccountLink.click();
        accountInformationPageVerify.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void navigateAccountInformationPageFromSiteInformationPage(){
        siteMapLink.click();
        accountInformationLink.click();
        accountInformationPageVerify.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyEmptyFieldsOfAccountInformationPage(){
        myAccountSection.click();
        myAccountLink.click();
        editAccountInformationLink.click();
        firstNameTextField.fill("");
        lastNameTextField.fill("");
        emailTextField.fill("");
        telephoneTextField.fill("");
        submitButton.click();
        firstNameErrorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        lastNameErrorMessage.isVisible();
        emailErrorMessage.isVisible();
        telephoneErrorMessage.isVisible();
    }
}
