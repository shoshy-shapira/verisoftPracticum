package Verisoft.elementActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickActions {
    private final WebDriver driver;

    public ClickActions(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void doubleClick(By locator) {
    }
}
