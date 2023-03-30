package core;

public class EnvSerenity extends Config {
    public static final String remoteHost = environmentVariables.getProperty("remoteAddress");
    public static final String platformName = environmentVariables.getProperty("patformName");
    public static final String deviceName = environmentVariables.getProperty("deviceName");
    public static final String applicationCalculatorPath = environmentVariables.getProperty("calculatorAppPath");

}
