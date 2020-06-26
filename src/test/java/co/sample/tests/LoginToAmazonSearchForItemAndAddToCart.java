package co.sample.tests;

import co.sample.pages.Actions.AmazonActionClass;
import co.sample.utils.BaseClass;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Login to amazon mobile application and search for an item and add to cart and purchase it.
 *
 * @author : Gurukiran H N
 */
public class LoginToAmazonSearchForItemAndAddToCart extends BaseClass {

    public AmazonActionClass getAmazonActionClass() {
        return new AmazonActionClass();
    }

    @Test(enabled = true, description = "Login to amazon mobile application and search for an item and add to cart and purchase it ")
    public void verifyUserAbleToStreamContentsOnline() throws Exception {
        getAmazonActionClass().tapOnSkipSignIn();
        Reporter.log("Tapping on skip sign in");
        getAmazonActionClass().searchForParticularModel(getAmazonActionClass().getCellData("TestData", 1, 1));
        Reporter.log("Search for particular item");
        String textInSearchScreen = getAmazonActionClass().getTextOfSearchedItem();
        Reporter.log("Getting the text of item from search screen");
        getAmazonActionClass().tapOnSearchedItem();
        Reporter.log("Tap on searched item");
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Reporter.log("Rotate screen to landscape");
        driver.rotate(ScreenOrientation.PORTRAIT);
        Reporter.log("Rotate screen to portrait");
        String textInItemSelectedScreen = getAmazonActionClass().getTextOfSelectedItem();
        Reporter.log("Getting the text of item from item selected screen");
        Assert.assertEquals(textInItemSelectedScreen, textInSearchScreen, "Item name is matching in both searched screen and checkout screen");
        Reporter.log("Comparing the information of selected item");
        getAmazonActionClass().tapOnAddToCartBtn();
        Reporter.log("Tap on add to cart button");
        getAmazonActionClass().tapOnProceedToCheckoutBtn();
        Reporter.log("Tap on proceed to checkout button");
    }
}
