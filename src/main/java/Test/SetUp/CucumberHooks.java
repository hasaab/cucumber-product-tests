package Test.SetUp;

import Common.Web.WebHelp;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public final class CucumberHooks {

  public static boolean wantsToQuit = false;

  @Before
  public void quitIfRequested(Scenario scenario) {
    if (wantsToQuit)
      throw new RuntimeException("Cucumber wants to quit.");
  }

  @After
  public void after(Scenario s) throws Exception {
    // Tell Cucumber to quit after this scenario is done - if it failed.
    if (CucumberHooks.wantsToQuit = true == s.isFailed()) {
      String testImage = "testFailureImage" + SetUp.systemTime;
      File scrFile = ((TakesScreenshot) WebHelp.webDriver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File("target/TestReports/" + testImage + ".png"));
      System.out.println("Test Failure ScreenShoot saved with " + testImage + " name to TestReports");



    }
  }
}
