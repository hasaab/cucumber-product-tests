package Test.Web;

import Product.LinkedIn.Pages.LinkedInWebMain;
import Product.LinkedIn.Pages.LinkedInWebSignIn;
import Product.LinkedIn.Pages.LinkedInWebSignOut;
import Product.YouTube.Pages.YouTubeWebMain;

public class AllPages {

  public static String currentProductName = null;
  public static String currentPageName = null;

  public static String getElementLocator(String elementName)
  {
    String elementLocator = null;

    if (currentProductName.contains("YouTubeWeb"))
    {
      elementLocator = getYouTubeElementLocator(elementName);
      return elementLocator;
    }
    else if (currentProductName.contains("LinkedInWeb"))
    {
      elementLocator = getLinkedInElementLocator(elementName);
      return elementLocator;
    }
    else
    {
      System.out.println(currentProductName + " pages have not defined yet in the test pages AllPages");
      return elementLocator;
    }

  }

  public static String getYouTubeElementLocator(String elementName)
  {
    String elementLocator = null;
    if (currentPageName.contains("Main"))
    {
      elementLocator = YouTubeWebMain.getELementLocator(elementName);
      return elementLocator;
    }
    else
    {
      System.out.println(currentPageName + " elementLocator has not defined yet in the test pages AllPages");
      return elementLocator;
    }
  }

  public static String getLinkedInElementLocator(String elementName)
  {
    String elementLocator = null;
    if (currentPageName.contains("SignIn"))
    {
      elementLocator = LinkedInWebSignIn.getELementLocator(elementName);
      return elementLocator;
    }
    else if (currentPageName.contains("Main"))
    {
      elementLocator = LinkedInWebMain.getELementLocator(elementName);
      return elementLocator;
    }
    else if (currentPageName.contains("SignOut"))
    {
      elementLocator = LinkedInWebSignOut.getELementLocator(elementName);
      return elementLocator;
    }
    else
    {
      System.out.println(currentPageName + " elementLocator has not defined yet in the test pages AllPages");
      return elementLocator;
    }
  }


}

