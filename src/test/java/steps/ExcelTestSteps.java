package steps;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import screens.ExcelSheetsScreen;
import screens.ExcelStartupScreen;

import static constants.SharedStateConstants.DRIVER;
import static org.assertj.core.api.Assertions.assertThat;

public class ExcelTestSteps {
    private WindowsDriver windowsDriver;
    private ExcelStartupScreen excelStartupScreen;
    private ExcelSheetsScreen excelSheetsScreen;

    @Given("User launches the MS Excel")
    public void startMSExcelApp() {
        windowsDriver = Serenity.sessionVariableCalled(DRIVER);
        excelStartupScreen = new ExcelStartupScreen(windowsDriver);
        assertThat(excelStartupScreen.excelIsLaunched())
                .withFailMessage("Excel didn't launch in 30 seconds")
                .isTrue();
    }

    @When("User creates new project")
    public void createNewProject() throws InterruptedException {
        excelStartupScreen.startNewProject();
        excelSheetsScreen = new ExcelSheetsScreen(windowsDriver);

        assertThat(excelSheetsScreen.isBlankSheetOpen())
                .withFailMessage("Blank sheet is not open")
                .isTrue();
    }

    @And("User navigates to {} tab")
    public void navigateToTab(String tabName) {
        excelSheetsScreen.navigateToTab(tabName);
    }

    @And("User writes {} in {}")
    public void enterDataIntoSheet(String data, String cell) {
        excelSheetsScreen.writeDataIntoSheet(data, cell);
    }

    @And("User waits for {int} seconds")
    public void waitForSeconds(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    @Then("Cell {} has {} value")
    public void assertCellValue(String cell, String expectedData) {
        assertThat(excelSheetsScreen.getDataFromSheet(cell)).isEqualTo(expectedData);
    }
}
