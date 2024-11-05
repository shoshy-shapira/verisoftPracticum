package Verisoft.Browser;

import Verisoft.Wait.WaitHandler;
import Verisoft.config.ConfigReader;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BrowserConfig {
    private final ConfigReader config;
    private final WaitHandler waiter;

    /**
     * Constructor for BrowserConfig.
     *
     * @param waiter an instance of WaitHandler to handle wait operations.
     */
    public BrowserConfig(WaitHandler waiter) {
        this.config = ConfigReader.getInstance();
        this.waiter = waiter;
    }

    public Browser createBrowser() {
        // Retrieve the browser type from the configuration file, defaulting to "chrome" if not specified, and convert to lowercase.
        String browserTypeStr = config.getProperty("browser.type", "chrome").toLowerCase();
        //Convert the browser type string to the corresponding BrowserType enum constant, handling case insensitivity.
        BrowserType browserType = BrowserType.valueOf(browserTypeStr.toLowerCase());
        log.info("Creating browser instance of type: {}", browserType);
        try {
            Browser browser = BrowserFactory.createDriver(browserType);
            String url = config.getProperty("test.url");
            browser.open(url);
            return browser;
        } catch (Exception e) {
            throw new AutomationException("Failed to initialize browser", e);

        }
    }
}

