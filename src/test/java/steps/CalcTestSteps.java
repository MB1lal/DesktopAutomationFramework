package steps;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import screens.CalculatorScreen;

import java.util.concurrent.TimeUnit;

import static constants.SharedStateConstants.DRIVER;
import static org.assertj.core.api.Assertions.assertThat;

public class CalcTestSteps {

    private WindowsDriver windowsDriver;
    private CalculatorScreen calculatorScreen;

    @Given("User starts the calculator application")
    public void startCalcApp() {
        windowsDriver = Serenity.sessionVariableCalled(DRIVER);
        calculatorScreen = new CalculatorScreen(windowsDriver);
    }

    @When("User inputs numbers")
    public void inputNumbers() {
        calculatorScreen.pressNumericButtons(1);
        calculatorScreen.pressOperatorButton("+");
        calculatorScreen.pressNumericButtons(9);
    }

    @And("User presses equals")
    public void pressEquals() {
        calculatorScreen.pressOperatorButton("=");
    }

    @Then("Results should be correct")
    public void assertResult() {
        /* Blocking Sleep - Not a good practice, used only for demo */
        windowsDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        String resultantText = "10";
        String resultsElementText = calculatorScreen.getResults().replace("Display is", "").trim();

        /* Assert if the result is not 10 */
        assertThat(resultantText).isEqualTo(resultsElementText);

    }
}
