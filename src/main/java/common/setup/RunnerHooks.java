package common.setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.joda.time.DateTime;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static common.cucumber.IWebSteps.ITakeScreenShot;
import static common.selenium.WebSteps.StartWebDriver;
import static common.selenium.WebSteps.StopWebDriver;
import static common.setup.SetUp.appDriver;
import static common.setup.SetUp.projectPath;
import static common.util.DataHelp.getTimeStamp;

public class RunnerHooks {

    public static boolean wantsToQuit = false;
    public static Scenario scenario;
    public static String myScenario;


    //------------------------------------------------------------------------//


    @Before
    public void setup(Scenario scenario) throws Exception
    {
        // local use, comment before push
        System.setProperty("runDriver","chrome");
        System.setProperty("seleniumGrid","local"); //http://192.168.1.208:32001/wd/hub
        System.setProperty("runEnvironment","PROD");


        this.scenario = scenario;

        if(wantsToQuit)
            throw new RuntimeException("Test FAIL : Cucumber wants to quit");
          try {
            if (!(System.getProperty("runDriver").isEmpty() || System.getProperty("runEnvironment").isEmpty() || System.getProperty("seleniumGrid").isEmpty())) {
              System.out.println("************************************************************************************\n");
              System.out.println("WebDriver, Environment and SeleniumGrid property found.");
            }
          }
          catch (Exception ex) {
            System.out.println("************************************************************************************\n");
            System.out.println("Error : No WebDriver, Environment or SeleniumGrid property found.");
          }

        myScenario = scenario.getName();
        System.setProperty("scenario",myScenario);
        System.setProperty("product",myScenario.substring(0,myScenario.indexOf("-")).replace(" ",""));

        System.setProperty("projectPath",System.getProperty("user.dir"));
        System.setProperty("systemTime", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        System.setProperty("userID",System.getProperty("user.home").replace("C:\\Users\\",""));
        System.setProperty("downloadPath",System.getProperty("user.home")+"\\Downloads");
        System.setProperty("reportPath",System.getProperty("projectPath")+"\\target\\cucumber-reports");
        System.setProperty("filePath",System.getProperty("projectPath") + "\\src\\main\\resources\\files\\");
        System.setProperty("driverPath",System.getProperty("projectPath") + "\\src\\main\\resources\\webdrivers\\");

        System.out.println("************************************************************************************\n");

        System.out.println("SystemTime : " + System.getProperty("systemTime") + "\n");
        System.out.println("Product Tests Starts \n");
        System.out.println("Scenario : " + myScenario + "\n");

        System.out.println("ProjectPath : " + System.getProperty("projectPath") + "\n");
        System.out.println("ReportPath : " + System.getProperty("reportPath") + "\n");
        System.out.println("FilePath : " + System.getProperty("filePath") + "\n");

        if( System.getProperty("product").contains("Web"))
        {
            System.setProperty("mainURL", AllURLs.getProductURL());
            System.out.println("MainURL : " + System.getProperty("mainURL") + "\n");
            System.out.println("WebDriver : " + System.getProperty("runDriver") + "\n");
            System.out.println("SeleniumGrid : " + System.getProperty("seleniumGrid") + "\n");
        }

        System.out.println("Environment : " + System.getProperty("runEnvironment") + "\n");

        System.out.println("************************************************************************************\n");

         if(scenario.getName().contains("Web"))
            StartWebDriver(System.getProperty("runDriver"));
        if(scenario.getName().contains("Android"))
            AndroidSetup();
        if(scenario.getName().contains("IOS"))
            IOSSetup();

        System.out.println("************************************************************************************\n");

    }

    @After
    public static void tearDown(Scenario screnario) throws Exception
    {
        if(screnario.isFailed())
        {
            ITakeScreenShot(myScenario + " failed_" + getTimeStamp("YYYY-MM-DD-HH-mm-ss-SSS"));
            StopWebDriver();

            appDriver.close();
            appDriver.quit();

            System.out.println("Test Failed ! \n");
            }

        else{
            System.out.println("Test Passed ! \n");
        }
        StopWebDriver();
        System.out.println("************************************************************************************\n");


    }


    public static AndroidDriver AndroidSetup() throws MalformedURLException {

        File app = new File(projectPath, "com.digitalchemy.calculator.freedecimal-5.1.0-www.APK4Fun.com.apk");
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

    //------------------------------------------------------------------------//

    @Before("@IOS")
    public static IOSDriver IOSSetup() throws MalformedURLException {
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

    //-----------------------------------------------------------------------------//

}
