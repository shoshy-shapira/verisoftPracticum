package Verisoft.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitHandler class implementing the Waiter interface for handling WebDriver waits.
 */
public class WaitHandler implements Waiter {
    private final WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 30;

    public WaitHandler(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public WebElement waitForElementVisible(WebElement element, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebElement waitForElementClickable(WebElement element, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void waitForOverlayToDisappearIfPresent(By overlayLocator, int timeoutInSeconds) {
        List<WebElement> overlays = driver.findElements(overlayLocator);
        if (!overlays.isEmpty() && overlays.get(0).isDisplayed()) {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
        }
    }

    /**
     * Executes a JavaScript command in the context of the currently selected frame or window.
     *
     * @param script The JavaScript code to be executed.
     * @return The result of the executed script.
     */
    public Object executeScript(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver; // Cast driver to JavascriptExecutor
        return js.executeScript(script); // Execute the JavaScript and return the result
    }

}
