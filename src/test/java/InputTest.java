import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InputTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    int inputValue = 1;

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/inputs");
    }

    @Test
    public void inputTest() {
        this.validateInputIncrease(inputValue);
        this.validateInputDecrease(inputValue);
    }

    private void validateInputIncrease(int inputValue) {
        validateInputChange(inputValue, Keys.ARROW_UP, 1, "Неверное значение элемента input после увеличения");
    }

    private void validateInputDecrease(int inputValue) {
        validateInputChange(inputValue, Keys.ARROW_DOWN, -1, "Неверное значение элемента input после уменьшения");
    }

    private void validateInputChange(int inputValue, Keys key, int delta, String message) {
        WebElement input = driver.findElement(By.tagName("input"));
        input.clear();
        input.sendKeys(String.valueOf(inputValue));
        input.sendKeys(key);
        Assert.assertEquals(input.getAttribute("value"), String.valueOf(inputValue + delta), message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}