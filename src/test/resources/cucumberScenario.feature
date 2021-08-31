#language: ru
@all
Функционал: Zoom

  @firstTest
  Сценарий: Проверка функционала сайта
    * Заполнить поиск значением "iphone"
    * Проверка, что мы открыли каталог товаров с наименованием "iphone"
    * Выставляем высокий рейтинг
    * Выставляем для параметра "Цена" максимальное значение "100000"
    * Выбираем параметр "Беспроводные интерфейсы" со значением "NFC" и кликаем
    * Добавляем "8" четных по списку продуктов
    * Переходим в корзину
    * Проверка, что находимся в корзине
    * Проверка, что добавленные продукты соответствует тому, что находятся в корзине
    * Записываем данные в файл
    * Удаление всех продуктов из корзины
    * Проверка пустая ли корзина после удаления