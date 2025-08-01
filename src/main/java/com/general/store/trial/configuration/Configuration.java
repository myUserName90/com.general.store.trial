package com.general.store.trial.configuration;

import com.general.store.trial.enums.Env;

import java.util.Properties;

public class Configuration {
    private static final String CONFIG_PATH = "src/main/resources/config.properties";
    private static final Properties MAIN_CONFIG = ConfigReader.readFile(CONFIG_PATH);
    private static final Properties ENVIRONMENT_PROPS;
    public static String APP;
    public static String PLATFORM;
    public static String DEVICE_NAME;

    public static String SERVER_URL;
    public static String APP_PACKAGE;
    public static String APP_ACTIVITY;
    public static String COMMAND_TIMEOUT;
    public static String AUTOMATOR2_SERVER;

    static {
        ENVIRONMENT_PROPS = ConfigReader.readFile(Env.valueOf(MAIN_CONFIG.getProperty("environment").toUpperCase()).getPath());
        APP = MAIN_CONFIG.getProperty("app");
        PLATFORM = MAIN_CONFIG.getProperty("platform");
        DEVICE_NAME = ENVIRONMENT_PROPS.getProperty("device.name");
        SERVER_URL=ENVIRONMENT_PROPS.getProperty("appium.server.url");
        APP_PACKAGE =ENVIRONMENT_PROPS.getProperty("app.package");
        APP_ACTIVITY =ENVIRONMENT_PROPS.getProperty("app.activity");
        COMMAND_TIMEOUT=ENVIRONMENT_PROPS.getProperty("new.command.timeout");
        AUTOMATOR2_SERVER=ENVIRONMENT_PROPS.getProperty("automator2.server");

    }
}
