package screens;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelSheetsScreen {
    private WindowsDriver windowsDriver;

    By tabBar = By.className("NetUIRibbonTab");
    By tabOptionView = By.name("View");

    public ExcelSheetsScreen(WindowsDriver driver) {
        windowsDriver = driver;
    }

    public boolean isBlankSheetOpen() {
        WebDriverWait wait = new WebDriverWait(windowsDriver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabBar));
        return windowsDriver.findElement(tabBar).isDisplayed();
    }

    public void navigateToTab(String tabName) {
        switch (tabName.toUpperCase()) {
            case "VIEW":
                windowsDriver.findElement(tabOptionView).click();
                break;
        }

    }

    public void writeDataIntoSheet(String data, String cell) {
        By cellLocator = By.name(cell);
        windowsDriver.findElement(cellLocator).sendKeys(data + Keys.ENTER);
    }

    public String getDataFromSheet(String cell) {
        By cellLocator = By.name(cell);
        return windowsDriver.findElement(cellLocator).getText();
    }

}
