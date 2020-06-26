package co.sample.utils;

import co.sample.config.WebDriverListener;
import org.testng.annotations.*;

import java.net.MalformedURLException;

@Listeners({WebDriverListener.class})
public class BaseClass extends WebDriverListener {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        startAppiumServer();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws MalformedURLException {
        System.out.println("=============================BeforeClass in TestSetup is executing==============================");
        launchApplication();
        System.out.println("Before class in Test Setup is executed");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("==================================After Class TestSetup=======================");
        closeApplication();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("After Suite");
        stopAppiumServer();
    }
}
