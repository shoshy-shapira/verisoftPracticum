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

    public ScreenshotManager() {
        createScreenshotsDirectory();
    }

    private void createScreenshotsDirectory() {
        File directory = new File(SCREENSHOTS_DIR);
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
