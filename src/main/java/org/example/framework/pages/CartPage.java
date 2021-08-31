package org.example.framework.pages;


import org.example.framework.Classes.Container;
import org.example.framework.Classes.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


public class CartPage extends BasePage {


    @FindBy(xpath = "//div[contains(text(), 'Корзина')]")
    private WebElement heading;

    @FindBy(xpath = "//a[@class='a7n3']/span")
    private List<WebElement> listNameProduct;

    @FindBy(xpath = "//div[@qa-id='checkcart-confirm-modal-confirm-button']")
    private WebElement trueRemove;

    @FindBy(xpath = "//span[@class='b4i9 b4j']")
    private WebElement removeAllProduct;

    @FindBy(xpath = "//h1")
    private WebElement cartAfterRemoveAll;



    /**
     * Проверка, что мы в корзине
     *
     * @return CartPageSteps  - т.е. остаемся на этой странице
     */
    public CartPage checkCartPage() {
        Assert.assertEquals("Находимся не в корзине", "Корзина", heading.getText());
        return this;
    }

    /**
     * Проверка, что все добавленные элементы в корзине
     *
     * @return CartPageSteps  - т.е. остаемся на этой странице
     */
    public CartPage checkProductInCart() {
        Container.getListProducts().forEach((product) -> {
            boolean b = false;
            for (WebElement element : listNameProduct) {
                String name = element.getAttribute("outerText");
                int price = Integer.parseInt(element.findElement(By.xpath("./../../..//div[@class='a7m6 a7o0']/div[1]/div[contains(@style,'font-size')]/span"))
                        .getAttribute("outerText")
                        .replaceAll("\\D", ""));
                if (product.getName().equals(name) && price == product.getPrice()) {
                    b = true;
                    System.out.print(product.getName() + " ");
                    System.out.println(product.getPrice());
                }
            }
            if (!b) {
                Assert.fail("Некорректный элемент в корзине");
            }
        });
        return this;
    }


    /**
     * Удалить все элементы
     *
     * @return CartPageSteps  - т.е. остаемся на этой странице
     */
    public CartPage removeAllProduct() {
        waitUtilElementToBeClickable(removeAllProduct).click();
        waitUtilElementToBeClickable(trueRemove).click();
        return this;
    }

    /**
     * проверка корзины после удаления
     *
     * @return CartPageSteps  - т.е. остаемся на этой странице
     */
    public CartPage checkCartAfterRemoveAll() {
        Assert.assertEquals("Проверка пустая ли корзина",
                "Корзина пуста",
                cartAfterRemoveAll.getText().trim());
        return this;
    }

    /**
     * Запись продуктов в файл
     *
     * @return CartPageSteps  - т.е. остаемся на этой странице
     */
    public CartPage writeProductInFile() {
        try(FileWriter writer = new FileWriter(new File("Products.txt"),false)){
            Product productMaxPrice = Container.getListProducts()
                    .stream()
                    .max(Comparator.comparingInt(Product::getPrice))
                    .get();

            Container.getListProducts().forEach( (product) -> {
                StringBuilder sb = new StringBuilder();
                sb.append(product.getName() + " ")
                        .append(product.getPrice())
                        .append("\n");
                try {
                    writer.write(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("Продукт с дорогой ценой : " + productMaxPrice.getName() + " " + productMaxPrice.getPrice());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
