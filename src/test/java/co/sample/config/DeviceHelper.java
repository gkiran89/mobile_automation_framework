package co.sample.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class DeviceHelper {

    public AppiumDriver driver;

    public DeviceHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Perform swipe action vertically from point X to point Y on any screen
     */
    public void swipeUp() {
        Dimension size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.85);
        int endy = (int) (size.height * 0.2);
        int startx = (int) (size.width / 2.2);
        try {
            System.out.println("Trying to swipe up from x:" + startx + " y:" + starty + ", to x:" + startx + " y:" + endy);
            new TouchAction(driver).press(point(startx, starty)).waitAction(waitOptions(ofSeconds(2)))
                    .moveTo(point(startx, endy)).release().perform();
        } catch (Exception e) {
            System.out.println("Swipe action was not successful.");
        }
    }

    public void waitTillTheElementIsVisibleAndClickable(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This Function is to check the element is present or not
     */
    public boolean isElementDisplayed(MobileElement locator) {
        try {
            if (locator.isDisplayed())
                System.out.println("Element present on screen ***********" + locator);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present on screen **************" + locator);
            return false;
        }
    }

    /**
     * This Function is to Scroll to element
     */
    public void scrollToMobileElement(MobileElement element, String scrollCount) {
        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementDisplayed(element)) {
                    break;
                } else {
                    swipeUp();
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    /**
     * This function will wait for the element to be visible and clickable and then clicks on it
     */
    public void waitAndClick(MobileElement element) {
        waitTillTheElementIsVisibleAndClickable(element);
        element.click();
    }

    public String getText(MobileElement element) {
        String elementText = element.getText();
        return elementText;
    }
}

