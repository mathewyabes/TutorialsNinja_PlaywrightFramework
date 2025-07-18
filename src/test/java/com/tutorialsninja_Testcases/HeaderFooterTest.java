package com.tutorialsninja_Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tutorialsninja_Base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderFooterTest extends Base {

    public Playwright playwright;
    public Browser browser;
    public Page page;

    @BeforeMethod
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(){
        page.close();
        browser.close();
    }

    @Test(priority = 1)
    public void verifyPhoneNumberIsDisplayedInTheHeaderOption(){
        Assert.assertTrue(page.locator("(//span[@class='hidden-xs hidden-sm hidden-md'])[2]").isVisible());
    }

    @Test(priority = 2)
    public void verifyCurrencyIsDisplayedInTheHeaderOption(){
        Assert.assertTrue(page.locator("(//span[@class='hidden-xs hidden-sm hidden-md'])[1]").isVisible());
    }

    @Test(priority = 3)
    public void verifyAboutUsLinkInTheFooterOption(){
        Assert.assertTrue(page.locator("text=About Us").isVisible());
    }

    @Test(priority = 4)
    public void verifyDeliveryInformationLinkInTheFooterOption(){
        Assert.assertTrue(page.locator("text=Delivery Information").isVisible());
    }

    @Test(priority = 5)
    public void verifyPrivacyPolicyLinkInTheFooterOption(){
        Assert.assertTrue(page.locator("text=Privacy Policy").isVisible());
    }

    @Test(priority = 6)
    public void verifyTermsAndConditionsLinkInTheFooterOption(){
        Assert.assertTrue(page.locator("text=Terms & Conditions").isVisible());
    }

    @Test(priority = 7)
    public void verifyBrandsLinkInTheFooterOption(){
        Assert.assertTrue(page.locator("text=Brands").isVisible());
    }




}
