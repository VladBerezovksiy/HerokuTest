import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HoversTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    String expectedResult = "Not Found";
    String[] nameDB = {"name: user1", "name: user2", "name: user3"};

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/hovers");
    }

    @Test
    public void hoversTest() {
        String Url = driver.getCurrentUrl();
        Actions actions = new Actions(driver);
        List<WebElement> image = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/img"));

        validateHoverOnAvatarAndCheckName(actions, image);
        validateCheckLinksIsEnable(actions, Url, image);
        validateCheckTextErrorIsCorrect(actions, image);
    }

    private void validateHoverOnAvatarAndCheckName(Actions actions, List<WebElement> image) {
        List<WebElement> name = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/h5"));
        for (int i = 0; i < image.size(); i++) {
            actions.moveToElement(image.get(i)).build().perform();
            Assert.assertEquals((name.get(i)).getText(), nameDB[i], "Неверное имя");
        }
    }

    private void validateCheckLinksIsEnable(Actions actions, String Url, List<WebElement> image) {
        for (int i = 0; i < image.size(); i++) {
            WebElement avatar = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/img")).get(i);
            WebElement link = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/a")).get(i);
            actions.moveToElement(avatar).perform();
            link.click();
            String userUrl = driver.getCurrentUrl();
            Assert.assertNotSame(userUrl, Url, "Не работает ссылка на профиль");
            navigateBack();
        }
    }

    private void validateCheckTextErrorIsCorrect(Actions actions, List<WebElement> image) {
        for (int i = 0; i < image.size(); i++) {
            WebElement avatar = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/img")).get(i);
            WebElement link = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/a")).get(i);
            actions.moveToElement(avatar).perform();
            link.click();
            WebElement error = driver.findElement(By.xpath("/html/body/h1"));
            Assert.assertEquals(error.getText(), expectedResult, "Не соответствует текст ошибки");
            navigateBack();
        }
    }

    private void navigateBack() {
        driver.navigate().back();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
