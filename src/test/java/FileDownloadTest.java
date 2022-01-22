import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class FileDownloadTest {

    protected WebDriver driver;
    protected ChromeOptions chromeOptions;
    private static final String FILE_NAME = "demo1.txt";
    private static final String FILE_PATH = "src/test/resources";
    private static final File RESOURCE_PATH_FILE = new File(FILE_PATH);
    private static final String ABSOLUTE_RESOURCE_PATH = RESOURCE_PATH_FILE.getAbsolutePath();

    @BeforeMethod
    public void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", ABSOLUTE_RESOURCE_PATH);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/download");
    }

    @Test
    public void fileDownloadTest() throws InterruptedException {
        String testFileName = "demo1.txt";
        actionsToDownload();
        Assert.assertTrue(checkFileDownloaded(testFileName), "Downloaded document is not found");
    }

    private void actionsToDownload() {
        driver.findElement(By.partialLinkText(FILE_NAME)).click();
    }

    private boolean checkFileDownloaded(String testFileName) throws InterruptedException {
        boolean found = false;
        for (int i = 0; i < 10; i++) {
            File folder = new File(ABSOLUTE_RESOURCE_PATH);
            File[] listOfFiles = folder.listFiles();
            File f = null;
            if (listOfFiles != null) {
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        String fileName = listOfFile.getName();
                        System.out.println("File " + listOfFile.getName());
                        if (fileName.matches(testFileName)) {
                            f = new File(fileName);
                            found = true;
                        }
                    }
                }
            }
            if (found) {
                break;
            }
            Thread.sleep(1000);
        }
        return found;
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
