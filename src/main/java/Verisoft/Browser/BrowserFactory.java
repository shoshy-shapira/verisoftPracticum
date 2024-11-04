package Verisoft.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

@Slf4j
// Factory class for creating WebDriver instances
public class BrowserFactory {
    private static final Map<String, WebDriver> drivers = new HashMap<>();

    public static Browser createDriver(BrowserType browserType) {
        log.info("Creating new {} driver instance", browserType);
        try {
            Browser browser = switch (browserType) {
                case CHROME -> new ChromeBrowser(createChromeDriver());
                case FIREFOX -> new FirefoxBrowser(createFirefoxDriver());
                case EDGE -> new EdgeBrowser(createEdgeDriver());
                default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);

            };
            drivers.put(browserType.name(), browser.getDriver());
            return browser;
        } catch (Exception e) {
            throw new AutomationException("Failed to create driver for " + browserType, e);
        }


    }


    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        // Add Edge-specific options here
        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        // Add Firefox-specific options here
        return new FirefoxDriver(options);

    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
        }

        return new ChromeDriver(options);
    }
}


