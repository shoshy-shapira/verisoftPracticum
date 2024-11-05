package Verisoft.elementActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputActions {
    private final WebDriver driver;

    public InputActions(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

}
