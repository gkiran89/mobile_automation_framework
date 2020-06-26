package co.sample.config;

import co.sample.utils.ConfigurationManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

abstract class BaseClassSetUp {

    public ConfigurationManager prop;
    public AppiumDriverLocalService service;
    public AppiumDriver<WebElement> driver;
    DeviceHelper deviceHelper;

    /**
     * Constructor to load config.properties
     */
    BaseClassSetUp() {
        try {
            deviceHelper = new DeviceHelper(driver);
            prop = ConfigurationManager.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start Appium Server
     */
    public void startAppiumServer() {
        service = AppiumDriverLocalService.buildDefaultService();
        File logFile = new File(System.getProperty("user.dir") + "/logs/" + "AppiumServerLogs.txt");
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withLogFile(logFile));
        service.start();
    }

    /**
     * Stop Appium Server
     */
    public void stopAppiumServer() {
        service.stop();
    }

    /**
     * Launch App
     */
    public void launchApplication() throws MalformedURLException {
        System.out.println("EndPoint used is " + prop.getProperty("endpoint"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", prop.getProperty("newCommandTimeout"));
        capabilities.setCapability("platformName", prop.getProperty("platformName"));
        capabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));
        capabilities.setCapability("appPackage", prop.getProperty("appPackage"));
        capabilities.setCapability("appActivity", prop.getProperty("appActivity"));
        capabilities.setCapability("deviceName", prop.getProperty("deviceName"));
        capabilities.setCapability("automationName", prop.getProperty("automationName"));
        capabilities.setCapability("app", System.getProperty("user.dir") + "/" + prop.getProperty("app"));
        capabilities.setCapability("enforceAppInstall", "true");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        driver = new AndroidDriver<WebElement>(new URL(prop.getProperty("endpoint")), capabilities);
        ContextManager.setDriver(driver);
    }

    /**
     * Quiting the Driver
     */
    public void closeApplication() {
        if (ContextManager.getDriver() != null) {
            ContextManager.getDriver().quit();
        }
    }
}
