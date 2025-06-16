package tutorialsninja_Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;
    private final Locator myAccount;
    private final Locator loginOption;
    private final Locator registerOption;

    public HomePage(Page page) {
        this.page = page;
        this.myAccount = page.locator("//span[text()='My Account']");
        this.loginOption = page.locator("text=Login");
        this.registerOption = page.locator("text=Register");
    }

    public void clickMyAccount() {
        myAccount.click();
    }

    public void clickLoginOption() {
        loginOption.click();
    }

    public void clickRegisterOption(){
        registerOption.click();
    }
}
