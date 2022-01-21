import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class NotificationMessagesTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    String expectedSuccessfulResult = "Action successful\n×";
    String expectedUnsuccessfulResult = "Action unsuccessful, please try again\n×";

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
    }

    @Test
    public void notificationTest() {
        Actions actions = new Actions(driver);
        validateClickOnLink(actions);
    }

    private void validateClickOnLink(Actions actions) {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p/a"));
        actions.moveToElement(link).click().perform();
        WebElement notification = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        String actualNotificationText = notification.getText();
        validateCheckOnNotification(expectedSuccessfulResult, expectedUnsuccessfulResult, actualNotificationText);
    }

    private void validateCheckOnNotification(String expectedSuccessfulResult, String expectedUnsuccessfulResult, String actualNotificationText) {
        Assert.assertEquals(actualNotificationText,
                expectedSuccessfulResult, "Неверное соответствие текста уведомления на успешность");
        Assert.assertEquals(actualNotificationText,
                expectedUnsuccessfulResult, "Неверное соответствие текста уведомления на не успешность");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}