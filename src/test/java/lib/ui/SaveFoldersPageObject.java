package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SaveFoldersPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[contains(@text, '{FOLDER_NAME}')]",
            ARTICLE_TO_SWIPE_TPL = "//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']//*[@text='{ARTICLE_TITLE}']",
            EMPTY_ARTICLES_PLACEHOLDER = "//*[@resource-id='org.wikipedia:id/reading_list_empty_text']",
            KEBAB_MENU = "org.wikipedia:id/item_overflow_menu",
            DELETE_BUTTON = "//*[@text='Delete list']",
            OK_AFTER_DELETE_BUTTON = "//*[@text='OK']",
            EMPTY_FOLDER_PLACEHOLDER_TITLE = "org.wikipedia:id/empty_title";

    /*TEMPLATE METHODS*/

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getArticleXpathToSwipe(String article_title){
        return ARTICLE_TO_SWIPE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    /*TEMPLATE METHODS*/

    public SaveFoldersPageObject(AppiumDriver android){
        super(android);
    }

    public void clickToFolderInSavePage(String name_of_folder) {
        String folder_name_Xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_Xpath),
                "не удалось перейти в список сохраненных статей",
                20
        );
    }

    public void swipeByArticleToDelete(String article_title){
        String article_title_Xpath = getArticleXpathToSwipe(article_title);
                this.swipeToLeft(
                        By.xpath(article_title_Xpath),
                        "не удалось найти элемент для свайпа");
    }

    public void checkArticleDeleteAfterSwipe(String article_title){
        String article_title_Xpath = getArticleXpathToSwipe(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title_Xpath),
                "Заголовок остался в сохраненном",
                10
        );
    }

    public void checkArticleNotDelete(String article_title){
        String article_title_Xpath = getArticleXpathToSwipe(article_title);
        this.waitForElementPresent(
                By.xpath(article_title_Xpath),
                "Не получилось найти заголовок для удаления по запросу " + article_title,
                10
        );
    }

    public void checkEmptyArticlePlaceholder(){
        this.waitForElementPresent(
                By.xpath(EMPTY_ARTICLES_PLACEHOLDER),
                "не обнаружен плейсхолдер о пустой странице",
                10
        );
    }

    public void getArticleTitleInSavedFolder(){
        this.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "text",
                "не удалось получить заголовок статьи в сохраненном",
                10
        );
    }

    public String getStringArticleTitleInSavedFolder(){
        return this.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "text",
                "не удалось получить заголовок статьи в сохраненном",
                10
        );
    }

    public void clickToKebab(){
        this.waitForElementAndClick(
                By.id(KEBAB_MENU),
                "не найдена кнопка кебаб-меню",
                5
        );
    }

    public void clickToDeleteFolder(){
        this.waitForElementAndClick(
                By.xpath(DELETE_BUTTON),
                "не найдена кнопка Delete для удаления категории сохраненных страниц",
                5
        );
    }

    public void clickOkAfterDelete(){
        this.waitForElementAndClick(
                By.xpath(OK_AFTER_DELETE_BUTTON),
                "Не получилось нажать на ОК при удалении категории сохраненных страниц",
                5
        );
    }

    public void GetEmptyFolderPlaceholderTitle(){
        this.waitForElementPresent(
                By.id(EMPTY_FOLDER_PLACEHOLDER_TITLE),
                "Не найден плейсхолдер, обозначающий отсутствие сохраненных статей",
                5
        );
    }


}
