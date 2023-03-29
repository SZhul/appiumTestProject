package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INIT_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",

    SEARCH_ARROWBACK_BUTTON = "android.widget.ImageButton",
    SEARCH_RESULT_BY_STRING_OR_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{STRING_OR_SUBSTRING}']";

// забираем драйвер из MainPageObject
    public SearchPageObject(AppiumDriver android){
        super(android);
    }



    public void initSearchInput(){
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Не удалось кликнуть на строку поиска",
                5);
        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Не произошел переход в поиск после клика по нему",
                5
        );
    }

    public void clickArrowButton(){
        this.waitForElementAndClick(
                By.className(SEARCH_ARROWBACK_BUTTON),
                "не найдена стрелочка назад в поиске",
                5
        );
    }

    public void cancelSearchAndClearInput(){
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "не найдена кнопка очистки строки",
                5
        );
    }

    public void waitForCancelSearchButton(){
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "не найдена кнопка очистки строка",
                5
        );
    }

    public void waitForCancelSearchButtonNotPresent(){
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "кнопка крестик осталась на странице",
                5
        );
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INIT_INPUT),
                search_line,
                "Не получилось ввести текст в строку поиска",
                5
        );
    }


    public void waitForSearchResult(String string_or_substring){
        String search_result_xpath = getResultSearchElement(string_or_substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Не удалось дождаться элемента на странице с заменой сабстринга на " + string_or_substring,
                15
        );
    }

    public void waitForSearchResultAndClick(String string_or_substring){
        String search_result_xpath = getResultSearchElement(string_or_substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Не удалось кликнуть на подзаголовок элемента",
                15
        );
    }


    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String string_or_substring){
        return SEARCH_RESULT_BY_STRING_OR_SUBSTRING_TPL.replace("{STRING_OR_SUBSTRING}", string_or_substring);
    }
    /* TEMPLATE METHODS */

}
