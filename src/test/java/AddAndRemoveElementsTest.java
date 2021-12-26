import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddAndRemoveElementsTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    int countAddButtons = 2;
    int countRemoveButtons = 1;

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Test
    public void AddAndRemoveTest() {
        validateAddElement(countAddButtons);
        validateRemoveElement(countRemoveButtons);
    }

    private void validateAddElement(int countAddButtons) {
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        validateAddButtonsChange(addButton, countAddButtons);
    }

    private void validateRemoveElement(int countRemoveButtons) {
        List<WebElement> removeButton = driver.findElements(By.xpath("//*[@id=\"elements\"]/button"));
        validateRemoveButtonsChange(removeButton, countRemoveButtons);
        validateCountOfElement(removeButton);
    }


    private void validateAddButtonsChange(WebElement button, int count) {
        for (int i = 0; i < count; i++) {
            button.click();
        }
    }

    private void validateRemoveButtonsChange(List<WebElement> button, int count) {
        for (int i = 0; i < count; i++) {
            button.get(i).click();
            button.remove(i);
        }
    }

    private void validateCountOfElement(List<WebElement> button) {
        Assert.assertEquals(button.size(), 1, "Неверное количество элементов");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}