package org.example.framework.steps;

import io.cucumber.java.ru.И;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.example.framework.managers.PageManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CartPageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Проверка, что находимся в корзине$")
    public void checkCartPage() {
        pageManager.getCartPage().checkCartPage();
    }

    @И("^Удаление всех продуктов из корзины$")
    public void removeAllProduct() {
        pageManager.getCartPage().removeAllProduct();
    }

    @И("^Проверка пустая ли корзина после удаления$")
    public void checkCartAfterRemoveAll() {
        pageManager.getCartPage().checkCartAfterRemoveAll();
    }

    @И("^Проверка, что добавленные продукты соответствует тому, что находятся в корзине$")
    public void checkProductInCart() {
        pageManager.getCartPage().checkProductInCart();
    }

    @И("^Записываем данные в файл$")
    public void  writeProductInFile() {
        pageManager.getCartPage(). writeProductInFile();
        try {
            Allure.getLifecycle().addAttachment("file",
                    "text/plain", null, Files.readAllBytes(Paths.get("Products.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Attachment(value = "Вложение", type = "text/plain")
//    public static byte[] methodTest() {
//        byte [] array = new byte[10000];
//        try {
//            array =  Files.readAllBytes(Paths.get("Products"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return array;
//    }

}
