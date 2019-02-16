package Test.Web;

import Common.Web.WebHelp;
import Test.SetUp.SetUp;
import cucumber.api.java.en.Given;
import java.io.IOException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class WebSteps {

  @Given("^I start the WebDriver with the \"([^\"]*)\" browser$")
  public void IStartTheWebDriver(String driver) {
    WebHelp.startWebDriver(driver);
    System.out.println( "Webdriver " +driver+ " started");
    System.out.println("\n");
  }

  @Given("^I stop the WebDriver with the \"([^\"]*)\" browser$")
  public void IStopTheWebDriver(String driver) {
    WebHelp.stopWebDriver(driver);
    System.out.println( "WebDriver " +driver+ " stopped");
    System.out.println("\n");
  }

  @Given("^I navigate to the \"([^\"]*)\" page$")
  public void INavigateTo(String URL) {
    WebHelp.navigateTo(URL);
    System.out.println( "Navigeted to " + URL + " URL");
    System.out.println("\n");
  }

  @Given("^I am on the \"([^\"]*)\" page$")
  public void IAmOnThePage(String pageName) {
    AllPages.currentPageName = pageName;
    System.out.println( "On the " + pageName + " page");
    System.out.println("\n");
  }

  @Given("^I select the \"([^\"]*)\" element$")
  public void ISelectTheElement(String elementName) {
    String elementLocator = AllPages.getElementLocator(elementName);
    if (elementName.contains("alert"))
      WebHelp.alertHandling("accept");
    else {
      Assert.assertTrue(WebHelp.waitToAppear(elementLocator));

      if (elementName.contains("selection") || (elementName.contains("login")))
        WebHelp.sleep(1000);

      WebHelp.safeSelect(elementLocator, "safeclick");

      System.out.println( "Element " + elementName + " selected with " + elementLocator + " elementLocator");
      System.out.println("\n");

      if (elementName.contains("confirm"))
        WebHelp.sleep(2000);
      else if (elementName.contains("search"))
        WebHelp.sleep(1000);
    }
  }

  @Given("^I select the \"([^\"]*)\" as the \"([^\"]*)\"$")
  public void ISelectTheElementAsThe(String value , String elementName )
  {
    String elementLocator = AllPages.getElementLocator(elementName);
    WebHelp.selectFromDropDown(elementLocator,value);
    System.out.println( "Value selected from the " + elementName + " with " + elementLocator + " elementLocator");
    System.out.println("\n");
  }

  @Given("^I enter \"([^\"]*)\" as the \"([^\"]*)\" element$")
  public void IEnterAsTheWeb(String entry,String elementName)
  {
    String elementLocator = AllPages.getElementLocator(elementName);

    if (entry.contains("system"))
      WebHelp.sleep(1000);

    Assert.assertTrue(WebHelp.waitToAppear(elementLocator));
    WebElement webElement = WebHelp.webDriver.findElement(By.cssSelector(elementLocator));

    WebHelp.safeSelect(elementLocator, "safeclick");

    if (elementName.contains("amount"))
    { for (int i = 0; i < 4; i++) { webElement.sendKeys(Keys.ARROW_LEFT); } }

   // if (entry.contains("system_date"))
   // {
   //   entry = entry.replace("system_date", "system_date_start");
   //   entry = DataHelp.GetDynamicDate(DataBaseRWCD.systemDate, entry, "ddMMyyyy");
   // }

   // if (entry.contains("random") && elementName.contains("proposal"))
   // {
   //     entry = DataHelp.GenerateRandomString(10, "01");
   // }

    if (elementName.contains("date"))
    {
      for (int i = 0; i < 10; i++)
      {
        webElement.sendKeys(Keys.ARROW_LEFT);
      }
      if (entry.contains("-"))
        entry = entry.replace("-", "");
      else if (entry.contains("."))
        entry = entry.replace(".", "");
      else if (entry.contains("/"))
        entry = entry.replace("/", "");
    }

    webElement.clear();
    webElement.sendKeys(entry);
    System.out.println( "Value " + entry + " entered into " + elementName +  " the field with " + elementLocator + " elementLocator");
    System.out.println("\n");
    if (elementName.contains("main"))
    {
      WebHelp.safeSelect("#main", "safeclick");
      WebHelp.sleep(2000);
    }
  }

  @Given("^I should see the \"([^\"]*)\" element$")
  public void IShouldSeeThe(String elementName) throws IOException
  {
    String elementLocator = AllPages.getElementLocator(elementName);
    Assert.assertTrue(WebHelp.waitToAppear(elementLocator));
    WebElement webElement = WebHelp.webDriver.findElement(By.cssSelector(elementLocator));
    WebHelp.safeSelect(elementLocator, "focus");
    String fileName = "ProductTests_Default_" + DateTime.now().toString("yyyy-MM-dd-HHmmss") + "_evidence.jpg";
    WebHelp.takeScreenShot(fileName, SetUp.reportPath);

    if (elementName.contains("notification"))
    {
      String message = webElement.getText();
      System.out.println(message);

      if (elementName.contains("success"))
        Assert.assertTrue(message.contains("Success:"));
    }
    Assert.assertTrue(WebHelp.isDisplayed(elementLocator));
    System.out.println( "Element " + elementName + " is visible with" + elementLocator + " elementLocator");
    System.out.println("\n");
  }

  @Given("^I should not see the \"([^\"]*)\" element$")
  public void IShouldNotSeeThe(String elementName) throws IOException
  {
    String elementLocator = AllPages.getElementLocator(elementName);
    String fileName = "ProductTests_Default_" + DateTime.now().toString("yyyy-MM-dd-HHmmss") + "_evidence.jpg";
    WebHelp.takeScreenShot(fileName, SetUp.reportPath);
    Assert.assertTrue(WebHelp.waitToDisappear(elementLocator));
    System.out.println( "Element " + elementName + " is not visible with " + elementLocator + " elementLocator");
    System.out.println("\n");
  }


  @Given("^I should see \"([^\"]*)\" value at the \"([^\"]*)\" element$")
  public void TheValueShouldBe(String elementName, String value) throws IOException
  {
    String elementLocator = AllPages.getElementLocator(elementName);
    Assert.assertTrue(WebHelp.waitToAppear(elementLocator));
    WebElement webElement = WebHelp.webDriver.findElement(By.cssSelector(elementLocator));
    WebHelp.safeSelect(elementLocator, "focus");
    String fileName = "ProductTests_Default_" + DateTime.now().toString("yyyy-MM-dd-HHmmss") + "_evidence.jpg";
    WebHelp.takeScreenShot(fileName, SetUp.reportPath);
    Assert.assertTrue(webElement.getText().contains(value));
    System.out.println( "Value "+ value  +" visble in element " + elementName + " with " + elementLocator + " elementLocator");
    System.out.println("\n");
  }

  @Given("^I take a test evidence streenshot$")
  public void ITakeScreenShot(String elementName, String value) throws IOException
  {
    String fileName = "ProductTests_Default_" + DateTime.now().toString("yyyy-MM-dd-HHmmss") + "_evidence.jpg";
    WebHelp.takeScreenShot(fileName, SetUp.reportPath);
  }

  @Given("The \"([^\"]*)\" element should be selected^$")
  public void ElementShouldBeSelected(String elementName) throws IOException
  {
    String elementLocator = AllPages.getElementLocator(elementName);
    Assert.assertTrue(WebHelp.waitToAppear(elementLocator));
    WebElement webElement = WebHelp.webDriver.findElement(By.cssSelector(elementLocator));
    WebHelp.safeSelect(elementLocator, "focus");
    String fileName = "ProductTests_Default_" + DateTime.now().toString("yyyy-MM-dd-HHmmss") + "_evidence.jpg";
    WebHelp.takeScreenShot(fileName, SetUp.reportPath);
    Assert.assertTrue(webElement.isSelected());
    //Assert.IsTrue(webelement.GetAttribute("checked")=="Checked");
    System.out.println( "Element " + elementName + " selected with " + elementLocator + " elementLocator");
    System.out.println("\n");
  }

  @Given("I wait \"([^\"]*)\" sec/s$")
  public static void GivenIWaitSec(int wait)
  {
    WebHelp.sleep(wait * 1000);
  }

}
