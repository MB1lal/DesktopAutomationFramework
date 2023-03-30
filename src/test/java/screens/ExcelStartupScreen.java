package screens;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelStartupScreen {
    private WindowsDriver windowsDriver;

    By newProjectBtn = By.name("New");
    By blankExcelSheetBtn = By.name("Blank workbook");

    By excelFileRibbon = By.name("File");
    By closePopUpCloseBtn = By.name("Close");


    public ExcelStartupScreen(WindowsDriver driver) {
        windowsDriver = driver;
    }

    private void closeTrialPopup() {
        try {
            windowsDriver.findElement(closePopUpCloseBtn).click();
        } catch (Exception ex) {
            // ignore
        }
    }

    public boolean excelIsLaunched() {
        WebDriverWait wait = new WebDriverWait(windowsDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(excelFileRibbon));

        return windowsDriver.findElement(excelFileRibbon).isDisplayed();
    }

    public void startNewProject() {
        windowsDriver.findElement(blankExcelSheetBtn).click();
    }
}
