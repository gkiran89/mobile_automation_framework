package co.sample.pages.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Object class
 */
public class AmazonObjectClass {

    @AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/skip_sign_in_button']")
    public MobileElement skipSignIn;

    @AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
    public MobileElement searchBar;

    @AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text']")
    public MobileElement selectSearchedItem;

    @AndroidFindBy(xpath = "//*[@class='android.widget.ListView']//following::android.widget.LinearLayout[4]//following::android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title']")
    public MobileElement searchedItem;

    @AndroidFindBy(xpath = "//*[@resource-id='title_feature_div']//following::android.view.View[1]")
    public MobileElement selectedItemInScreen;

    @AndroidFindBy(xpath = "//*[@resource-id='add-to-cart-button']")
    public MobileElement addToCartBtn;

    @AndroidFindBy(xpath = "//*[@text='Proceed to checkout']")
    public MobileElement proceedToCheckoutBtn;
}