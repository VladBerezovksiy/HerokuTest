import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DropDownTest {

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    String valueOption = "Option";

    @BeforeClass
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void dropDownTest() {
        validateDropDownIsEmpty();
        validateDropDownFirstOption(valueOption);
        validateDropDownSecondOption(valueOption);
    }

    private void validateDropDownIsEmpty() {
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Select dropDownSelect = new Select(dropDown);
        Assert.assertFalse(dropDownSelect.getAllSelectedOptions().isEmpty());
    }

    private void validateDropDownFirstOption(String valueOption) {
        validateDropDownChange(valueOption, " 1", "Неверно выбрано первое значение");
    }

    private void validateDropDownSecondOption(String valueOption) {
        validateDropDownChange(valueOption, " 2","Неверно выбрано второе значение");
    }

    private void validateDropDownChange(String valueOption, String numberOption, String message) {
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Select dropDownSelect = new Select(dropDown);
        dropDownSelect.getAllSelectedOptions().clear();
        dropDownSelect.selectByVisibleText(valueOption + numberOption);
        Assert.assertTrue(dropDownSelect.getFirstSelectedOption().isSelected());
        Assert.assertEquals(dropDownSelect.getFirstSelectedOption().getText(), valueOption + numberOption, message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}