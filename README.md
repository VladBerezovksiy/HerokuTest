# Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/ 
## Каждая страница - отдельный класс и тест.

#### Add/Remove Elements - добавить 2 элемента, удалить элемент, проверить количество элементов 
Локаторы xpath:
* By.xpath(“//button[text()=’Add Element’]”)
* By.xpath(“//*[text()=’Add Element’]”)
* By.xpath(“//*[@onclick=’addElement()’]”)


####Checkboxes - проверить, что первый чекбокс unchecked, отметить первый чекбокс, проверить что он checked. Проверить, что второй чекбокс checked, сделать unheck, проверить, что он unchecked
Локатор:
* By.cssSelector(“[type=checkbox]”)


#### Dropdown - Взять все элементы дроп-дауна и проверить их наличие. Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что он выбран
Локатор:
* By.id(“dropdown”)


#### Inputs - Проверить на возможность ввести различные цифровые и нецифровые значения, используя Keys.ARROW_UP  И Keys.ARROW_DOWN
Локатор:
* By.tagName(“input”)


#### Sortable Data Tables - Проверить содержимое нескольких (3-5) ячеек таблицы. Использовать xpath типа //table//tr[1]//td[1] - получение первой ячейки из первого ряда первой таблицы и так далее


#### Typos - Проверить соответствие параграфа орфографии
  Локатор:
  * By.tagName(“p”)


#### Hovers - Сделать цепочку из действий: наведение на профиль, проверка имени, клик по ссылке, проверка 404 ошибки. Повторить для каждого из профилей. Использовать класс Actions и https://stackoverflow.com/questions/17293914/how-to-perform-mouseover-function-in-selenium-webdriver-using-java


#### Notification Messages - кликнуть на кнопку, дождаться появления нотификации, проверить соответствие текста ожиданиям



# Second Part



#### Context Menu
* Правый клик по элементу
* Валидация текста на алерте
* Закрытие алерта

#### Dynamic Controls
* Найти чекбокс
* Нажать на кнопку
* Дождаться надписи “It’s gone”
* Проверить, что чекбокса нет
* Найти инпут
* Проверить, что он disabled
* Нажать на кнопку
* Дождаться надписи “It's enabled!”
* Проверить, что инпут enabled

#### File Upload
* Загрузить файл. Для относительного пути использовать https://stackoverflow.com/questions/45612089/how-to-upload-file-with-relative-path-in-selenium-webdriver 
* Проверить, что имя файла на странице совпадает с именем загруженного файла

#### Frames
* Открыть iFrame
* Проверить, что текст внутри параграфа равен “Your content goes here.”

#### File Download 
* Изучить https://www.swtestacademy.com/download-file-in-selenium/ 
* Скачать файл
* Проверить наличие файла на файловой системе
** Добавить в метод isPageLoaded() из демонстрации на уроке обработку результата явно ожидания, чтоб вернуть false в методе, когда страница не прогрузилась за указанное время.
