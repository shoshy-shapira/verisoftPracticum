package Verisoft.Browser;

import Verisoft.Screenshot.ScreenshotManager;
import Verisoft.Wait.WaitHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
public class ChromeBrowser implements Browser {
    private WebDriver driver; // The WebDriver instance for controlling the browser
    private final WaitHandler wait; // Use a separate waiter class
    private static final String SCREENSHOTS_DIR = "test-output/screenshots/";
    private ScreenshotManager screenshotManager; // ScreenshotManager for handling screenshots

    public ChromeBrowser(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHandler(driver);
        this.screenshotManager = new ScreenshotManager();

    }


    @Override
    public void open(String url) {
        try {
            log.info("Opening URL: {}", url);
            driver.get(url);// Open the specified URL
            wait.waitForPageLoad();
        } catch (Exception e) {
            throw new AutomationException("Failed to open URL: " + url, e);
        }
    }


    @Override
    public void maximize() {
        try {
            log.info("Maximizing browser window");
            driver.manage().window().maximize(); // Maximize the browser window

        } catch (Exception e) {
            throw new AutomationException("Failed to maximize browser window", e);

        }
    }

    @Override
    public void quit() {
        try {
            if (driver != null) {
                log.info("Quitting browser");
                driver.quit(); // Close the browser
                driver = null;
            }
        } catch (Exception e) {
            log.info("Error while quitting browser", e);
        }
    }

    /**
     * Opens the specified URL in the Chrome browser.
     */
    @Override
    public WebDriver getDriver() {

        return driver; // Return the current WebDriver instance
    }

    @Override
    public String takeScreenshot(String fileName) {
        return screenshotManager.takeScreenshot(this, fileName);
    }

    @Override
    public void clearBrowserData() {
        try {
            log.info("Clearing browser data");
            driver.manage().deleteAllCookies();
            wait.executeScript("window.localStorage.clear();");
            wait.executeScript("window.sessionStorage.clear();");
        } catch (Exception e) {
            throw new AutomationException("Failed to clear browser data", e);
        }
    }


}
