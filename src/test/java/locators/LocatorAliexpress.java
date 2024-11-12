package locators;

import Verisoft.locators.LocatorManager;
import org.openqa.selenium.By;

public class LocatorAliexpress {
    // Add locators to LocatorManager
    public static void loadLocators(LocatorManager locatorManager) {
        locatorManager.addLocator("linkAliexpress", By.xpath("//*[@id=\"_full_container_header_23_\"]/div/a"));
        locatorManager.addLocator("buttonSearch", By.className("search--submit--2VTbd-T"));
        locatorManager.addLocator("search", By.xpath("//*[@id=\"search-words\"]"));
    }
}
