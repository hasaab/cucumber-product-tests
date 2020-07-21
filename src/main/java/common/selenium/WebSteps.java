package common.selenium;

import common.setup.AllURLs;
import common.util.DataHelp;
import common.util.FileHelp;

public class WebSteps extends WebHelp {

    public static String startWebDriver(String driver) {
        System.setProperty("mainURL", AllURLs.getProductURL());
        System.out.println("MainURL : " + System.getProperty("mainURL"));
        System.out.println("WebDriver : " + System.getProperty("runDriver"));
        System.out.println("SeleniumGrid : " + System.getProperty("seleniumGrid"));
        return startMyWebDriver(driver) +" : "+ "I start the Webdriver with the " + driver + " browser";
    }

    public static String stopWebDriver() {
        return stopMyWebDriver() +" : "+ "I stop the Webdriver with " + System.getProperty("runDriver") + " the browser";
    }

    public static String navigateToHomePage(String pageName) {
        return navigateTo(System.getProperty("mainURL")) +" : "+ "I navigate to the " + System.getProperty("mainURL") + " URL";
    }

    public static String navigateToTheElementLink(String elementName, String elementSelector)
    { return navigateTo(readAttributeOfWebElement(elementSelector,"HREF"))+" : "+ "I navigate to the " + elementName + " link with selector " + elementSelector;
    }

    public static String onThePage(String pageName) {
        System.setProperty("activePage", pageName);
        return "PASS : I am on the " + pageName + " page ";
    }

    public static String switchToWindow(int windowNumber) {
        return switchToWindow(windowNumber)  +" : "+ "I switch to the " + windowNumber + " window";
    }

    public static String switchToDefaultContent() {
        return switchToDefaultContent() +" : "+ "I switch to the default content";
    }

    public static String waitToAppear(String elementName, String elementSelector){
        if(waitToAppear(elementSelector).contains("PASS"))
            return "PASS ";
        else
            return "FAIL : Visibility of element " + elementName + " with selector " + elementSelector;
    }

    public static String waitToDisappear(String elementName, String elementSelector) {
        if (waitToDisappear(elementSelector).contains("PASS"))
            return "PASS ";
        else
            return "FAIL : UnVisibility of element " + elementName + " with selector " + elementSelector;
    }

    public static String actTheElement(String act, String elementName, String elementSelector) {
        if (elementName.contains("alert"))
            return handleAlert(act) +" : "+ "I " + act + "ed the alert";
        else
            return safeAct(act, elementSelector) +" : "+ "I " + act + "ed the " + elementName + " with selector " + elementSelector;
    }

    public static String elementStatusShouldBe(String elementName, String elementSelector, String status) {
        if (status.toUpperCase().equals("SELECTED"))
            return isSelected(elementSelector, status) +" : "+ "The " + elementName + " status should be " + status + " with selector " + elementSelector;
        else if (status.toUpperCase().equals("CHECKED"))
            return  isChecked(elementSelector, status) +" : "+ "The " + elementName + " status should be " + status + " with selector " + elementSelector;
        else return " status is not a implemented to assert";
    }

    public static String checkDownloads(String expectedFileName) {
        if (expectedFileName.contains("Text"))
            expectedFileName = DataHelp.prepText(expectedFileName);
        return FileHelp.checkDownLoad(expectedFileName) +" : "+ "I should find the " + expectedFileName + " file ind the " + System.getProperty("downloadPath") + " folder";
    }

    public static String uploadFile(String fileName, String elementName, String elementSelector) {
        if (fileName.contains("Text"))
            fileName = DataHelp.storedTexts.get(Integer.parseInt(fileName.replaceAll("\\D+", "")));
        return uploadFile(elementSelector, fileName) +" : "+ "I upload the " + fileName + " file to the " + elementName + " with selector " + elementSelector ;
    }

    public static String uploadFileWithKey(String fileName, String elementName, String elementSelector) {
        actTheElement("select", elementName, elementSelector);
        if (fileName.contains("Text"))
            fileName = DataHelp.getStoredText(fileName);
        return uploadFileWithKey(fileName) +" : "+ "I upload the " + fileName + " file to the " + elementName + " with selector " + elementSelector ;
    }

    public static String renameFile(String fileName, String text) {
        String newName = DataHelp.getStoredText(text);
        return FileHelp.renameFile(fileName, newName) +" : "+ "I rename the " + fileName + " file to the " + newName ;
    }

    public static String deleteFile(String fileName) {
        fileName = DataHelp.getStoredText(fileName);
        return FileHelp.deleteFile(fileName, System.getProperty("filePath")) +" : "+ "I delete the " + fileName ;
    }

    public static String selectFromDropDownBy(String text, String attribute, String elementName, String elementSelector) {
        return selectFromDropDownBy(elementSelector, attribute, text) +" : "+ "I select the " + text + " " + attribute + " from the " + elementName + " with selector " + elementSelector ;
    }

    public static String clickFromDropDownBy(String text, String attribute, String optionName, String optionSelector, String dropDownName, String dropDownSelector) {
        return clickFromDropDownBy(dropDownSelector, optionSelector, text,attribute) +" : "+"I select the " + text + " from the " + dropDownName + " with selector " + dropDownSelector ;
    }

    public static String selectDateInDatePicker(String date, String datePickerName, String datePickerSelector, String daySelector, String doneButtonSelector) {
        String result = safeAct("select",datePickerSelector) +" : "+ "I select the " + datePickerName + " with selector " + datePickerSelector ;

        String dateToSet = DataHelp.getDynamicDate(date, "yyyyMMdd");
        int nth = Integer.parseInt(dateToSet.substring(4, 6));
        daySelector = daySelector.replace("DD", String.valueOf(nth));

        waitToAppear("day_button" , daySelector);
        result = result + selectNthElement(daySelector, String.valueOf(nth - 1))  +" : "+ "I select the " + dateToSet + " with selector " + daySelector ;

        waitToAppear("done_button", doneButtonSelector);
        return result + safeAct("select",doneButtonSelector) +" : "+ "I select the " + doneButtonSelector + " with selector " + datePickerSelector ;
    }

    public static String intoTheElement(String act, String entry, String elementName, String elementSelector)
    {
        if(entry.contains("CurrentDate"))
            entry= DataHelp.getDynamicDate(entry,"yyyy-MM-dd");

        if(entry.contains("TimeStamp"))
            entry= DataHelp.getTimeStamp("yyyy-MM-dd-hh-mm-ss");

        if(entry.contains("Text"))
            entry= DataHelp.getStoredText(entry);

        if(elementName.contains("date"))
        {
            safeAct("select",elementSelector);
            for(int i=0;i<10;i++)
                keyActions("ArrowLeft");
        }
        entry = entry.replace("-","").replace(".","").replace("/","");

        return safeInto(act,elementSelector, entry) +" : "+ "I " + act + " the " + entry + " text into the " + elementName + " with selector " + elementSelector ;
    }

    public static String shouldSeeTheElement(String elementName, String elementSelector)
    {
        return waitToAppear(elementName,elementSelector)  +" : " + elementName + " should be visible with selector " + elementSelector ;
    }

    public static String shouldNotSeeTheElement(String elementName, String elementSelector)
    {
        return waitToDisappear(elementName,elementSelector)  +" : " + elementName + " should not be visible with selector " + elementSelector ;
    }

    public static String elementTextShouldBe(String elementName, String elementSelector, String attribute, String condition, String text)
    {
        attribute = attribute.toUpperCase();

        if(text.contains("Text"))
            DataHelp.getStoredText(text);

        String currentText = "null";
        if(attribute.equals("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equals("VALUE") || attribute.equals("HREF") || attribute.equals("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector,attribute.toLowerCase());
        else return "FAIL"  +" : "+ "The " + attribute + " attribute test of element has not been implemented" ;

        if(!text.contains("http"))
            text = DataHelp.prepText(text);

        String result = "FAIL";

        if(condition.toUpperCase().equals("EQUAL"))
        {
            if(currentText.equals(text))
                result = "PASS";
        }

        else if(condition.toUpperCase().equals("CONTAIN"))
        {
            if(currentText.contains(text))
                result = "PASS";
        }
        else return "FAIL"  +" : "+ "The " + condition + " condition test of element has not been implemented" ;

        return result + " : " +"The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName + " with selector " + elementSelector ;

    }

    public static String elementTextShouldNotBe(String elementName, String elementSelector, String attribute, String condition, String text)
    {
        attribute = attribute.toUpperCase();

        if(text.contains("Text"))
            DataHelp.getStoredText(text);

        String currentText = "null";
        if(attribute.equals("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equals("VALUE") || attribute.equals("HREF") || attribute.equals("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector,attribute.toLowerCase());
        else return "FAIL" +" : "+ "The " + attribute + " attribute has not been implemented" ;

        if(!text.contains("http"))
            text = DataHelp.prepText(text);

        String result = "PASS";

        if(condition.toUpperCase().equals("EQUAL"))
        {
            if(currentText.equals(text))
                result = "FAIL";
        }

        else if(condition.toUpperCase().equals("CONTAIN"))
        {
            if(currentText.contains(text))
                result = "FAIL";
        }
        else return "FAIL" +" : "+ "The " + condition + " condition test of element has not been implemented" ;

        return result +" : "+"The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName + " with selector " + elementSelector ;

    }

    public static String takeScreenShot(String dest)
    {
        return takeScreen(dest) +" : "+ "I take screenshot and save to " + dest;
    }

    public static String waitSomeSec(String wait, String waitFor)
    {
        return sleep(Integer.valueOf(wait)*1000) +" : "+"Wait " + wait + " sec/s for " + waitFor ;
    }

    public static String hitOnTheKeyBoard(String key)
    {
        return keyActions(key) +" : "+ "I hit " + key + " on the keyboard" ;
    }

    public static String storeElementTextAsTextX(String elementName, String attribute, String elementSelector, String textX)
    {
        String currentText = "null";
        if(attribute.equals("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equals("VALUE") || attribute.equals("HREF") || attribute.equals("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector,attribute.toLowerCase());
        else return "FAIL" +" : "+ "The " + attribute + " attribute has not been implemented" ;

        String text = readTextOfWebElement(elementSelector);
        return DataHelp.storeText(currentText,textX) +" : "+"Store the " + attribute  + " of the " + elementName  + " with selector " + elementSelector + " as " + textX ;
    }

    public static String storeTextAsTextX(String text, String textX)
    {
        text = DataHelp.prepText(text);
        return DataHelp.storeText(text,textX) +" : "+"Store the " + text + " as " + textX ;

    }



}
