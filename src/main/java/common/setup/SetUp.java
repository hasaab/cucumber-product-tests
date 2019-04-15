package common.setup;

import web.AllPages;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.joda.time.DateTime;
import org.openqa.selenium.remote.DesiredCapabilities;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class SetUp {

  public static String projectPath = System.getProperty("user.dir");
  public static String driverPath = projectPath + "\\src\\main\\resources\\WebDrivers\\";
  public static String reportPath = projectPath + "\\target\\TestReports";
  public static String systemTime = DateTime.now().toString("yyyy-MM-ddHHmmss");
  public static String testStartTime = systemTime;
  public static int pageLoadWait= 20;
  public static int implicitWait=15;
  public static AppiumDriver appDriver;


  //------------------------------------------------------------------------//

  @Before("@ANDROID")
  public static AndroidDriver AndroidSetup() throws MalformedURLException {
    System.out.println("*******************\n");
    System.out.println(" Launching Android driver \n");
    System.out.println(" ProjectPath is " + projectPath);
    File appDir = new File(projectPath + "\\src\\main\\resources\\ProductX\\");
    File app = new File(appDir, "com.digitalchemy.calculator.freedecimal-5.1.0-www.APK4Fun.com.apk");
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

    appDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    //if (androiddriver.isLocked()){	System.out.println("device is locked");	}
    //else{	System.out.println("device is not locked");	}
    return (AndroidDriver) appDriver;
  }

  @After ("@ANDROID")
  public void androidTearDown()
  {
    System.out.println(" Test teardown - Android driver quit \n");
    appDriver.close();
    appDriver.quit();
  }

  //------------------------------------------------------------------------//

  @Before("@IOS")
  public static IOSDriver iosSetup() throws MalformedURLException {
    System.out.println("*******************\n");
    System.out.println(" Launching IOS driver \n");

    File appDir = new File(projectPath + "\\src\\main\\resources\\ProductX\\");
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

    appDriver = new IOSDriver(new URL("http://127.0.0.1:5010/wd/hub"), capabilities);

    //if (androiddriver.isLocked()){	System.out.println("device is locked");	}
    //else{	System.out.println("device is not locked");	}

    return (IOSDriver) appDriver;
  }

  @After ("@IOS")
  public void iosTearDown()
  {
    System.out.println(" Test teardown - IOS driver quit \n");
    appDriver.close();
    appDriver.quit();
  }

  //------------------------------------------------------------------------//

  @Before ("@REST")
  public void restSetup()
  {
    System.out.println("*******************");
    System.out.println(" Launching service driver \n");
  }

  @After ("@REST")
  public void restTearDown()
  {
    System.out.println("test teardown - service driver quit \n");
  }

  @Before("@LinkedInWeb")
  public void linkedInSetUp()
  {
    System.out.println("*******************");
    System.out.println("current product - LinkedIn");
    AllPages.currentProductName = "LinkedInWeb";
  }

  @Before("@YouTubeWeb")
  public void youTubeSetUp()
  {
    System.out.println("*******************");
    System.out.println("current product - YouTube");
    AllPages.currentProductName = "features/YouTube";
  }
}
