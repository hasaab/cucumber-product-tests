package products.YouTube.Pages;

import java.util.HashMap;

public class HomePage {

    public static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("search_field", "//*[@id=\"search\"]");
        elementLocators.put("search_submit", "//*[@id=\"search-icon-legacy\"]");
        elementLocators.put("search_result1_image", "//*[@id=\"img\"]");
        elementLocators.put("search_result1_title", "//*[@id=\"video-title\"]");

        elementLocators.put("search_result_2_title", "//*[contains(text(),'TOP 6 Things To Do')]");

        elementLocators.put("menu", "//div[@id='container']//yt-icon[@id='guide-icon']");

        elementLocators.put("menu1", "//span[contains(text(),'Home')]");



        elementLocators.put("", "");
        elementLocators.put("", "");
        elementLocators.put("", "");
    }

    public static String getElementSelector(String element_name)
    {
        setElements();
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }

}
