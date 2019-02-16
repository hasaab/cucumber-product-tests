package Common.Web;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import sun.plugin.dom.exception.InvalidStateException;

import static Test.SetUp.SetUp.driverPath;
import static Test.SetUp.SetUp.implicitWait;
import static Test.SetUp.SetUp.pageLoadWait;

public class WebHelp {

  public static WebDriver webDriver = null;

  public static void startWebDriver(String driver)
  {
    String currentDriver = null;

    if(driver.equals("Firefox"))
    {
      currentDriver = "Firefox";
      System.out.println("******************* \n");
      System.out.println("launching " + currentDriver + " driver \n");
      webDriver = new FirefoxDriver();
    }
    else if (driver.equals("Chrome"))
    {
      currentDriver = "Chrome";
      System.out.println("*******************\n");
      System.out.println("launching " + currentDriver + " driver \n");
      String chromeDriverPath = driverPath + "chromeDriver.exe";
      System.setProperty("webdriver.chrome.driver", chromeDriverPath);
      //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      //ChromeOptions options = new ChromeOptions();
      //options.addArguments("test-type");
      //capabilities.setCapability("webdriver.chrome.driver", chromeDriverPath);
      //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      webDriver = new ChromeDriver();
    }
    else if (driver.equals("IE"))
    {
      currentDriver = "Chrome";
      System.out.println("*******************\n");
      System.out.println("launching " + currentDriver + " driver \n");
      String chromeDriverPath = driverPath + "chromeDriver.exe";
      System.setProperty("webdriver.ie.driver", chromeDriverPath);
      webDriver = new InternetExplorerDriver();
    }
    else System.out.println("Webdriver " + driver + "not implemented in the test setup");

    webDriver.manage().window().maximize();
    webDriver.manage().timeouts().pageLoadTimeout(pageLoadWait, TimeUnit.SECONDS);
    webDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
  }

  public static void stopWebDriver(String driver)
  {
    webDriver.close();
    webDriver.quit();
  }

  public static void navigateTo(String URL)
  {
    try
    {
      webDriver.navigate().to(URL);
    }
    catch (Exception ex)
    {
      System.out.println(ex.toString());
    }
  }

  public static void alertHandling(String action)
  {
    try
    {
      if (action == "accept")
        webDriver.switchTo().alert().accept();
      else if (action == "dismiss")
        webDriver.switchTo().alert().dismiss();
    }
    catch (Exception ex)
    {
      System.out.println(ex.toString());
    }
    System.out.println("Alert has been " + action + " ed");
  }

  public static void waitSelect(String elementLocator, int timeOut)
  {
    WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
    WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(elementLocator)));
    webElement = webDriver.findElement(By.cssSelector(elementLocator));
    webElement.click();
    System.out.println("Element with " + elementLocator + " locator has been selected");
  }

  public static void waitType(String elementLocator, int timeOut, String value)
  {
    WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
    WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(elementLocator)));
    webElement.sendKeys(value);
    System.out.println("Value "+ value + " entered into element with locator " + elementLocator);
  }

  public static String waitGetText(String elementLocator, int timeOut)
  {
    String value = null;
    WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
    WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(elementLocator)));
    value = webElement.getAttribute("value");
    System.out.println("Value " + value + " presents in element with locator " + elementLocator);
    return value;
  }

  public static void safeSelect(String elementLocator, String to)
  {
    Assert.assertTrue(waitToAppear(elementLocator));
    WebElement webElement = webDriver.findElement(By.cssSelector(elementLocator));
    if ( to.equals("click"))
    {
      webElement.click();
    }
    else
    {
      Actions actions = new Actions(webDriver);
      actions.moveToElement(webElement);
      actions.perform();

      if (to.equals("safeclick"))
        webElement.click();
      else if (to.equals("return"))
        webElement.sendKeys(Keys.RETURN);
      else if (to.equals("enter"))
        webElement.sendKeys(Keys.ENTER);
    }
  }

  public static void safeType(String elementLocator, String text)
  {
    Assert.assertTrue(waitToAppear(elementLocator)==true);
    WebElement webElement = webDriver.findElement(By.cssSelector(elementLocator));
    safeSelect(elementLocator, "safeclick");
    webElement.clear();
    webElement.sendKeys(text);
  }

  public static void selectFromDropDown(String elementLocator, String value)
  {
    Assert.assertTrue(waitToAppear(elementLocator));
    WebElement webElement = webDriver.findElement(By.cssSelector(elementLocator));
    Select selectElement = new Select(webElement);
    selectElement.selectByVisibleText(value);
  }

  public static Boolean isDisplayed(String elementLocator)
  {
    try
    {
      Thread.sleep(10);
      WebElement webElement = webDriver.findElement(By.cssSelector(elementLocator));
      return (webElement.isDisplayed() && webElement.isEnabled());
    }
    catch (NoSuchElementException RequiredPageContentNotPresent)
    {
      return false;
    }
    catch (InvalidStateException requiredPageContentNotPresent)
    {
      return false;
    }
    catch (StaleElementReferenceException elementHasDestroyed)
    {
      return false;
    }
    catch (InterruptedException elementHasDestroyed)
    {
      return false;
    }
  }

  public static void sleep(int sleep)
  {
    try {Thread.sleep(sleep);}
    catch (Exception ex ) {System.out.println(ex.toString());}
  }

  public static Boolean waitToAppear(String elementLocator)
  {
    int startTime = 0;
    while (startTime < 30000)
    {
      if (isDisplayed(elementLocator))
      {
        return true;
      }
      sleep(250);
      startTime = startTime + 250;
    }
    System.out.println(" Element with locator " + elementLocator + " did not appear in " + 30000 + " msec");
    return false;
  }

  public static Boolean waitToDisappear(String elementLocator)
  {
    int startTime = 0;
    while (startTime < 30000)
    {
      if (!(isDisplayed(elementLocator)))
      {
        return true;
      }
      sleep(250);
      startTime = startTime + 250;
    }
    System.out.println(" Element with locator " + elementLocator + " did not disappear in " + 30000 + " msec");
    return false;
  }

  public static void upLoadFile(String browseButtonCSS, String filePath)
  {
    webDriver.findElement(By.cssSelector(browseButtonCSS)).sendKeys(filePath);
    System.out.println(" File with path " + filePath + " uploaded to " + browseButtonCSS + " element");
  }

  public static void takeScreenShot(String fileName, String filePath) throws IOException
  {
    File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File(filePath + fileName));
    System.out.println("Test Evidence ScreenShoot saved with " + fileName + " name to TestReports");
  }
}

