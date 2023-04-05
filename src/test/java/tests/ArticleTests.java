package tests;

import lib.InitDriver;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SaveFoldersPageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArticleTests extends InitDriver {

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
    public void testSwipeToElement(){
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.waitForSearchResultAndClick("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(android);
        articlePageObject.swipeToElement();
    }
}
