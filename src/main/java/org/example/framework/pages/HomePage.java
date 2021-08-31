package org.example.framework.pages;

import org.example.framework.managers.PageManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Стартовая страница приложения
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Искать на Ozon']")
    private WebElement search;


    /**
     * Заполнить поиск
     *
     * @return ResultSearchPage - т.е. переходим на другую страницу
     */
    public ResultSearchPage fillSearch(String value){
        waitUtilElementToBeVisible(search).sendKeys(value);
        search.sendKeys(Keys.ENTER);
        return PageManager.getPageManager().getResultSearchPage().checkOpenResultSearchPage(value);
    }

}
