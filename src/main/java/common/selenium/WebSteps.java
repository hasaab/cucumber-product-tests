package common.selenium;

import common.util.DataHelp;
import common.util.FileHelp;

public class WebSteps extends WebHelp {

    public static String StartWebDriver(String driver) {
        return startWebDriver(driver) +" : "+ "I start the Webdriver with the " + driver + " browser" +"\n";
    }

    public static String StopWebDriver() {
        return stopWebDriver() +" : "+ "I stop the Webdriver with " + System.getProperty("runDriver") + " the browser" +"\n";
    }

    public static String NavigateToHomePage(String pageName) {
        return navigateTo(System.getProperty("mainURL")) +" : "+ "I navigate to the " + System.getProperty("mainURL") + " URL" +"\n";
    }

    public static String NavigateToTheElementLink(String elementName, String elementSelector)
    { return navigateTo(readAttributeOfWebElement(elementSelector,"HREF"))+" : "+ "I navigate to the " + elementName + " link with selector " + elementSelector + "\n";
        }

    public static String OnThePage(String pageName) {
        System.setProperty("activePage", pageName);
        return "PASS : I am on the " + pageName + " page " +"\n";
        }

    public static String SwitchToWindow(int windowNumber) {
        return switchToWindow(windowNumber)  +" : "+ "I switch to the " + windowNumber + " window" +"\n";
    }

    public static String SwitchToDefaultContent() {
        return switchToDefaultContent() +" : "+ "I switch to the default content" +"\n";
    }

    public static String WaitToAppear(String elementName, String elementSelector){
        if(waitToAppear(elementSelector).contains("PASS"))
            return "PASS ";
        else
            return "PASS : Visibility of element " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String WaitToDisappear(String elementName, String elementSelector) {
        if (waitToDisappear(elementSelector).contains("PASS"))
            return "PASS ";
        else
            return "PASS : UnVisibility of element " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String ActTheElement(String act, String elementName, String elementSelector) {
      if (elementName.contains("alert"))
            return handleAlert(act) +" : "+ "I " + act + "ed the alert" +"\n";
        else
            return safeAct(act, elementSelector) +" : "+ "I " + act + "ed the " + elementName + " with selector " + elementSelector +"\n";
    }

    public static String ElementStatusShouldBe(String elementName, String elementSelector, String status) {
       if (status.toUpperCase().equals("SELECTED"))
            return isSelected(elementSelector, status) +" : "+ "The " + elementName + " status should be " + status + " with selector " + elementSelector +"\n";
        else if (status.toUpperCase().equals("CHECKED"))
            return  isChecked(elementSelector, status) +" : "+ "The " + elementName + " status should be " + status + " with selector " + elementSelector +"\n";
        else return " status is not a implemented to assert";
    }

    public static String CheckDownloads(String expectedFileName) {
        if (expectedFileName.contains("Text"))
            expectedFileName = DataHelp.prepText(expectedFileName);
        return FileHelp.checkDownLoad(expectedFileName) +" : "+ "I should find the " + expectedFileName + " file ind the " + System.getProperty("downloadPath") + " folder" +"\n";
    }

    public static String UploadFile(String fileName, String elementName, String elementSelector) {
     if (fileName.contains("Text"))
            fileName = DataHelp.storedTexts.get(Integer.parseInt(fileName.replaceAll("\\D+", "")));
        return uploadFile(elementSelector, fileName) +" : "+ "I upload the " + fileName + " file to the " + elementName + " with selector " + elementSelector +"\n";
    }

    public static String UploadFileWithKey(String fileName, String elementName, String elementSelector) {
      ActTheElement("select", elementName, elementSelector);
        if (fileName.contains("Text"))
            fileName = DataHelp.getStoredText(fileName);
        return uploadFileWithKey(fileName) +" : "+ "I upload the " + fileName + " file to the " + elementName + " with selector " + elementSelector +"\n";
    }

    public static String RenameFile(String fileName, String text) {
        String newName = DataHelp.getStoredText(text);
        return FileHelp.renameFile(fileName, newName) +" : "+ "I rename the " + fileName + " file to the " + newName +"\n";
    }

    public static String DeleteFile(String fileName) {
        fileName = DataHelp.getStoredText(fileName);
        return FileHelp.deleteFile(fileName, System.getProperty("filePath")) +" : "+ "I delete the " + fileName +"\n";
    }

    public static String SelectFromDropDownBy(String text, String attribute, String elementName, String elementSelector) {
        return selectFromDropDownBy(elementSelector, attribute, text) +" : "+ "I select the " + text + " " + attribute + " from the " + elementName + " with selector " + elementSelector +"\n";
    }

    public static String ClickFromDropDownBy(String text, String attribute, String optionName, String optionSelector, String dropDownName, String dropDownSelector) {
       return clickFromDropDownBy(dropDownSelector, optionSelector, text,attribute) +" : "+"I select the " + text + " from the " + dropDownName + " with selector " + dropDownSelector +"\n";
    }

    public static String SelectDateInDatePicker(String date, String datePickerName, String datePickerSelector, String daySelector, String doneButtonSelector) {
        String result = safeAct("select",datePickerSelector) +" : "+ "I select the " + datePickerName + " with selector " + datePickerSelector +"\n";

        String dateToSet = DataHelp.getDynamicDate(date, "yyyyMMdd");
        int nth = Integer.parseInt(dateToSet.substring(4, 6));
        daySelector = daySelector.replace("DD", String.valueOf(nth));

        WaitToAppear("day_button" , daySelector);
        result = result + selectNthElement(daySelector, String.valueOf(nth - 1))  +" : "+ "I select the " + dateToSet + " with selector " + daySelector +"\n";

        WaitToAppear("done_button", doneButtonSelector);
        return result + safeAct("select",doneButtonSelector) +" : "+ "I select the " + doneButtonSelector + " with selector " + datePickerSelector +"\n";
    }

    public static String IntoTheElement(String act, String entry, String elementName, String elementSelector)
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

        return safeInto(act,elementSelector, entry) +" : "+ "I " + act + " the " + entry + " text into the " + elementName + " with selector " + elementSelector +"\n";
    }

    public static String ShouldSeeTheElement(String elementName,String elementSelector)
    {
        return WaitToAppear(elementName,elementSelector)  +" : " + elementName + " should be visible with selector " + elementSelector +"\n";
    }

    public static String ShouldNotSeeTheElement(String elementName,String elementSelector)
    {
        return WaitToDisappear(elementName,elementSelector)  +" : " + elementName + " should not be visible with selector " + elementSelector +"\n";
    }

    public static String ElementTextShouldBe(String elementName, String elementSelector, String attribute, String condition, String text)
    {
        attribute = attribute.toUpperCase();

        if(text.contains("Text"))
            DataHelp.getStoredText(text);

        String currentText = "null";
        if(attribute.equals("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equals("VALUE") || attribute.equals("HREF") || attribute.equals("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector,attribute.toLowerCase());
        else return "FAIL"  +" : "+ "The " + attribute + " attribute test of element has not been implemented" +"\n";

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
        else return "FAIL"  +" : "+ "The " + condition + " condition test of element has not been implemented" +"\n";

        return result + " : " +"The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName + " with selector " + elementSelector +"\n";

    }

    public static String ElementTextShouldNotBe(String elementName, String elementSelector, String attribute, String condition, String text)
    {
        attribute = attribute.toUpperCase();

        if(text.contains("Text"))
            DataHelp.getStoredText(text);

        String currentText = "null";
        if(attribute.equals("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equals("VALUE") || attribute.equals("HREF") || attribute.equals("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector,attribute.toLowerCase());
        else return "FAIL" +" : "+ "The " + attribute + " attribute has not been implemented" +"\n";

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
        else return "FAIL" +" : "+ "The " + condition + " condition test of element has not been implemented" +"\n";

        return result +" : "+"The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName + " with selector " + elementSelector +"\n";

    }

    public static String TakeScreenShot(String dest)
    {
        return takeScreenShot(dest) +" : "+ "I take screenshot and save to " + dest + "\n";
    }

    public static String WaitSomeSec(String wait, String waitFor)
    {
        return sleep(Integer.valueOf(wait)*1000) +" : "+"Wait " + wait + " sec/s for " + waitFor +"\n";
    }

    public static String HitOnTheKeyBoard(String key)
    {
        return keyActions(key) +" : "+ "I hit " + key + " on the keyboard" +"\n";
    }

    public static String StoreElementTextAsTextX(String elementName,String attribute, String elementSelector, String textX)
    {
        String currentText = "null";
        if(attribute.equals("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equals("VALUE") || attribute.equals("HREF") || attribute.equals("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector,attribute.toLowerCase());
        else return "FAIL" +" : "+ "The " + attribute + " attribute has not been implemented" +"\n";

        String text = readTextOfWebElement(elementSelector);
        return DataHelp.storeText(currentText,textX) +" : "+"Store the " + attribute  + " of the " + elementName  + " with selector " + elementSelector + " as " + textX +"\n";
    }

    public static String StoreTextAsTextX(String text, String textX)
    {
        text = DataHelp.prepText(text);
        return DataHelp.storeText(text,textX) +" : "+"Store the " + text + " as " + textX +"\n";

    }



}
