package Verisoft.Browser;

import Verisoft.Wait.WaitHandler;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


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

    public Browser createBrowser(String browserTypeStr) {
        if (browserTypeStr == null || browserTypeStr.isEmpty()) {
            browserTypeStr = config.getProperty("browser.type", "chrome").toLowerCase();
        }
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

