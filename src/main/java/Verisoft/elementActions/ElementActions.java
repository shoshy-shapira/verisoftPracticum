package Verisoft.elementActions;

import Verisoft.Wait.WaitHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementActions {
    private final ClickActions clickActions;
    private final InputActions inputActions;
    private final ScrollActions scrollActions;
    private final WaitHandler waitHandler;

    public ElementActions(WebDriver driver, WaitHandler waitHandler) {
        this.clickActions = new ClickActions(driver,waitHandler);
        this.inputActions = new InputActions(driver);
        this.scrollActions = new ScrollActions(driver);
        this.waitHandler = waitHandler;

    }
    public void clickElement(By lactor){
        clickActions.click(lactor);
    }
    public void enterText(By locator, String text) {
        inputActions.enterText(locator, text);
    }

    public void scrollTo(By locator) {
        scrollActions.scrollToElement(locator);
    }

}
