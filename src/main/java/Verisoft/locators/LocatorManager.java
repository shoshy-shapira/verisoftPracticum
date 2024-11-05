package Verisoft.locators;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class LocatorManager {
    private Map<String, By> locators = new HashMap<>();

    public void addLocator(String name, By locator) {
        locators.put(name, locator);
    }

    public By getLocator(String name) {
        if (locators.containsKey(name)) {
            return locators.get(name);
        } else {
            throw new IllegalArgumentException("Locator not found for: " + name);
        }
    }
    public void removeLocator(String name) {
        locators.remove(name);
    }
}
