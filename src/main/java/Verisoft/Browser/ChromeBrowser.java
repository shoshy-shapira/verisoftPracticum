package Verisoft.General.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * The ChromeBrowser class implements the Browser interface, providing
 * specific functionalities for controlling the Chrome browser
 */
public class ChromeBrowser implements Browser {
    private WebDriver driver; // The WebDriver instance for controlling the browser
    private WebDriverWait wait; // The WebDriverWait instance for managing waits

    @Override
    /**
     * Opens the specified URL in the Chrome browser.
     *
     * @param url the URL to open
     */
    public void open(String url) {
        driver = new ChromeDriver(); // Initialize the ChromeDriver
        driver.get(url); // Open the specified URL
    }

    @Override
    /**
     * Maximizes the current browser window.
     */
    public void maximize() {
        driver.manage().window().maximize(); // Maximize the browser window
    }

    @Override
    /**
     * Quits the browser and releases all associated resources.
     */
    public void quit() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }

    @Override
    /**
     * Returns the WebDriver instance used for browser interactions.
     * @return the WebDriver instance
     */
    public WebDriver getDriver() {
        return driver; // Return the current WebDriver instance
    }

    /**
     * Waits for the current page to fully load.
     * This method checks the document's ready state using JavaScript and waits
     * until the state is "complete" before proceeding. The wait time is set to
     * a maximum of 10 seconds.

     * @throws TimeoutException if the page does not load completely within the specified timeout
     */
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    /**
     * Waits for the specified element to be visible within the given timeout period.
     * @param element          the WebElement to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become visible, in seconds
     * @return the WebElement once it is visible
     * @throws TimeoutException if the element does not become visible within the specified timeout
     */
    public WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element)); // Wait for the element to be visible
    }

    /**
     * Waits for the specified element to be clickable within the given timeout period.
     * @param element          the WebElement to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become clickable, in seconds
     * @return the WebElement once it is clickable
     * @throws TimeoutException if the element does not become clickable within the specified timeout
     */
    public WebElement waitForElementClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait for the element to be clickable
    }

    /**
     * Waits for the overlay to disappear if it is present.
     * @param overlayLocator   the locator of the overlay to wait for
     * @param timeoutInSeconds the number of seconds to wait before the operation fails
     */
    public void waitForOverlayToDisappearIfPresent(By overlayLocator, int timeoutInSeconds) {
        // Finds all elements that match the overlay locator
        List<WebElement> overlays = driver.findElements(overlayLocator);

        // Checks if at least one overlay is displayed
        if (!overlays.isEmpty() && overlays.get(0).isDisplayed()) {
            // Creates a WebDriverWait instance to wait until the overlay disappears
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator)); // Wait for the overlay to disappear
        }
    }

    /**
     * Waits for the specified element to be visible using the default timeout.
     * @param element the WebElement to wait for
     */
    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)); // Wait for the element to be visible
    }
}
