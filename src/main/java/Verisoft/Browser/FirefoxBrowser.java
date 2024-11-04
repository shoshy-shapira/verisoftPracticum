package Verisoft.Browser;

import Verisoft.Screenshot.ScreenshotManager;
import Verisoft.Wait.WaitHandler;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;


@Slf4j
public class FirefoxBrowser implements Browser {
    private WebDriver driver; // WebDriver instance for controlling Firefox
    private final WaitHandler wait; // To manage waits for elements and page loading
    private static final String SCREENSHOTS_DIR = "test-output/screenshots/";
    private ScreenshotManager screenshotManager; // ScreenshotManager for handling screenshots

    public FirefoxBrowser(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHandler(driver);
        this.screenshotManager = new ScreenshotManager();// This will create the directory
    }

    @Override
    public String takeScreenshot(String fileName) {
        return screenshotManager.takeScreenshot(this, fileName);
    }


    @Override
    public void open(String url) {
        try {
            log.info("Opening URL: {}", url);
            driver.get(url); // Open the specified URL
            wait.waitForPageLoad(); // Use WaitHandler for waiting
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

    @Override
    public WebDriver getDriver() {
        return driver; // Return the current WebDriver instance
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
