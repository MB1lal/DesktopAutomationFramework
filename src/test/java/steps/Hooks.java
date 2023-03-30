package steps;

import core.EnvSerenity;
import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.WinDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import static constants.SharedStateConstants.DRIVER;

public class Hooks {

    private WindowsDriver windowsDriver;

    @Before
    public void startDesktopDriver(Scenario scenario) throws IOException {
        String appPath;
        if (scenario.getSourceTagNames().contains("@calculator")) {
            appPath = EnvSerenity.applicationCalculatorPath;
        } else {
            // For MS Excel for now
            appPath = "\"C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE\"";
        }

        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app", appPath);
        capability.setCapability("platformName", EnvSerenity.platformName);
        capability.setCapability("deviceName", EnvSerenity.deviceName);
        /* Start WinAppDriver.exe so that it can start listening to incoming requests */
        WinDriver.start();
        windowsDriver = new WindowsDriver(new URL(EnvSerenity.remoteHost), capability);
        Serenity.setSessionVariable(DRIVER).to(windowsDriver);
    }

    @After
    public void stopDesktopDriver() {
        /* The instance of WinAppDriver will be freed once
            last test is complete
            */
        windowsDriver.quit();
        try {
            Thread.sleep(100);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_N);
        } catch (Exception ex) {
            // ignore
        } finally {
            WinDriver.stop();
        }
    }
}
