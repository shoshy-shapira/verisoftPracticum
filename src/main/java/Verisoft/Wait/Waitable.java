package Verisoft.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Waitable {
    /**
     * Waits for the current page to fully load.
     * This method checks the document's ready state and waits
     * until the state is "complete" before proceeding.
     */
    void waitForPageLoad();

    /**
     * Waits for the specified element to be visible within the given timeout period.
     * @param element the WebElement to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become visible, in seconds
     * @return the WebElement once it is visible
     */
    WebElement waitForElement(WebElement element, int timeoutInSeconds);

    /**
     * Waits for the specified element to be clickable within the given timeout period.
     * @param element the WebElement to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become clickable, in seconds
     * @return the WebElement once it is clickable
     */
    WebElement waitForElementClickable(WebElement element, int timeoutInSeconds);

    /**
     * Waits for an overlay to disappear if it is present.
     * @param overlayLocator the locator of the overlay to wait for
     * @param timeoutInSeconds the number of seconds to wait before the operation fails
     */
    void waitForOverlayToDisappearIfPresent(By overlayLocator, int timeoutInSeconds);

    /**
     * Waits for the specified element to be visible using the default timeout.
     * @param element the WebElement to wait for
     */
    void waitForElement(WebElement element);

}
