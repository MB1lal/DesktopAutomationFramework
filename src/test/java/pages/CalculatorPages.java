package pages;


import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;

public class CalculatorPages {

    private WindowsDriver windowsDriver;

    String num1Btn = "num1Button";

    By num9Btn = By.name("Nine");

    By plusBtn = By.name("Plus");

    By equalsBtn = By.name("Equals");

    String results = "CalculatorResults";

    public CalculatorPages(WindowsDriver driver) {
        windowsDriver = driver;
    }

    public void pressNumericButtons(int number) {
        switch (number) {
            case 1:
                windowsDriver.findElementByAccessibilityId(num1Btn).click();
                break;
            case 9:
                windowsDriver.findElement(num9Btn).click();
                break;

            default:
                break;
        }
    }

    public void pressOperatorButton(String operator) {
        switch (operator.toUpperCase()) {
            case "+":
            case "ADD":
                windowsDriver.findElement(plusBtn).click();
                break;
            case "=":
            case "EQUALS":
                windowsDriver.findElement(equalsBtn).click();
                break;

            default:
                break;
        }
    }

    public String getResults() {
        return windowsDriver.findElementByAccessibilityId(results).getText();
    }

}
