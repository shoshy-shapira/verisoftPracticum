package Verisoft.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

    /**
     * Waiter interface defining methods for various wait operations in WebDriver.
     */
    public interface Waiter {
        /**
         * Waits for the page to load completely.
         *
         * @throws AutomationException if the page doesn't load within the default timeout.
         */
        void waitForPageLoad();

        /**
         * Waits for the specified element to be visible.
         *
         * @param element          WebElement to wait for.
         * @param timeoutInSeconds Custom timeout in seconds.
         * @return WebElement once visible.
         * @throws AutomationException if the element doesn't become visible within timeout.
         */
        WebElement waitForElementVisible(WebElement element, int timeoutInSeconds);

        /**
         * Waits for the specified element to be clickable.
         *
         * @param element          WebElement to wait for.
         * @param timeoutInSeconds Maximum time to wait.
         * @return WebElement once clickable.
         * @throws AutomationException if the element doesn't become clickable within timeout.
         */
        WebElement waitForElementClickable(WebElement element, int timeoutInSeconds);

        /**
         * Waits for the overlay to disappear if present.
         *
         * @param overlayLocator   Locator for the overlay element.
         * @param timeoutInSeconds Maximum time to wait.
         * @throws AutomationException if the overlay doesn't disappear within timeout.
         */
        void waitForOverlayToDisappearIfPresent(By overlayLocator, int timeoutInSeconds);
    }


