package Verisoft.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Browser {
    /**
     * Opens the specified URL in the browser.
     * @param url the URL to open
     * @throws AutomationException if unable to open the URL
     */
    void open(String url);

    /**
     * Maximizes the browser window.
     * @throws AutomationException if unable to maximize the window
     */
    void maximize();

    /**
     * Quits the browser and releases all associated resources.
     */
    void quit();

    /**
     * Returns the WebDriver instance for further operations.
     * @return the WebDriver instance used for browser interactions
     */
    WebDriver getDriver();

    /**
     * Takes a screenshot of the current page
     * @param fileName name for the screenshot file
     * @return Path to the saved screenshot
     * @throws AutomationException if unable to take screenshot
     */
    String takeScreenshot(String fileName);

    /**
     * Clears browser cookies and cache
     */
    void clearBrowserData();

}

