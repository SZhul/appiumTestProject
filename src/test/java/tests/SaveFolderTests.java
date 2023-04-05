package tests;

import lib.InitDriver;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SaveFoldersPageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;

public class SaveFolderTests extends InitDriver {

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
}
