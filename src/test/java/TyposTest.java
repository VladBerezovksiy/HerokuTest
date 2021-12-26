import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TyposTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    String expectedResult = "Sometimes you'll see a typo, other times you won't.";

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/typos");
    }

    @Test
    public void typosTest() {
        validateTyposIsCorrect(expectedResult);
    }

    private void validateTyposIsCorrect(String expectedResult) {
        validateTyposChange(expectedResult);
    }

    private void validateTyposChange(String expectedResult) {
        List<WebElement> typos = driver.findElements(By.tagName("p"));
        Assert.assertEquals(
                typos.get(1).getText(),
                expectedResult, "Неверная сравнение орфографии c ожидаемым результатом");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}