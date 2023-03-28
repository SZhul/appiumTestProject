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
    public void testCompareArticleTitle()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Не удается ввести текст в поле ввода логина");
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "не найден результат в строке поиска");
        WebElement element = mainPageObject.waitForElementPresent(
                By.className("android.widget.TextView"),
                "не получается найти заголовок",
                15
        );
        String header = element.getAttribute("text");
        Assertions.assertEquals(
                "Java (programming language)", header,
                "сравниваемые значения не эквивалентны");
    }

    @Test
    public void TestSwipeArticle() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Не удается ввести текст в поле ввода логина");
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "не найден результат в строке поиска");
        mainPageObject.waitForElementPresent(
                By.className("android.widget.TextView"),
                "не получается найти заголовок",
                15
        );
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
    public void testSearch()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Не удается ввести текст в поле ввода логина");
        mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "cannot find java",
                15);
    }

    @Test
    public void cancelSearchTest()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "не вводится текст в поисковую строку"
                , 5);
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "не найдена кнопка очистки строки");
        mainPageObject.waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "не найдена кнопка возвращения назад");
        mainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "крестик очистки строки остался на странице",
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
    public void saveFirstArticle(){
        String valueToStartSeach = "Search Wikipedia";
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + valueToStartSeach +"')]"),
                "не найден поиск");
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, '" + valueToStartSeach + "')]"),
                "Java",
                "Не удается ввести текст в поле ввода логина");
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "cannot find java",
                15);
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Не могу найти элемент Save на странице Java",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'ADD TO LIST')]"),
                "не появилось меню с Add to List",
                5
        );
        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Appium lessons",
                "не удалось ввести сообщение в поле для saved list",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "не удалось сохранить значения",
                5
        );

        String arrowToBack = "Navigate up";

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='" + arrowToBack + "']"),
                "не удалось нажать на стрелочку",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='" + arrowToBack + "']"),
                "не удалось нажать на стрелочку",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "не удалось нажать на saved на стартовом экране",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Appium lessons')]"),
                "не удалось перейти в список сохраненных статей",
                5
        );
        mainPageObject.swipeToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "не удалось найти элемент для свайпа");
        mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='You have no articles added to this list.']"),
                "не обнаружен плейсхолдер о пустой странице",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_overflow_menu"),
                "не найдена кнопка бургер-меню",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Delete list']"),
                "не найдена кнопка Delete для удаления категории сохраненных страниц",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Не получилось нажать на ОК при удалении категории сохраненных страниц",
                5
        );
        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/empty_title"),
                "Не найден плейсхолдер, обозначающий отсутствие сохраненных статей",
                5
        );
    }

    @Test
    public void assertTest(){
        String valueToStartSeach = "Search Wikipedia";
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + valueToStartSeach +"')]"),
                "не найден поиск");
        String valueToSearchLine = "Linkin Park";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, '" + valueToStartSeach + "')]"),
                valueToSearchLine,
                "Не удается ввести текст в поле ввода логина");
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        mainPageObject.waitForElementPresent(
                By.xpath(searchResultLocator),
                "не найден элемент в поиске " + valueToSearchLine,
                15);

        int amountOfSearchResults = mainPageObject.getAmounOfElements(
                By.xpath(searchResultLocator));

        System.out.println(amountOfSearchResults);

        Assertions.assertTrue(
                amountOfSearchResults > 0,
                "мы нашли слишком мало результатов"
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        String valueToStartSearch = "Search Wikipedia";
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + valueToStartSearch +"')]"),
                "не найден поиск");

        String valueToSearchLine = "zxcvasdf";

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, '" + valueToStartSearch + "')]"),
                valueToSearchLine,
                "Не удается ввести текст в поле ввода логина");

        String searchLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/results_text']";

        mainPageObject.waitForElementPresent(
                By.xpath(searchLocator),
                "не удалось найти элемент для ожидания",
                10
        );

        mainPageObject.assertElementWithOnePresent(By.xpath(searchLocator), "обнаружены элементы на странице по запросу " + valueToSearchLine);
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
