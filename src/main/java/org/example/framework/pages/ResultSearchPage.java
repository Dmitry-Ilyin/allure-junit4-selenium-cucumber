package org.example.framework.pages;

import org.example.framework.Classes.Container;
import org.example.framework.Classes.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ResultSearchPage extends BasePage {

    @FindBy(xpath= "//div[@data-widget='fulltextResultsHeader']//strong")
    WebElement element;

    @FindBy(xpath = "//div[@value='Высокий рейтинг']")
    private WebElement highRating;

    @FindBy(xpath = "//div[@data-widget='searchResultsFiltersActive']")
    private WebElement filters;

    @FindBy(xpath = "//div[@class='g1o2']/../..//div[@class='g1h8']")
    private List<WebElement> parameterClick;

    @FindBy(xpath = "//div[@unit='[object Object]']/../div[@class='g1h8']")
    private List<WebElement> parametersWrite;

    @FindBy(xpath = "//div[contains(@style,'grid-column-start')]")
    private List<WebElement>  productNames;

    @FindBy(xpath = "//span[contains(text(),'Корзина')]/../span[contains(@class,'f')]")
    private WebElement numberProductInCart;

    @FindBy(xpath = "//a[@data-widget='headerIcon']")
    private WebElement btnCart;

    /**
     * Проверить, что в нужном каталоге
     *
     * @return ResultSearchPage - т.е. остаемся на этой странице
     */
    public ResultSearchPage checkOpenResultSearchPage(String value) {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
        value, element.getText());
        return this;
    }


    /**
     * Фильтруем поиск по рейтингу
     *
     * @return ResultSearchPage - т.е. остаемся на этой странице
     */
    public ResultSearchPage selectHighRating() {
        waitUtilElementToBeClickable(highRating).click();
        waitUtilElementToBeVisible(filters.findElement(By.xpath(".//span[contains(text(), 'Высокий рейтинг')]")));
        return this;
    }


    /**
     * Фильтруем кликабельные параметры.(Линейка, Модель и т.д)
     *
     * @param nameParameter - имя параметра
     * @param parameterItem - выбор параметра
     * @return ResultSearchPage - т.е. остаемся на этой странице
     */
    public ResultSearchPage selectParametersClick(String nameParameter, String parameterItem) {
        for (WebElement element : parameterClick) {
            scrollWithOffset(element, 0, -150);
            if (attributeFilter(element.getAttribute("outerText")).contains(attributeFilter(nameParameter))) {
                wait.until(ExpectedConditions.textToBePresentInElement(element, "Беспроводные интерфейсы"));
                List<WebElement> selectCheckBox = element.findElements(By.xpath("./..//label"));
                for (WebElement temp : selectCheckBox) {
                    if (attributeFilter(temp.getAttribute("outerText")).contains(attributeFilter(parameterItem))) {
                        waitUtilElementToBeClickable(temp).click();
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Беспроводные интерфейсы: NFC')]")));
                        return this;
                    }
                }
                Assert.fail("фильтр " + parameterItem + " не существует");
                return this;
            }
        }
        Assert.fail("фильтр " + nameParameter + " не существует");
        return this;
    }


    /**
     * Фильтруем до граничного значения цену, емкость аккумулятора, вес
     *
     * @param nameParameter - имя параметра
     * @param priceBefore - до граничного значения
     * @return ResultSearchPage - т.е. остаемся на этой странице
     */
    public ResultSearchPage selectParametersWithRangeBefore(String nameParameter, String priceBefore) {
        for (WebElement element : parametersWrite) {
            if (attributeFilter(element.getAttribute("outerText")).contains(attributeFilter(nameParameter))) {
                WebElement temp = element.findElement(By.xpath("./..//input[@qa-id='range-to']"));
                scrollWithOffset(temp, 0, -150);
                fillInputField(temp, priceBefore);
                wait.until(ExpectedConditions.attributeToBe(temp, "value", priceBefore));
                return this;
            }
        }
        Assert.fail("фильтр " + nameParameter + " не существует");
        return this;
    }

    public ResultSearchPage addToCartEvenProduct(int countProduct){
        for(int i = 1; i < productNames.size(); i += 2){
            int addedProductsInCart = Container.getListProducts().size();
            if(countProduct <= addedProductsInCart ) {
                return this;
            }
            WebElement productElement = productNames.get(i);
            scrollWithOffset(productElement, 0, -150);
            addProductInCart(productElement);
            addProductInContainer(productElement);
            wait.until(ExpectedConditions.textToBePresentInElement(numberProductInCart, Integer.toString(addedProductsInCart+1)));
        }
        Assert.fail("Не было добавлено " + countProduct + " элементов в корзину");
        return this;
    }

    /**
     * Добавляем продукт в контейнер
     *
     */
    public void addProductInContainer(WebElement element) {
        String nameProduct = element.findElement(By.xpath("./div/div/div[2]/div/a/span/span")).getText();
        int priceProduct = Integer.parseInt(element.
                findElement(By.xpath(".//span[@style='color: rgb(249, 17, 85);']")).
                getAttribute("outerText").
                replaceAll("\\D",""));
        Container.getListProducts().add(new Product(nameProduct, priceProduct));
        System.out.println(nameProduct + " " +priceProduct);
    }


    /**
     * Добавляем продукт в корзину
     *
     */
    public void addProductInCart(WebElement element) {
        WebElement btnAddInCart = element.findElement(By.xpath(".//div[contains(text(),'В корзину')]/../.."));
        waitUtilElementToBeClickable(btnAddInCart).click();
        System.out.print(" Добавили в корзину ");
    }

    /**
     * Переходим в корзину
     *
     * @return CartPageSteps  - т.е. переходим на другую страницу
     */
    public CartPage goToCart() {
        wait.until(ExpectedConditions.textToBePresentInElement(numberProductInCart,
                Integer.toString(Container.getListProducts().size())));
        waitUtilElementToBeClickable(btnCart).click();
        return pageManager.getCartPage();
    }




}
