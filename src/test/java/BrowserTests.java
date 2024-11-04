import Verisoft.Browser.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BrowserTests {

    private WebDriver chromeDriver;
    // private WebDriver firefoxDriver;
    private WebDriver edgeDriver;

    private ChromeBrowser chromeBrowser;
    //private FirefoxBrowser firefoxBrowser;
    private EdgeBrowser edgeBrowser;

    @BeforeEach
    void setUp() {
        try {
            // Use WebDriverManager instead of System.setProperty
            WebDriverManager.chromedriver().setup();
            chromeDriver = new ChromeDriver();
            chromeBrowser = new ChromeBrowser(chromeDriver);

//            WebDriverManager.firefoxdriver().setup();
//            firefoxDriver = new FirefoxDriver();
//            firefoxBrowser = new FirefoxBrowser(firefoxDriver);

            WebDriverManager.edgedriver().setup();
            edgeDriver = new EdgeDriver();
            edgeBrowser = new EdgeBrowser(edgeDriver);

        } catch (Exception e) {
            tearDown(); // Ensure cleanup if initialization fails
            throw e;
        }
    }

    @AfterEach
    void tearDown() {
        // Safely quit each driver
        if (chromeDriver != null) {
            try {
                chromeDriver.quit();
            } catch (Exception e) {
                // Log the exception but continue cleanup
                e.printStackTrace();
            }
        }

//        if (firefoxDriver != null) {
//            try {
//                firefoxDriver.quit();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        if (edgeDriver != null) {
            try {
                edgeDriver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Set all references to null
        chromeDriver = null;
        // firefoxDriver = null;
        edgeDriver = null;
        chromeBrowser = null;
        // firefoxBrowser = null;
        edgeBrowser = null;
    }

    /**
     * Tests the ability to open a URL in various browsers.
     *
     * <p>This is a parameterized test that runs the same test logic for each
     * browser type defined in the {@link BrowserType} enum. It creates an instance
     * of the specified browser, opens the Google homepage, and asserts that the
     * title of the page is "Google". This ensures that the browser correctly
     * navigates to the expected URL and retrieves the correct page title.</p>
     *
     * @param browserType The type of browser to test, provided by
     *                    {@link EnumSource} using the {@link BrowserType} enum.
     */

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    // משתמש בערכי enum של BrowserType
    void testOpenUrlInBrowser(BrowserType browserType) {
        Browser browser = BrowserFactory.createDriver(browserType);
        browser.open("https://www.google.com");
        assertEquals("Google", browser.getDriver().getTitle());
    }

    @Test
    void testOpenUrlInAllBrowsers() {
        List<Browser> browsers = Arrays.asList(chromeBrowser, edgeBrowser);

        for (Browser browser : browsers) {
            System.out.println("Testing in: " + browser.getClass());
            browser.open("https://www.google.com");
            String title = browser.getDriver().getTitle();
            assertEquals("Google", title);
        }
    }

    @Test
    void testOpenUrlInChrome() {
        chromeBrowser.open("https://www.google.com");
        assertEquals("Google", chromeBrowser.getDriver().getTitle());
    }

//    @Test
//    void testOpenUrlInFirefox() {
//        firefoxBrowser.open("https://www.example.com");
//        assertEquals("Example Domain", firefoxBrowser.getDriver().getTitle());
//    }

    @Test
    void testOpenUrlInEdge() {
        edgeBrowser.open("https://www.google.com");
        assertEquals("Google", edgeBrowser.getDriver().getTitle());
    }

    @Test
    void testMaximizeChrome() {
        chromeBrowser.maximize();
        // Additional assertions can be made here if needed
    }

//    @Test
//    void testMaximizeFirefox() {
//        firefoxBrowser.maximize();
//        // Additional assertions can be made here if needed
//    }

    @Test
    void testMaximizeEdge() {
        edgeBrowser.maximize();
        // Additional assertions can be made here if needed
    }

    @Test
    void testTakeScreenshotInChrome() {
        String screenshotPath = chromeBrowser.takeScreenshot("chrome_screenshot");
        assertTrue(screenshotPath.endsWith(".png"));
    }

//    @Test
//    void testTakeScreenshotInFirefox() {
//        String screenshotPath = firefoxBrowser.takeScreenshot("firefox_screenshot");
//        assertTrue(screenshotPath.endsWith(".png"));
//    }

    @Test
    void testTakeScreenshotInEdge() {
        String screenshotPath = edgeBrowser.takeScreenshot("edge_screenshot");
        assertTrue(screenshotPath.endsWith(".png"));
    }
}

