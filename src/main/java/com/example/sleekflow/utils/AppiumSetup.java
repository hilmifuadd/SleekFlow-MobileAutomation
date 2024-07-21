package com.example.sleekflow.utils;

import com.example.sleekflow.config.ProjectConfiguration;
import com.example.sleekflow.config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
public class AppiumSetup {
    private static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.mobileServerRemoteUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver createDriver() {
        File app = getApp();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("platformName", config.mobilePlatformName());
        desiredCapabilities.setCapability("deviceName", config.mobileDeviceName());
        desiredCapabilities.setCapability("platformVersion", config.mobileDeviceOsVersion());
        desiredCapabilities.setCapability("app", app.getAbsolutePath());
        desiredCapabilities.setCapability("appPackage", config.appPackage());
        desiredCapabilities.setCapability("appActivity", config.appActivity());
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);

        return new AndroidDriver<>(getAppiumServerUrl(), desiredCapabilities);
    }

    private static File getApp() {
        String appUrl = config.appUrl();
        String appPath = AppiumSetup.class.getClassLoader().getResource("apps/" + config.appName()).getPath();

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }
}
