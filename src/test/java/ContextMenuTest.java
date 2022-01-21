import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenuTest {

    protected WebDriver driver;
    protected ChromeOptions chromeOptions;
    protected Actions actions;
    protected Alert alert;

    @BeforeMethod
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/context_menu");
        actions = new Actions(driver);
    }

    @Test
    public void contextMenuTest() {
        rightClickOnBox();
        validateToGetTextFromAlert();
    }

    private void rightClickOnBox() {
        WebElement box = driver.findElement(By.id("hot-spot"));
        actions.contextClick(box).perform();
    }

    private void validateToGetTextFromAlert() {
        alert = driver.switchTo().alert();
        Assert.assertEquals("You selected a context menu", alert.getText(), "Alert message is not validate");
        alert.accept();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
