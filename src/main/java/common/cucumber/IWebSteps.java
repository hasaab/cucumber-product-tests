package common.cucumber;

import common.selenium.WebSteps;
import common.setup.AllProducts;
import common.setup.RunnerHooks;
import cucumber.api.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static common.selenium.WebHelp.webDriver;
import static common.selenium.WebSteps.*;
import static common.setup.RunnerHooks.*;


public class IWebSteps {


    public static void IStartTheWebDriver(String driver)
    {AssertExecutedStep(startWebDriver(driver));}


    public static void IStopTheWebDriver()
    {AssertExecutedStep(WebSteps.stopWebDriver());}

    @Given("^I navigate to the Home page")
    public static void INavigateToTheHomePage()
    { System.out.println("************************************************************************************\n");
        AssertExecutedStep(navigateToHomePage(System.getProperty("mainURL")));
        AssertExecutedStep(onThePage("Home"));
    }

    @Given("^I navigate to the \"([^\"]*)\" element link")
    public static void INavigateToTheElementLink(String elementName)
    { VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(navigateToTheElementLink(elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^I am on the \"([^\"]*)\" page")
    public static void IAmOnThePage(String pageName)
    {AssertExecutedStep(onThePage(pageName));}

    @Given("^I switch to the \"([^\"]*)\" window$")
    public static void ISwitchToTheWindow(int windowNumber)
    {AssertExecutedStep(WebSteps.switchToWindow(windowNumber));}

    @Given("^I switch to the default content$")
    public static void ISwitchToTheDefaultContent()
    {AssertExecutedStep(WebSteps.switchToDefaultContent());}

    @Given("^I \"([^\"]*)\" the \"([^\"]*)\"$")
    public static void IActTheElement(String act, String elementName)
    {  VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(actTheElement(act,elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^The \"([^\"]*)\" element status should be \"([^\"]*)\"$")
    public static void TheElementStatusShouldBe(String elementName, String status)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(elementStatusShouldBe(elementName, AllProducts.getElementSelector(elementName),status));}

    @Given("^I should find the \"([^\"]*)\" in the downloads$")
    public static void ICheckDownloads(String expectedFileName)
    {AssertExecutedStep(checkDownloads(expectedFileName));}

    @Given("^I select the \"([^\"]*)\" \"([^\"]*)\" from the \"([^\"]*)\" dropdown$")
    public static void ISelectFromDropDownBy(String text, String attribute, String elementName)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebSteps.selectFromDropDownBy(text,attribute,elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^I click the \"([^\"]*)\" \"([^\"]*)\" from the \"([^\"]*)\" options of the \"([^\"]*)\" dropdown$")
    public static void IClickFromDropDownBy(String text, String attribute,String optionName, String elementName)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebSteps.clickFromDropDownBy(text,attribute,elementName, AllProducts.getElementSelector(elementName),optionName, AllProducts.getElementSelector(optionName)));}

    @Given("^I upload the \"([^\"]*)\" file to the \"([^\"]*)\"$")
    public static void IUploadTheFile(String fileName, String elementName)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebSteps.uploadFile(fileName,elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^I upload the \"([^\"]*)\" file to the \"([^\"]*)\" element with keys$")
    public static void IUploadTheFileWithKeys(String fileName, String elementName)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebSteps.uploadFileWithKey(fileName,elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^I rename the \"([^\"]*)\" file to the \"([^\"]*)\"$")
    public static void IRenameFileTo(String fileName, String textX)
    {AssertExecutedStep(renameFile(fileName,textX));}

    @Given("^I delete the \"([^\"]*)\" file$")
    public static void IDeleteFile(String fileName)
    {AssertExecutedStep(deleteFile(fileName));}

    @Given("^I select the \"([^\"]*)\" \"([^\"]*)\" from the \"([^\"]*)\"$")
    public static void ISelectTheElementBy(String text,String attribute,String dropDownName)
    {VerifyExecutedStep(WebSteps.waitToAppear(dropDownName, AllProducts.getElementSelector(dropDownName)));
        AssertExecutedStep(WebSteps.selectFromDropDownBy(text,attribute,dropDownName, AllProducts.getElementSelector(dropDownName)));}

    @Given("^I click the \"([^\"]*)\" \"([^\"]*)\" from the \"([^\"]*)\"$")
    public static void IClickTheElementBy(String text,String attribute,String selection,String dropDownName)
    {VerifyExecutedStep(WebSteps.waitToAppear(dropDownName, AllProducts.getElementSelector(dropDownName)));
        AssertExecutedStep(WebSteps.clickFromDropDownBy(text,attribute,dropDownName, AllProducts.getElementSelector(dropDownName),selection, AllProducts.getElementSelector(selection)));}

    @Given("^I select the \"([^\"]*)\" date in the \"([^\"]*)\" datepicker$")
    public static void ISelectTheDateInTheDatePicker(String date, String datePickerName)
    {VerifyExecutedStep(WebSteps.waitToAppear(datePickerName, AllProducts.getElementSelector(datePickerName)));
        AssertExecutedStep(selectDateInDatePicker(date,datePickerName, AllProducts.getElementSelector(datePickerName), AllProducts.getElementSelector("day_selector"), AllProducts.getElementSelector("done_button")));}

    @Given("^I \"([^\"]*)\" \"([^\"]*)\" into the \"([^\"]*)\"$")
    public static void IIntoTheElement(String act, String entry, String elementName)
    { VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(intoTheElement(act,entry, elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^I should see the \"([^\"]*)\"$")
    public static void IShouldSeeTheElement(String elementName)
    {AssertExecutedStep(shouldSeeTheElement(elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^I should not see the \"([^\"]*)\"$")
    public static void IShouldNotSeeTheElement(String elementName)
    {AssertExecutedStep(shouldNotSeeTheElement(elementName, AllProducts.getElementSelector(elementName)));}

    @Given("^The \"([^\"]*)\" element \"([^\"]*)\" should \"([^\"]*)\" \"([^\"]*)\"$")
    public static void TheElementTextShouldBe(String elementName, String attribute, String condition, String entry)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(elementTextShouldBe(elementName, AllProducts.getElementSelector(elementName),attribute,condition,entry ));}

    @Given("^The \"([^\"]*)\" element \"([^\"]*)\" should not \"([^\"]*)\" \"([^\"]*)\"$")
    public static void TheElementTextShouldNotBe(String elementName, String attribute, String condition, String entry)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(elementTextShouldNotBe(elementName, AllProducts.getElementSelector(elementName),attribute,condition,entry ));}

    @Given("^I takes screenshot as \"([^\"]*)\"$")
    public static void ITakeScreenShot(String fileName)
    {
        final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        RunnerHooks.scenario.embed(screenshot, "image/jpg"); //stick it in the report
        AssertExecutedStep(WebSteps.takeScreenShot(System.getProperty("reportPath") + "/" + fileName));
    }

    @Given("^I wait \"([^\"]*)\" sec/s for \"([^\"]*)\"$")
    public static void IWaitSomeSec(String wait, String waitFor)
    {AssertExecutedStep(waitSomeSec(wait,waitFor));}

    @Given("^I hit \"([^\"]*)\" on the keyboard$")
    public static void IHitOnTheKeyBoard(String key)
    {AssertExecutedStep(hitOnTheKeyBoard(key));}

    @Given("^I store the \"([^\"]*)\" element text as \"([^\"]*)\"$")
    public static void IStoreTheElementTextAsTheTextX(String elementName, String attribute, String textX)
    {VerifyExecutedStep(WebSteps.waitToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(storeElementTextAsTextX(elementName, AllProducts.getElementSelector(elementName),attribute,textX));}


    public static void IStoreTheTextAsTheTextX(String text,String textX)
    {AssertExecutedStep(storeTextAsTextX(text,textX));}



}
