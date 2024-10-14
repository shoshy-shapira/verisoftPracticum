package Verisoft.General.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * The BrowserConfig class is responsible for setting up the
 * WebDriver environment for browser automation.
 * It configures the WebDriver for the Chrome browser
 * using WebDriverManager, which handles driver binaries
 * automatically.
 */

public class BrowserConfig {
    /**
     * Sets up the WebDriver for Chrome by using WebDriverManager.
     * This method will ensure that the appropriate version of
     * the ChromeDriver is downloaded and set up for use
     * with Selenium WebDriver.
     */
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

}
