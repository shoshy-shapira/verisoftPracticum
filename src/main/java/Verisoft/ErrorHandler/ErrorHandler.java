package Verisoft.ErrorHandler;

import Verisoft.Browser.Browser;
import Verisoft.Logger.LoggerManager;
import Verisoft.Screenshot.ScreenshotManager;
import org.apache.logging.log4j.Logger;

public class ErrorHandler {
    private static final Logger logger = LoggerManager.getLogger(ErrorHandler.class);
    private final ScreenshotManager screenshotManager;

    public ErrorHandler(ScreenshotManager screenshotManager) {
        this.screenshotManager = screenshotManager;
    }


    /**
     * Handles a specific throwable (error or exception) by logging it and taking a screenshot.
     *
     * @param throwable the throwable that occurred
     * @param browser   the browser instance used for taking the screenshot
     */
    public void handleSpecificThrowable(Throwable throwable, Browser browser) {
        logError(throwable); // רישום השגיאה ביומן
        takeScreenshotOnError(browser,throwable);
    }
    /**
     * Logs the error with detailed information.
     *
     * @param throwable the exception that occurred
     */
    private void logError(Throwable throwable) {
        logger.error("An error occurred: " + throwable.getMessage(),throwable);
    }

    /**
     * Takes a screenshot on error using ScreenshotManager.
     *
     * @param browser the browser instance used for taking the screenshot
     */
    private void takeScreenshotOnError(Browser browser, Throwable throwable) {
        String fileName = "error_" + throwable.getClass().getSimpleName();
        try {
            String screenshotPath = screenshotManager.takeScreenshot(browser, screenshotManager.getErrorScreenshotsDir()+fileName);
            logger.info("Screenshot saved at: " + screenshotPath);
        } catch (Exception e) {
            logger.error("Failed to take screenshot on error: " + e.getMessage());
        }
    }
}

