package org.example.framework.steps;

import io.cucumber.java.ru.И;
import org.example.framework.managers.PageManager;

public class HomePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Заполнить поиск значением \"(.+)\"$")
    public void fillSearch(String value) {
        pageManager.getHomePage().fillSearch(value);
    }
}
