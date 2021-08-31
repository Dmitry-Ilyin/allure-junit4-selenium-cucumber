package org.example.framework.steps;

import io.cucumber.java.ru.И;
import org.example.framework.managers.PageManager;

public class ResultSearchPageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Проверка, что мы открыли каталог товаров с наименованием \"(.+)\"$")
    public void checkPage(String value) {
        pageManager.getResultSearchPage().checkOpenResultSearchPage(value);
    }

    @И("^Выставляем высокий рейтинг$")
    public void filterPageRating() {
        pageManager.getResultSearchPage().selectHighRating();
    }

    @И("^Выставляем для параметра \"(.+)\" максимальное значение \"(.+)\"$")
    public void filterPageParametersBefore(String nameParameter, String priceBefore) {
        pageManager.getResultSearchPage().selectParametersWithRangeBefore(nameParameter, priceBefore);
    }

    @И("^Выбираем параметр \"(.+)\" со значением \"(.+)\" и кликаем$")
    public void filterPageParametersClick(String nameParameter, String value) {
        pageManager.getResultSearchPage().selectParametersClick(nameParameter, value);
    }

    @И("^Добавляем \"(.+)\" четных по списку продуктов$")
    public void chooseProductWithEvenPrice(int number) {
        pageManager.getResultSearchPage().addToCartEvenProduct(number);
    }

    @И("^Переходим в корзину$")
    public void goToCart() {
        pageManager.getResultSearchPage().goToCart();
    }


}
