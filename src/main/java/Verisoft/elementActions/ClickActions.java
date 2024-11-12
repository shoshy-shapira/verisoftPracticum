package Verisoft.elementActions;

import Verisoft.Wait.WaitHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickActions {
    private final WebDriver driver;
    private final WaitHandler waitHandler;


    public ClickActions(WebDriver driver, WaitHandler waitHandler) {
        this.driver = driver;
        this.waitHandler = waitHandler;

    }

    public void click(By locator) {
        WebElement element = driver.findElement(locator);
        WebElement clickableElement = waitHandler.waitForElementClickable(element, 10);
        element.click();
    }

    public void doubleClick(By locator) {
    }
}
