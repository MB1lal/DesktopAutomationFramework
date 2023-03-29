package steps;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import pages.CalculatorPages;

import java.util.concurrent.TimeUnit;

import static constants.SharedStateConstants.DRIVER;
import static org.assertj.core.api.Assertions.assertThat;

public class CalcTestSteps {

    private WindowsDriver windowsDriver;
    private CalculatorPages calculatorPages;

    @Given("User starts the calculator application")
    public void startCalcApp() {
        windowsDriver = Serenity.sessionVariableCalled(DRIVER);
        calculatorPages = new CalculatorPages(windowsDriver);
    }

    @When("User inputs numbers")
    public void inputNumbers() {
        calculatorPages.pressNumericButtons(1);
        calculatorPages.pressOperatorButton("+");
        calculatorPages.pressNumericButtons(9);
    }

    @And("User presses equals")
    public void pressEquals() {
        calculatorPages.pressOperatorButton("=");
    }

    @Then("Results should be correct")
    public void assertResult() {
        /* Blocking Sleep - Not a good practice, used only for demo */
        windowsDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        String resultantText = "10";
        String resultsElementText = calculatorPages.getResults().replace("Display is", "").trim();

        /* Assert if the result is not 10 */
        assertThat(resultantText).isEqualTo(resultsElementText);

    }
}
