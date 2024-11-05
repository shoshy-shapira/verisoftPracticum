package Verisoft.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A utility class that provides a simplified interface for obtaining Log4j2 loggers.
 * This class serves as a wrapper around Log4j2's LogManager to standardize logger creation
 * across the Verisoft framework.
 *
 * <p>This manager class provides a single static method to obtain a logger instance
 * for a specific class. It centralizes logger creation and makes it easier to modify
 * logging configuration in one place if needed.
 *
 * <p>Usage example:
 * <pre>
 * private static final Logger logger = LoggerManager.getLogger(MyClass.class);
 * logger.info("This is a log message");
 * </pre>
 */
public class LoggerManager {

    /**
     * Returns a Logger instance for the specified class.
     *
     * @param clazz The class requiring the logger instance
     * @return A Logger instance configured for the specified class
     */
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
