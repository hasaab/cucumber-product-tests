package common.setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.joda.time.DateTime;
import org.junit.Assert;

import static common.app.App.*;
import static common.selenium.WebHelp.takeScreen;
import static common.selenium.WebSteps.*;
import static common.util.DataHelp.getTimeStamp;

public class RunnerHooks {

    public static boolean wantsToQuit = false;
    public static Scenario scenario;
    public static String myScenario;
    public static String message;

    //------------------------------------------------------------------------//


    @Before
    public void setup(Scenario scenario) throws Exception
    {
        // local use, comment before push
        System.setProperty("runDriver","chrome");
        System.setProperty("seleniumGrid","local"); //http://192.168.1.208:32001/wd/hub
        System.setProperty("runEnvironment","QA");

        this.scenario = scenario;

        if(wantsToQuit)
            throw new RuntimeException("Test FAIL : Cucumber wants to quit");

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

        System.out.println("SystemTime : " + System.getProperty("systemTime"));
        System.out.println("Product Tests Starts ");
        System.out.println("Scenario : " + myScenario);

        System.out.println("ProjectPath : " + System.getProperty("projectPath"));
        System.out.println("ReportPath : " + System.getProperty("reportPath"));
        System.out.println("FilePath : " + System.getProperty("filePath"));

        System.out.println("Environment : " + System.getProperty("runEnvironment"));

        if(scenario.getName().contains("Web"))
            startWebDriver(System.getProperty("runDriver"));
        else if(scenario.getName().contains("Android"))
            startAndroidDriver();
        else if(scenario.getName().contains("IOS"))
            startIOSDriver();

        System.out.println("************************************************************************************\n");

    }

    @After
    public static void tearDown(Scenario screnario) throws Exception
    {
        if(screnario.isFailed())
        {
            takeScreenShot(myScenario + " failed_" + getTimeStamp("YYYY-MM-DD-HH-mm-ss-SSS"));
            stopWebDriver();
            stopAndroidDriver();
            startIOSDriver();

            System.out.println("Test Failed !");
            }

        else{
            System.out.println("Test Passed !");
        }
        stopWebDriver();
        stopAndroidDriver();
        stopIOSDriver();
        System.out.println("************************************************************************************\n");


    }


    //-----------------------------------------------------------------------------//


    public static void AssertExecutedStep(String result)
    {
        if (!result.toUpperCase().contains("PASS")) {
            RunnerHooks.scenario.write(result);
            //System.out.println(message);
            Assert.assertTrue(false);
        }
        else {
            RunnerHooks.scenario.write(result);
            //System.out.println(result);
        }
    }

    public static void VerifyExecutedStep(String result)
    {
        if (!result.toUpperCase().contains("PASS")){
            RunnerHooks.scenario.write(result);
            //System.out.println(result);
        }
        else {
            RunnerHooks.scenario.write(result);
            //System.out.println(result);
        }
    }


}
