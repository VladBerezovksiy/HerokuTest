import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckboxesTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void checkboxesTest() {
        validateCheckboxFirst();
        validateCheckboxSecond();
    }

    private void validateCheckboxFirst() {
        WebElement checkbox = driver.findElement(By.cssSelector("[type=checkbox]"));
        validateCheckboxChange(checkbox, "Неверное состояние первого чекбокса");
    }

    private void validateCheckboxSecond() {
        WebElement checkbox = driver.findElement(By.cssSelector("#checkboxes > input[type=checkbox]:nth-child(3)"));
        checkbox.click();
        validateCheckboxChange(checkbox, "Неверное состояние второго чекбокса");
    }

    private void validateCheckboxChange(WebElement checkbox, String message) {
        Assert.assertFalse(checkbox.isSelected(), message);
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected(), message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}