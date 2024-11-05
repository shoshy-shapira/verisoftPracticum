package Verisoft.Screenshot;

import Verisoft.Browser.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotManager {

    private static final String SCREENSHOTS_DIR = "test-output/screenshots/";
    private static final String ERROR_SCREENSHOTS_DIR = SCREENSHOTS_DIR + "errors/";

    public ScreenshotManager() {
        createScreenshotsDirectory(SCREENSHOTS_DIR);
        createScreenshotsDirectory(ERROR_SCREENSHOTS_DIR);
    }
    // Getter for ERROR_SCREENSHOTS_DIR
    public String getErrorScreenshotsDir() {
        return ERROR_SCREENSHOTS_DIR;
    }
    private void createScreenshotsDirectory(String nameFile) {
        File directory = new File(nameFile);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public String takeScreenshot(Browser browser, String fileName) {
       WebDriver driver=browser.getDriver();
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = SCREENSHOTS_DIR + fileName + "_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
            return destination;
        } catch (IOException e) {
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }

}
