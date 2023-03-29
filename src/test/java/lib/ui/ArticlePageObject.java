package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "//*[@resource-id='org.wikipedia:id/page_contents_container']/*/*/*/*/*",
        FOOTER_ELEMENT = "//*[contains(@text, 'View article in browser')]";


    public ArticlePageObject(AppiumDriver android){
        super(android);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(
                By.xpath(TITLE),
                "не найден заголовок статьи",
                15
        );
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToElement(){
        this.swipeUpToElement(By.xpath(FOOTER_ELEMENT),
                "не удалось досвайпать до элемента",
                6);
    }
}
