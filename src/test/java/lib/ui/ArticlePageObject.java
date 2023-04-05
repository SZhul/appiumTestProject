package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "//*[@resource-id='org.wikipedia:id/page_contents_container']/*/*/*/*/*",
        FOOTER_ELEMENT = "//*[contains(@text, 'View article in browser')]",
        SAVE_BUTTON = "org.wikipedia:id/page_save",
        ADD_TO_LIST_BUTTON = "//*[contains(@text, 'ADD TO LIST')]",
        SAVE_MODAL_INPUT_FIELD = "org.wikipedia:id/text_input",
        SAVE_MODAL_SAVE_BUTTON = "android:id/button1";





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

    public void swipeUp(){
        this.swipeUp(500);
    }





    public void saveArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Не могу найти элемент Save на странице Java",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "не появилось меню с Add to List",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(SAVE_MODAL_INPUT_FIELD),
                name_of_folder,
                "не удалось ввести сообщение в поле для saved list",
                5
        );
        this.waitForElementAndClick(
                By.id(SAVE_MODAL_SAVE_BUTTON),
                "не удалось сохранить значения",
                5
        );
    }
}
