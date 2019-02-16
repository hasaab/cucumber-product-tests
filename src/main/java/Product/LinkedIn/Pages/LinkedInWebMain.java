package Product.LinkedIn.Pages;

import java.util.HashMap;

public class LinkedInWebMain {

  public static HashMap<String, String> elementLocators = new HashMap<String, String>();

  public static void setElements()
  {
    elementLocators.put("user_profile_image", "button#nav-settings__dropdown-trigger > img");
    elementLocators.put("user_menu_dropdown", "button#nav-settings__dropdown-trigger > img");
    elementLocators.put("user_menu_sign_out_button", "#nav-settings__dropdown-options > li.nav-settings__dropdown-options--actions.nav-settings__no-hover");
    elementLocators.put("1", "");

  }

  public static String getELementLocator(String element_name)
  {
    setElements();
    String elementLocator = elementLocators.get(element_name);
    return elementLocator;
  }

}
