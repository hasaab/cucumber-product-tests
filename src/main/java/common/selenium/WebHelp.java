package common.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebHelp {

    public static WebDriver webDriver;
    public static int waitTimeMax= 5000;
    public static int waitTime = 250;

    public static String startWebDriver(String driver)
    {
        try
        {
            DesiredCapabilities capabilities ;
            driver = driver.toUpperCase();
            String driverPath = System.getProperty("driverPath");

            switch(driver)
            {

                case "CHROME":
                    String chromeDriverPath = driverPath + "chromeDriver.exe";
                    System.setProperty("webdriver.chrome.driver",chromeDriverPath);

                    HashMap<String,Object> chromePrefs = new HashMap<String,Object>();
                    chromePrefs.put("profile.default_content_settings.popups",0);
                    chromePrefs.put("download.default_directory","C:\\" + System.getProperty("userId") + "\\Temp\\Documents\\");

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs",chromePrefs);
                    options.addArguments("--ssl-version-max=tls1");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--start-maximized");
                    options.addArguments("--screenshot");

                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
                    capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
                    capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
                    capabilities.setCapability("chrome.switches", Arrays.asList("--screenshots"));
                    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                    if(!System.getProperty("seleniumGrid").toUpperCase().equals("LOCAL"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")),capabilities);
                    else if(System.getProperty("seleniumGrid").toUpperCase().equals("LOCAL"))
                        webDriver = new ChromeDriver(options);
                    else System.out.println("seleniumGrid" + " has not been defined.");

                    break;

                case "IE" :

                    String ieDriverPath = driverPath + "IEDriverServer.exe";
                    System.setProperty("webdriver.ie.driver",ieDriverPath);

                    capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                    capabilities.setJavascriptEnabled(true);

                    if(System.getProperty("runEnvironment").toUpperCase().equals("REMOTE"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")),capabilities);
                    else if(System.getProperty("runEnvironment").toUpperCase().equals("LOCAL"))
                        webDriver = new InternetExplorerDriver();
                    else System.out.println(System.getProperty("runEnvironment") + " has not been defined yet.");

                    break;

                case "FIREFOX" :

                    capabilities = DesiredCapabilities.firefox();
                    String firefoxDriverPath = driverPath + "geckodriver.exe";
                    System.setProperty("webdriver.gecko.driver",firefoxDriverPath);

                    if(System.getProperty("runEnvironment").toUpperCase().equals("REMOTE"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")),capabilities);
                    else if(System.getProperty("runEnvironment").toUpperCase().equals("LOCAL"))
                        webDriver = new FirefoxDriver();
                    else System.out.println(System.getProperty("runEnvironment") + " has not been defined yet.");

                default :
                    System.out.println("webDriver " + driver + " has not been defined yet");

            }

            webDriver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);

            webDriver.manage().window().maximize();

            return  "PASS";

        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}

    }

    public static String stopWebDriver()
    {
        try
        {
            webDriver.close();
            webDriver.quit();
            return  "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String navigateTo(String URL)
    {
        try
        {
            if(!URL.isEmpty() && !URL.equals("") && !(URL == null))
                webDriver.navigate().to(URL);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static Boolean verifyNotNull(String text)
    {
        if(!text.isEmpty() && !text.equals("") && !(text == null))
        return true;
        else return false;
    }

    public static String isDisplayed(String elementSelector)
    {
        try
        {
            WebElement webElement  = webDriver.findElement(By.xpath(elementSelector));
            if(webElement.isDisplayed() || webElement.isEnabled())
                return "PASS";
            else if(webElement.isEnabled())
                return "Element not not displayed or hidden";
            else return "FAIL";
        }
        catch (Exception ex){
            return "FAIL";
        }
    }

    public static String sleep(int sleep)
    {
        try
        {
            Thread.sleep(sleep);
            return "PASS";
        }
        catch (Exception ex){return ex.toString();}
    }

    public static Boolean checkAppeared(String elementSelector) {
        double startTime = 0;

        while (startTime < waitTimeMax) {
            if (isDisplayed(elementSelector).equals("PASS"))
                return true;
            else {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        if (!isDisplayed(elementSelector).equals("PASS"))
            return false;
        else return true;
    }

    public static void tryToSwitchFrame(int index) {
        try{   webDriver.switchTo().frame(webDriver.findElements(By.xpath("//iframe")).get(index));}
        catch (Exception ex) {}
    }

    public static String waitToAppear(String elementSelector) {

        if (checkAppeared(elementSelector))
            return "PASS";

        else {

            webDriver.switchTo().defaultContent();

            for (int i = 0; i < 4; i++) {
                tryToSwitchFrame(i);
                for (int j = 0; j < 4; j++) {

                    if (isDisplayed(elementSelector).equals("PASS"))
                        return "PASS";

                    tryToSwitchFrame(j);

                    for (int x = 0; x < 4; x++) {

                        if (isDisplayed(elementSelector).equals("PASS"))
                            return "PASS";

                        tryToSwitchFrame(x);

                        for (int y = 0; y < 4; y++) {

                            if (isDisplayed(elementSelector).equals("PASS"))
                                return "PASS";

                            tryToSwitchFrame(y);
                        }
                        webDriver.switchTo().defaultContent();
                    }
                    webDriver.switchTo().defaultContent();
                }
                webDriver.switchTo().defaultContent();
            }

        }

        return isDisplayed(elementSelector);

    }




    public static String waitToDisappear(String elementSelector)
    {
        double startTime = 0;
        while (startTime < waitTimeMax)
        {
            if(!isDisplayed(elementSelector).equals("PASS"))
                return  "PASS";
            else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return "FAIL";
    }

    public static String switchToFrame(String frameSelector)
    {
        try
        {
            if(verifyNotNull(frameSelector)) {
                waitToAppear(frameSelector);
                WebElement frame = webDriver.findElement(By.xpath(frameSelector));
                webDriver.switchTo().frame(frame);
                sleep(3000);
                return "PASS";
            }
            else return "FAIL";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String switchToFrameInFrame(String frameSelector1, String frameSelector2)
    {
        try
        {
            webDriver.switchTo().defaultContent();
            if(verifyNotNull(frameSelector1)) {
                waitToAppear(frameSelector1);
                WebElement frame = webDriver.findElement(By.xpath(frameSelector1));
                webDriver.switchTo().frame(frame);
                sleep(2000);
            }
            else return  "FAIL";
            if(verifyNotNull(frameSelector2)) {
                waitToAppear(frameSelector2);
                WebElement frame = webDriver.findElement(By.xpath(frameSelector2));
                webDriver.switchTo().frame(frame);
                sleep(2000);
                return "PASS";
            }
            else return "FAIL";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String switchToDefaultContent()
    {
        try
        {
            webDriver.switchTo().defaultContent();
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String switchToWindow(int windowNumber)
    {
        try
        {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(windowNumber-1));
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String keyActions(String actionKey)
    {
        try
        {
            actionKey = actionKey.toUpperCase();
            Actions action = new Actions(webDriver);

            switch(actionKey)
            {
                case "PAGEDOWN": action.sendKeys(Keys.PAGE_DOWN); break;
                case "PAGEUP": action.sendKeys(Keys.PAGE_UP); break;
                case "ARROWUP": action.sendKeys(Keys.ARROW_UP); break;
                case "ARROWDOWN": action.sendKeys(Keys.ARROW_DOWN); break;
                case "ALT": action.sendKeys(Keys.ALT); break;
                case "ESC": action.sendKeys(Keys.ESCAPE); break;
                case "F1": action.sendKeys(Keys.F1); break;
                case "F2": action.sendKeys(Keys.F2); break;
                case "F3": action.sendKeys(Keys.F3); break;
                case "F4": action.sendKeys(Keys.F4); break;
                case "F5": action.sendKeys(Keys.F5); break;
                case "F6": action.sendKeys(Keys.F6); break;
                case "F7": action.sendKeys(Keys.F7); break;
                case "F8": action.sendKeys(Keys.F8); break;
                case "F9": action.sendKeys(Keys.F9); break;
                case "ENTER": action.sendKeys(Keys.ENTER); break;
                default: {System.out.println(actionKey + " actionKey has net been defined"); return "FAIL";}
            }
            action.perform();
            sleep(500);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String sendKeys(String elementSelector, String actionKey)
    {
        try
        {
            actionKey = actionKey.toUpperCase();
            WebElement webELement = webDriver.findElement(By.xpath(elementSelector));
            switch(actionKey)
            {
                case "PAGEDOWN": webELement.sendKeys(Keys.PAGE_DOWN); break;
                case "PAGEUP": webELement.sendKeys(Keys.PAGE_UP); break;
                case "ARROWUP": webELement.sendKeys(Keys.ARROW_UP); break;
                case "ARROWDOWN": webELement.sendKeys(Keys.ARROW_DOWN); break;
                case "ARROWRIGHT": webELement.sendKeys(Keys.ARROW_RIGHT); break;
                case "ARROWLEFT": webELement.sendKeys(Keys.ARROW_LEFT); break;
                case "ALT": webELement.sendKeys(Keys.ALT); break;
                case "ESC": webELement.sendKeys(Keys.ESCAPE); break;
                case "F1": webELement.sendKeys(Keys.F1); break;
                case "F2": webELement.sendKeys(Keys.F2); break;
                case "F3": webELement.sendKeys(Keys.F3); break;
                case "F4": webELement.sendKeys(Keys.F4); break;
                case "F5": webELement.sendKeys(Keys.F5); break;
                case "F6": webELement.sendKeys(Keys.F6); break;
                case "F7": webELement.sendKeys(Keys.F7); break;
                case "F8": webELement.sendKeys(Keys.F8); break;
                case "F9": webELement.sendKeys(Keys.F9); break;
                case "ENTER": webELement.sendKeys(Keys.ENTER); break;
                default: {System.out.println(actionKey + " sendKey has net been defined"); return "FAIL";}
            }
            sleep(500);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }


    public static String navigateToWithSendEnterKey(String URL, int thread1, int thread2)
    {
        try
        {
            navigateTo(URL);
            sleep(3000);
            keyActions("ENTER");
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String handleAlert(String action)
    {
        try
        {
            action = action.toUpperCase();
            if(action.equals("ACCEPT"))
                webDriver.switchTo().alert().accept();
            else if(action.equals("DISMISS"))
                webDriver.switchTo().alert().dismiss();
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String readTextOfWebElement(String elementSelector)
    {
        try
        {
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
            return webElement.getText();
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String readAttributeOfWebElement(String elementSelector, String attribute)
    {
        try
        {
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
            return webElement.getAttribute(attribute);
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String isChecked(String elementSelector, String status)
    {
        try
        {
            status = status.toUpperCase();
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
            if(webElement.isSelected() && status.equals("CHECKED"))
                return "PASS";
            else if(!webElement.isSelected() && status.equals("UNCHECKED"))
                return  "PASS";
            else return "FAIL";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String tryToSelect(WebElement webElement)
    {
        try
        {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().perform();
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String tryToClick(WebElement webElement)
    {
        try
        {
            webElement.click();
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String tryToHover(WebElement webElement)
    {
        try
        {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String scrollAnd(String act, String elementSelector)
    {
        try
        {
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
            for(int i =0;i<3;i++)
            {keyActions("PAGEUP");}
            String result = "";
            int trying = 0;
            if(act.toUpperCase().equals("CLICK"))
                result = tryToClick(webElement);
            else if(act.toUpperCase().equals("SELECT"))
                result = tryToSelect(webElement);
            result = tryToClick(webElement);
            while(trying < 70)
            {
                if(result.toUpperCase().equals("PASS"))
                    break;
                else
                {
                    keyActions("ARROWDOWN");
                    if(act.toUpperCase().equals("CLICK"))
                        result = tryToClick(webElement);
                    else if(act.toUpperCase().equals("SELECT"))
                        result = tryToSelect(webElement);
                    trying = trying + 1;
                }
            }
            trying = 0;
            return result;
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String safeAct(String act, String elementSelector)
    {
        try
        {
            act = act.toUpperCase();
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));

            if(act.toUpperCase().equals("HOVER"))
                return tryToHover(webElement);
            if(act.toUpperCase().equals("CLICK"))
                return tryToClick(webElement);
            else if(act.toUpperCase().equals("SELECT"))
                return tryToSelect(webElement);
            else if(act.equals("HIT"))
                {webElement.sendKeys(Keys.ENTER); return "PASS";}
            else if(act.equals("CLEAR"))
            {webElement.clear(); return "PASS";}
            else {System.out.println(act + " action has not been defined"); return  "FAIL";}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String safeInto(String act, String elementSelector, String text)
    {
        try
        {   if(act.toUpperCase().equals("TYPE"))
            {
                safeAct("click", elementSelector);
                WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
                webElement.sendKeys(text);
                return "PASS";
            }
            else if(act.toUpperCase().equals("ENTER"))
            {
                safeAct("select",elementSelector);
                WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
                Actions action = new Actions(webDriver);
                action.sendKeys(text).perform();
                return "PASS";
            }
            else { System.out.println(act + " action has not been defined");return "FAIL";}
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String takeScreenShot(String dest)
    {
        try
        {
            TakesScreenshot ts = (TakesScreenshot)webDriver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(dest);
            FileUtils.copyFile(source,destination);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String selectFromDropDownBy(String elementSelector,String what, String text)
    {
        try
        {
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
            Select select = new Select(webElement);
            if(what.equals("text"))
            select.selectByVisibleText(text);
            else if(what.equals("index"))
                select.selectByIndex(Integer.parseInt(text));
            else if(what.equals("value"))
                select.selectByValue(text);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String clickFromDropDownBy(String dropdownSelector, String optionsSelector, String text, String attribute)
    {
        try
        {
            safeAct("select", dropdownSelector);
            sleep(500);
            waitToAppear(dropdownSelector);
            List<WebElement> options = webDriver.findElements(By.xpath(optionsSelector));
            for(WebElement option : options)
            {
                String currenText = "null";
                if(attribute.equals("text"))
                    currenText = option.getText();
                if(attribute.equals("value"))
                    currenText = option.getAttribute("value");
                if(attribute.equals("placeholder"))
                    currenText = option.getAttribute("placeholder");

                if(currenText.equals(text))
                {
                    tryToSelect(option);
                    break;
                }
            }
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String isSelected(String elementSelector, String status)
    {
        try
        {
            status = status.toUpperCase();
            WebElement webElement = webDriver.findElement(By.xpath(elementSelector));
            if(status.equals("SELECTED"))
                if(webElement.isSelected())
                    return "PASS";
                else return "FAIL";

            else if(status.equals("UNSELECTED"))
                if(!webElement.isSelected())
                    return "PASS";
                else return "FAIL";
            else { System.out.println(status + " has not been defined");return "FAIL";}
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String uploadFileWithKey(String fileName)
    {
        try
        {
            String path = System.getProperty("filePath") + fileName;
            sleep(1000);
            StringSelection string = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(string,null);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            sleep(2000);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String uploadFile(String browserButtonSelector, String fileName)
    {
        try
        {
            String path = System.getProperty("filePath") + fileName;
            webDriver.findElement(By.xpath(browserButtonSelector)).sendKeys(path);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String selectNthElement(String elementSelector , String index)
    {
        try
        {
          List<WebElement> list = webDriver.findElements(By.xpath(elementSelector));
          list.get(Integer.parseInt(index)).click();
           return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }


}
