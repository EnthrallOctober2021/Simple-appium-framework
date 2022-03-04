package base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    protected static AndroidDriver<MobileElement> androidDriver;

    public static void initializeDriver(String appPackage, String appActivity, String appPath){
        try {
            androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(appPackage, appActivity, appPath));
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static DesiredCapabilities getCapabilities(String appPackage, String appActivity, String appPath){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30);
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554"); // your device id
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true); // If reset  is not needed for the app
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("app", appPath);

        return capabilities;
    }


    public static AndroidDriver<MobileElement> getDriver()  {
        return androidDriver;
    }
}
