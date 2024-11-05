package Verisoft.elementActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementActions {
    private final ClickActions clickActions;
    private final InputActions inputActions;
    private final ScrollActions scrollActions;

    public ElementActions(WebDriver driver) {
        this.clickActions = new ClickActions(driver);
        this.inputActions = new InputActions(driver);
        this.scrollActions = new ScrollActions(driver);

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
