package steps;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.WinDriver;

import java.io.IOException;
import java.net.URL;

import static constants.SharedStateConstants.DRIVER;

public class Hooks {

    private WindowsDriver windowsDriver;
    public String appPath = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";

    @Before
    public void startDesktopDriver() throws IOException {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app", appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows10Machine");
        /* Start WinAppDriver.exe so that it can start listening to incoming requests */
        WinDriver.start();
        windowsDriver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
        Serenity.setSessionVariable(DRIVER).to(windowsDriver);
    }

    @After
    public void stopDesktopDriver() {
        if (windowsDriver != null) {
            /* The instance of WinAppDriver will be freed once
            last test is complete
            */

            WinDriver.stop();
            windowsDriver.quit();

        }
    }
}
