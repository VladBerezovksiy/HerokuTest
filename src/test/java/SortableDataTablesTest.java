import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SortableDataTablesTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/tables");
    }

    @Test
    public void sortableDataTablesTest() {
        validateFirstRowOfDataTables();
        validateSecondRowOfDataTables();
        validateThirdRowOfDataTables();
        validateFourthRowOfDataTables();
        validateFifthRowOfDataTables();
    }

    private void validateFirstRowOfDataTables() {
        List<WebElement> dataOfFirstRowInFirstTable = driver.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));
        List<WebElement> dataOfFirstRowInSecondTable = driver.findElements(By.xpath("//*[@id=\"table2\"]/thead/tr/th"));
        for (int i = 0; i < dataOfFirstRowInFirstTable.size(); i++) {
            Assert.assertEquals(dataOfFirstRowInFirstTable.get(i).getText(), dataOfFirstRowInSecondTable.get(i).getText(),
                    "Не соответствуют данные первого ряда в таблицах");
        }
    }

    private void validateSecondRowOfDataTables() {
        List<WebElement> dataOfSecondRowInFirstTable = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td"));
        List<WebElement> dataOfSecondRowInSecondTable = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr[1]/td"));
        for (int i = 0; i < dataOfSecondRowInFirstTable.size(); i++) {
            Assert.assertEquals(dataOfSecondRowInFirstTable.get(i).getText(), dataOfSecondRowInSecondTable.get(i).getText(),
                    "Не соответствуют данные второго ряда в таблицах");
        }
    }

    private void validateThirdRowOfDataTables() {
        List<WebElement> dataOfThirdRowInFirstTable = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr[2]/td"));
        List<WebElement> dataOfThirdRowInSecondTable = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr[2]/td"));
        for (int i = 0; i < dataOfThirdRowInFirstTable.size(); i++) {
            Assert.assertEquals(dataOfThirdRowInFirstTable.get(i).getText(), dataOfThirdRowInSecondTable.get(i).getText(),
                    "Не соответствуют данные третьего ряда в таблицах");
        }
    }

    private void validateFourthRowOfDataTables() {
        List<WebElement> dataOfFourthRowInFirstTable = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr[3]/td"));
        List<WebElement> dataOfFourthRowInSecondTable = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr[3]/td"));
        for (int i = 0; i < dataOfFourthRowInFirstTable.size(); i++) {
            Assert.assertEquals(dataOfFourthRowInFirstTable.get(i).getText(), dataOfFourthRowInSecondTable.get(i).getText(),
                    "Не соответствуют данные четвертого ряда в таблицах");
        }
    }

    private void validateFifthRowOfDataTables() {
        List<WebElement> dataOfFifthRowInFirstTable = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr[4]/td"));
        List<WebElement> dataOfFifthRowInSecondTable = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr[4]/td"));
        for (int i = 0; i < dataOfFifthRowInFirstTable.size(); i++) {
            Assert.assertEquals(dataOfFifthRowInFirstTable.get(i).getText(), dataOfFifthRowInSecondTable.get(i).getText(),
                    "Не соответствуют данные пятого ряда в таблицах");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
