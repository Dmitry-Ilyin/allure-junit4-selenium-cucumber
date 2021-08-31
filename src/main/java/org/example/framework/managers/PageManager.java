package org.example.framework.managers;


import org.example.framework.pages.CartPage;
import org.example.framework.pages.ResultSearchPage;
import org.example.framework.pages.HomePage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Каталог товаров
     */
    private ResultSearchPage resultSearchPage;

    /**
     * Коризна
     */
    private CartPage cartPage;



    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return StartPage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link ResultSearchPage}
     *
     * @return СatalogPage
     */
    public ResultSearchPage getResultSearchPage() {
        if (resultSearchPage == null) {
            resultSearchPage = new ResultSearchPage();
        }
        return resultSearchPage;
    }

    /**
     * Ленивая инициализация {@link CartPage}
     *
     * @return CartPageSteps
     */
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }




    public static void resetPageManager() {
        pageManager = null;
    }



}
