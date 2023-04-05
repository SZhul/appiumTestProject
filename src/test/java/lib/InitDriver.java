package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.MainPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class InitDriver {
    protected AppiumDriver android;
    public static String appiumURL = "http://127.0.0.1:4723/wd/hub";



    @BeforeEach
    protected void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","13.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
        capabilities.setCapability("appValue","D:\\Git\\appiumTestProject\\apks\\org.wikipedia_2.7.50433-r-2023-03-13_50433.apk");
        capabilities.setCapability("noReset", true);

        android = new AndroidDriver(new URL(appiumURL), capabilities);
        this.rotateScreenToLandscape();
    }

    @AfterEach
    protected void tearDown()
    {
        android.quit();
    }

    protected void rotateScreenToPortrait(){
        android.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenToLandscape(){
        android.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void runAppInBackground(int seconds){
        android.runAppInBackground(Duration.ofSeconds(seconds));
    }
}
