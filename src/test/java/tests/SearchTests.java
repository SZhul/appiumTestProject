package tests;

import lib.InitDriver;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchTests extends InitDriver {

    @Test
    public void testSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Selenium");
        searchPageObject.waitForSearchResult("Selenium");
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
        SearchPageObject searchPageObject = new SearchPageObject(android);
        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.getAmountOfSearchResults();
        searchPageObject.assertWhenSearchIsNotEmpty(search_line);
    }

    @Test
    public void clearTest(){
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.clearSearchInput();
        searchPageObject.clickArrowButton();
    }

}
