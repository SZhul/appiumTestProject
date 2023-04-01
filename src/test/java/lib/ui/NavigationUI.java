package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String

            MAIN_SCREEN_SAVE_BUTTON = "//*[@resource-id='org.wikipedia:id/main_nav_tab_layout']//*[@content-desc='Saved']";

    public NavigationUI(AppiumDriver android){
        super(android);
    }

    public void clickSaveOnMainPage(){
        this.waitForElementAndClick(
                By.xpath(MAIN_SCREEN_SAVE_BUTTON),
                "не удалось нажать на Save на стартовом экране",
                10
        );
    }



}
