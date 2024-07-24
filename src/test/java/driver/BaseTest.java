package driver;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utils.PropertyUtils;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public class BaseTest {
    public static final String packageName = PropertyUtils.getProperty("android.appPackage");
    public static final String activity = PropertyUtils.getProperty("android.appActivity");
    public static final String uiautomator = PropertyUtils.getProperty("android.automationName");
    public static final String permissions = PropertyUtils.getProperty("android.autoGrantPermissions");
    public static final String clearSystem = PropertyUtils.getProperty("android.clearSystemFiles");
    protected static AppiumDriverLocalService service;
    protected static AndroidDriver driver;

    public BaseTest() {
    }

    @BeforeTest
    @Parameters({"udid", "platformVersion"})
    public void preparation(String udid, String platformVersion) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", activity);
        capabilities.setCapability("clearSystemFile", clearSystem);
        capabilities.setCapability("autoGrantPermissions", permissions);
        capabilities.setCapability("automationName", uiautomator);
        capabilities.setCapability("setWebContentsDebuggingEnabled", true);
        capabilities.setCapability("autoWebview", false);
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        // Automatically find Node.js and Appium
        String nodePath = (String) findExecutable("node").orElseThrow(() -> new IllegalStateException("Node.js not found"));
        String appiumPath = (String) findExecutable("appium").orElseThrow(() -> new IllegalStateException("Appium not found"));
        int port = 4723;
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodePath))
                .withAppiumJS(new File(appiumPath))
                .withIPAddress("127.0.0.1")
                .usingPort(port));
        service.start();
        String service_url = service.getUrl().toString();
        System.out.println("Appium Service Address: " + service_url);
        driver = new AndroidDriver(new URL(service_url), capabilities);
        ThreadLocalDriver.setTLDriver(driver);
    }

    @AfterTest
    public void stopService() {
        try {
            if (ThreadLocalDriver.getTLDriver() != null) {
                ThreadLocalDriver.getTLDriver().quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.stop();
            }
        }
    }
    private Optional<String> findExecutable(String executable) {
        String path = System.getenv("PATH");
        String[] paths = path.split(File.pathSeparator);
        for (String p : paths) {
            File file = new File(p, executable + (isWindows() ? ".exe" : ""));
            if (file.isFile() && file.canExecute()) {
                return Optional.of(file.getAbsolutePath());
            }
        }
        return Optional.empty();
    }
    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
