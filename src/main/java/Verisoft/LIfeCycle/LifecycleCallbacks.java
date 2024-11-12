package Verisoft.LIfeCycle;

import Verisoft.Logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.*;


public class LifecycleCallbacks implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterAllCallback {
    private static final Logger logger = LoggerManager.getLogger(LifecycleCallbacks.class);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        logger.info("Starting test suite: " + context.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        logger.info("Starting test: " + context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        logger.info("Finished test: " + context.getDisplayName());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        logger.info("Finished test suite: " + context.getDisplayName());

    }
    }