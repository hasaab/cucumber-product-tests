package Product.YouTube.Pages;

import java.util.HashMap;

public class YouTubeWebMain {

  public static HashMap<String, String> elementLocators = new HashMap<String, String>();

  public static void setElements()
  {
    elementLocators.put("search_field", "input#masthead-search-term");
    elementLocators.put("search_submit", "button#search-btn > span");
    elementLocators.put("play_that", "li:nth-child(8) > div > div > div.yt-lockup-content > h3 > a");

  }

  public static String getELementLocator(String element_name)
  {
    setElements();
    String elementLocator = elementLocators.get(element_name);
    return elementLocator;
  }
}
