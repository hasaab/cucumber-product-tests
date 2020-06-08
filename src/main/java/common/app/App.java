package common.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class App {

    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;

    public static String startAndroidDriver() {

        try{
        File app = new File(System.getProperty("projectPath"), "com.digitalchemy.calculator.freedecimal-5.1.0-www.APK4Fun.com.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("Platform Name", "Android");

        //REALDEVICE:
        //capabilities.setCapability("deviceName", "Galaxy S10");
        //capabilities.setCapability("platformVersion", "6.0.1");

        //AVD:
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "6.0");

        capabilities.setCapability("platform", "Windows");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.digitalchemy.calculator.freedecimal");
        capabilities.setCapability("appWaitPackage", "com.digitalchemy.calculator.freedecimal");
        //capabilities.setCapability("appActivity", "");
        //capabilities.setCapability("appWaitActivity", "");
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("fullReset", true);
        //capabilities.setCapability("deviceReadyTimeout", 500);
        //capabilities.setCapability("newCommandTimeout", 500);

            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            //if (androiddriver.isLocked()){	System.out.println("device is locked");	}
            //else{	System.out.println("device is not locked");	}
            return  "PASS";
        } catch (Exception ex) {
            return "FAIL : " + ex.toString();
        }
    }

    public static String stopAndroidDriver() {
        try {
            androidDriver.close();
            return "PASS";
        } catch (Exception ex) {
            return "NOTE : " + ex.toString();
        }
    }

    //------------------------------------------------------------------------//

    public static String startIOSDriver() {
            try{
        System.out.println("*******************\n");
        System.out.println(" Launching IOS driver \n");

        File appDir = new File(System.getProperty("projectPath") + "\\src\\main\\resources\\ProductX\\");
        File app = new File(appDir, "com.linkedin.android-4.0.53.com.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("platformVersion", "");
        capabilities.setCapability("deviceName", "");
        capabilities.setCapability("udid", "");
        capabilities.setCapability("deviceReadyTimeout", 45);
        capabilities.setCapability("newCommandTimeout", 180);
        capabilities.setCapability("sendKeyStrategy", "oneByOne");

        iosDriver = new IOSDriver(new URL("http://127.0.0.1:5010/wd/hub"), capabilities);

        //if (iosDriver.isLocked()){	System.out.println("device is locked");	}
                // else{	System.out.println("device is not locked");	}
            return  "PASS";
        }
        catch(Exception ex) {
            return "FAIL : " + ex.toString();
        }
    }

    public static String stopIOSDriver() {
        try {
            iosDriver.close();
            return "PASS";
        }
        catch(Exception ex)
        { return  "NOTE : " + ex.toString();
        }
    }

}
