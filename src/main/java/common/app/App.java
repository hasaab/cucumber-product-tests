package common.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class App {

    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;

    public static void StartAndroidDriver() {

        File app = new File(System.getProperty("projectPath"), "com.digitalchemy.calculator.freedecimal-5.1.0-www.APK4Fun.com.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("Platform Name","Android");

        //REALDEVICE:
        //capabilities.setCapability("deviceName", "Galaxy S5 Neo");
        //capabilities.setCapability("platformVersion", "6.0.1");

        //AVD:
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "6.0");

        //GENYMOTION
        //capabilities.setCapability("deviceName", "AndroidTestDevice");
        //capabilities.setCapability("platformVersion", "6.0");

        capabilities.setCapability("platform", "Windows");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.digitalchemy.calculator.freedecimal");
        capabilities.setCapability("appWaitPackage", "com.digitalchemy.calculator.freedecimal");
        //capabilities.setCapability("appActivity", "");
        //capabilities.setCapability("appWaitActivity", "");
        capabilities.setCapability("autoAcceptAlerts",true);
        capabilities.setCapability("fullReset",true);
        //capabilities.setCapability("deviceReadyTimeout", 500);
        //capabilities.setCapability("newCommandTimeout", 500);

        try {
            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
        catch (Exception ex)
        {System.out.println("Appdriver URL is not correct");}

        //if (androiddriver.isLocked()){	System.out.println("device is locked");	}
        //else{	System.out.println("device is not locked");	}
    }

    public static void StopAndroidDriver() {

        try{
        androidDriver.close();
        }
        catch (Exception ex)
        {System.out.println("Could not close android Driver or no android driver was running");}
    }

    //------------------------------------------------------------------------//

    public static void StartIOSDriver() {
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

        try{
        iosDriver = new IOSDriver(new URL("http://127.0.0.1:5010/wd/hub"), capabilities);
        }
        catch (Exception ex)
        {System.out.println("Appdriver URL is not correct");}

        //if (androiddriver.isLocked()){	System.out.println("device is locked");	}
        //else{	System.out.println("device is not locked");	}

    }

    public static void StopIOSDriver() {

        try {
            iosDriver.close();
        }
        catch (Exception ex)
        {System.out.println("Could not close ios Driver or no ios driver was running");}
    }



}
