import Verisoft.Browser.*;
import Verisoft.ErrorHandler.ErrorHandler;
import Verisoft.Screenshot.ScreenshotManager;
import org.junit.jupiter.api.*;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import Verisoft.Logger.LoggerManager;

import static org.junit.jupiter.api.Assertions.*;


public class BrowserTests {
    private  Browser browser;
    private static String url;
    private static BrowserType browserType;
    private static final Logger logger = LoggerManager.getLogger(BrowserTests.class);
    private ErrorHandler errorHandler;
    private ScreenshotManager screenshotManager;


    @BeforeAll
    static void loadConfig() {
        System.setProperty("log4j2.configurationFile", "log4j2.xml");
        logger.info("Loading configuration...");

        // Load the configuration properties once before all tests
        Properties config = new Properties();
        try (InputStream input = BrowserTests.class.getClassLoader().getResourceAsStream("config.properties")) {
            config.load(input);
            url = config.getProperty("browser.url", "https://www.google.com"); // Default to Google if not set
            String browserTypeStr = config.getProperty("browser.type", "chrome").toUpperCase();
            browserType = BrowserType.valueOf(browserTypeStr);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    @BeforeEach
    void setUp() {
        logger.info("Setting up browser...");

        // Initialize ScreenshotManager and ErrorHandler
        screenshotManager = new ScreenshotManager();
        errorHandler = new ErrorHandler(screenshotManager);

        // Initialize the browser using the factory and maximize
        browser = BrowserFactory.createDriver(browserType);
        browser.maximize(); // Maximize the browser window at the start
    }

    @AfterEach
    void tearDown() {
        if (browser != null) {
            browser.quit();
        }
        browser = null;
        logger.info("Closing browser...");


    }
    @Test
    void testOpenUrl() {
        logger.info("Opening URL: " + url);
        try {
            browser.open(url);
            assertEquals("Gogle", browser.getDriver().getTitle());
            logger.info("Tested URL opening successfully.");
        }
        catch (AssertionError ae) {
            // Handle assertion errors specifically
            errorHandler.handleSpecificThrowable(ae, browser);
            fail("Assertion failed while testing URL opening: " + ae.getMessage());
        } catch (Exception e)
        {
            // Handle error using ErrorHandler if an exception occurs
            errorHandler.handleSpecificThrowable(e, browser);
            fail("Exception occurred while testing URL opening: " + e.getMessage());

        }

    }


}


