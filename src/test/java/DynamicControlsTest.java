import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicControlsTest {

    protected WebDriver driver;
    protected ChromeOptions chromeOptions;
    protected WebDriverWait wait;
    protected Actions actions;

    @BeforeMethod
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test
    public void dynamicControlsTest() {
        validateCheckbox();
        validateInput();
    }

    private void validateCheckbox() {
        WebElement checkBox = driver.findElement(By.xpath("//form[@id='checkbox-example']//input"));

        WebElement buttonRemove = driver.findElement(By.xpath("//form[@id='checkbox-example']//button"));
        buttonRemove.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='checkbox-example']//p")));

        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkBox)), "Checkbox is visible");
    }

    private void validateInput() {
        WebElement inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));
        Assert.assertFalse(inputField.isEnabled(), "Input field is enable");

        WebElement buttonEnable = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        buttonEnable.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='input-example']//p")));

        Assert.assertTrue(inputField.isEnabled(), "Input field is Disable");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
