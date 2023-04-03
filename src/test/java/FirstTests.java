import lib.InitDriver;
import lib.ui.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class FirstTests extends InitDriver {

    MainPageObject mainPageObject;

    @BeforeEach
    protected void setUp() throws Exception{
        super.setUp();
        mainPageObject = new MainPageObject(android);
    }


    @Test
    public void testSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Selenium");
        searchPageObject.waitForSearchResult("Testing framework for web applications");
    }

    @Test
    public void cancelSearchTest()
    {
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForCancelSearchButton();
        searchPageObject.cancelSearchAndClearInput();
        searchPageObject.waitForCancelSearchButtonNotPresent();
    }

    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Selenium");
        searchPageObject.waitForSearchResultAndClick("Testing framework for web applications");

        ArticlePageObject articlePageObject = new ArticlePageObject(android);

        Assertions.assertEquals(
                "Selenium (software)", articlePageObject.getArticleTitle(),
                "сравниваемые значения не эквивалентны");
    }

    @Test
    public void TestSwipeArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Selenium");
        searchPageObject.waitForSearchResultAndClick("Testing framework for web applications");

        ArticlePageObject articlePageObject = new ArticlePageObject(android);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToElement();
    }

    @Test
    public void saveFirstArticle(){
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Javascript");
        searchPageObject.waitForSearchResultAndClick("JavaScript");

        ArticlePageObject articlePageObject = new ArticlePageObject(android);

        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        System.out.println(article_title);
        String name_of_folder = "Appium lessons";
        articlePageObject.saveArticleToMyList(name_of_folder);
        searchPageObject.clickArrowButton();
        searchPageObject.clickArrowButton();

        NavigationUI navigationUI = new NavigationUI(android);

        navigationUI.clickSaveOnMainPage();

        SaveFoldersPageObject saveFoldersPageObject = new SaveFoldersPageObject(android);

        saveFoldersPageObject.clickToFolderInSavePage(name_of_folder);

        saveFoldersPageObject.getArticleTitleInSavedFolder();

        saveFoldersPageObject.checkArticleNotDelete(article_title);
        saveFoldersPageObject.swipeByArticleToDelete(article_title);
        saveFoldersPageObject.checkArticleDeleteAfterSwipe(article_title);
        saveFoldersPageObject.checkEmptyArticlePlaceholder();
        saveFoldersPageObject.clickToKebab();
        saveFoldersPageObject.clickToDeleteFolder();
        saveFoldersPageObject.clickOkAfterDelete();
        saveFoldersPageObject.GetEmptyFolderPlaceholderTitle();
    }

    @Test
    public void amountOfSearchArticles(){
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        String search_line = "linkin park";
        searchPageObject.typeSearchLine(search_line);
        int amountOfSearchResults = searchPageObject.getAmountOfSearchResults();
        System.out.println(amountOfSearchResults);
        Assertions.assertTrue(
                amountOfSearchResults > 0,
                "мы нашли слишком мало результатов"
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject searchPageObject = new SearchPageObject(android);
        searchPageObject.initSearchInput();
        String search_line = "zxcvasdf";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.assertWhenSearchIsEmpty(search_line);
    }

    @Test
    public void listTest() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Не удается ввести текст в поле ввода логина");
        List<WebElement> elements = android.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        System.out.println(elements.size());
        for (WebElement e : elements) {
            Assertions.assertTrue(e.getText().toLowerCase().contains("java"), "message is " + e.getText());
        }
    }

    @Test
    public void testSwipeToElement(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Не удается ввести текст в поле ввода логина");
        mainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Automation for Apps')]"),
                "не получается найти заголовок",
                15
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Automation for Apps')]"),
                "не найден результат в строке поиска");
        mainPageObject.swipeUpToElement(
                By.xpath("//*[contains(@text, 'View article in browser')]"),
                "элемент не найден на странице Appium",
                5);
    }



    @Test
    public void clearTest(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "не вводится текст в поисковую строку",
                5);
        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "не получается удалить текст",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "не найдена кнопка возвращения назад");
    }

    @Test
    public void rotationTest(){
        String valueToStartSearch = "Search Wikipedia";
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + valueToStartSearch +"')]"),
                "не найден поиск");

        String valueToSearchLine = "Java";
        String xPathToFind = "//*[contains(@text, 'Java (programming language)')]";

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, '" + valueToStartSearch + "')]"),
                valueToSearchLine,
                "Не удается ввести текст в поле ввода логина");

        mainPageObject.waitForElementAndClick(
                By.xpath(xPathToFind),
                "cannot find " + valueToSearchLine,
                15);

        String xPathToFindHeader = "//*[@resource-id='org.wikipedia:id/page_contents_container']/*/*/*/*/*";

        String title_before_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.xpath(xPathToFindHeader),
                "text",
                "не удалось найти элемент на странице",
                5
        );

        android.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.xpath(xPathToFindHeader),
                "text",
                "не удалось найти элемент на странице",
                5
        );

        Assertions.assertEquals(title_before_rotation, title_after_rotation, "элемент был изменен после поворота экрана");

        android.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.xpath(xPathToFindHeader),
                "text",
                "не удалось найти элемент на странице",
                5
        );

        Assertions.assertEquals(title_before_rotation, title_after_second_rotation, "элемент был изменен после поворота экрана");
    }

    @Test
    public void backgroundTest(){

        String valueToStartSearch = "Search Wikipedia";
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + valueToStartSearch +"')]"),
                "не найден поиск");

        String valueToSearchLine = "Java";
        String xPathToFind = "//*[contains(@text, 'Java (programming language)')]";

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, '" + valueToStartSearch + "')]"),
                valueToSearchLine,
                "Не удается ввести текст в поле ввода логина");

        WebElement x1 = mainPageObject.waitForElementPresent(
                By.xpath(xPathToFind),
                "не удается найти элемент в строке выдачи",
                15
        );

        android.runAppInBackground(Duration.ofSeconds(2));


        WebElement x2 = mainPageObject.waitForElementPresent(
                By.xpath(xPathToFind),
                "не удается найти элемент в строке выдачи после ухода в бэкграунд и возврата обратно",
                15
        );

        Assertions.assertEquals(x1.getText(), x2.getText());

    }
}
