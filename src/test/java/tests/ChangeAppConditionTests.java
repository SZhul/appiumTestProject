package tests;

import lib.InitDriver;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class ChangeAppConditionTests extends InitDriver {

    @Test
    public void rotationTest(){
        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        String search_line = "Linkin Park";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResultAndClick("2017 tribute concert");

        ArticlePageObject articlePageObject = new ArticlePageObject(android);
        String title_before_rotation =  articlePageObject.getArticleTitle();
        System.out.println(title_before_rotation);
        this.rotateScreenToPortrait();
        String title_after_rotation = articlePageObject.getArticleTitle();
        System.out.println(title_after_rotation);
        Assertions.assertEquals(title_before_rotation, title_after_rotation, "элемент был изменен после поворота экрана");
        this.rotateScreenToLandscape();
        String title_after_second_rotation = articlePageObject.getArticleTitle();
        System.out.println(title_after_second_rotation);
        Assertions.assertEquals(title_before_rotation, title_after_second_rotation, "элемент был изменен после поворота экрана");
    }

    @Test
    public void backgroundTest(){

        SearchPageObject searchPageObject = new SearchPageObject(android);

        searchPageObject.initSearchInput();
        String search_line = "Linkin Park";
        searchPageObject.typeSearchLine(search_line);
        WebElement element = searchPageObject.waitForSearchResultElement("2017 tribute concert");
        System.out.println(element.getText());
        this.runAppInBackground(2);
        WebElement element2 = searchPageObject.waitForSearchResultElement("2017 tribute concert");
        System.out.println(element2.getText());
        Assertions.assertEquals(element.getText(), element2.getText());

    }
}
