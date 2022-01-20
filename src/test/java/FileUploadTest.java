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

public class FileUploadTest {

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
        driver.navigate().to("http://the-internet.herokuapp.com/upload");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test
    public void fileUploadTest() {
        validateUploadFilesAndSubmit();
        validateFileName();
    }

    private void validateUploadFilesAndSubmit() {
        String path = "C:\\Users\\vlad_\\Intel IDEA\\SDK\\src\\test\\resources\\index.html";
        WebElement fileUpload = driver.findElement(By.id("file-upload"));
        fileUpload.sendKeys(path);

        WebElement button = driver.findElement(By.id("file-submit"));
        button.click();
    }

    private void validateFileName() {
        WebElement fileName = driver.findElement(By.id("uploaded-files"));
        Assert.assertEquals(fileName.getText(), "index.html", "Files aren't match names");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
