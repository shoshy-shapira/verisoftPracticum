package Verisoft.LIfeCycle;

import Verisoft.Logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestResultWatcher implements TestWatcher {
private static final Logger logger= LoggerManager.getLogger(TestResultWatcher.class);

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        String testName = context.getDisplayName();
        String disableReason = reason.orElse("No reason provided");
        logger.warn("Test '" + testName + "' was disabled. Reason: " + disableReason);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        logger.info("Test '" + testName + "' passed successfully.");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        logger.warn("Test '" + testName + "' was aborted with exception: " + cause.getMessage(), cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        logger.error("Test '" + testName + "' failed with exception: " + cause.getMessage(), cause);
    }


}
