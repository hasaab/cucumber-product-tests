package common.util;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
import java.util.Random;

public class DataGen {

  public static HashMap<String,String> MyMap = null;

  public static HashMap<String, String> dataToMap(DataTable table, int valueColumn)
  {
    HashMap<String,String> verticalMap = new HashMap<String, String>();
    HashMap<String,String> horizontalMap = new HashMap<String, String>();

    for (DataTableRow row : table.getGherkinRows())
    {
      verticalMap.put(row.getCells().get(0),row.getCells().get(valueColumn));
    }
    return verticalMap;

  }

  public static void printMap(HashMap<String,String> map) {
    StringBuilder sb = new StringBuilder();
    Iterator<Entry<String, String>> iter = map.entrySet().iterator();
    while (iter.hasNext()) {
      Entry<String, String> entry = iter.next();
      sb.append(entry.getKey());
      sb.append('=').append('"');
      sb.append(entry.getValue());
      sb.append('"');
      if (iter.hasNext()) {
        sb.append(',').append(' ');
      }
    }
    System.out.println(sb.toString());

  }

  public static String hashMapToString(HashMap map)
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    XMLEncoder xmlEncoder = new XMLEncoder(bos);
    xmlEncoder.writeObject(map);
    xmlEncoder.flush();

    String serializedMap = bos.toString();

    return serializedMap;
  }

  public static String generateRandomString(int length , String startWith)
  {
    String datetimestring = DateTime.now().toString("yyyyMMddHHmmss");
    int random_lenght = length - startWith.length();
    int remove = datetimestring.length() - random_lenght;
    String random_string = (datetimestring.substring(1, remove));
    random_string = startWith + random_string;
    return random_string;
  }

  public static String generateRandomIntString(int min , int max)
  {
    String randomIntString;
    Random rand = new Random();
    int randomInt = rand.nextInt((max - min) + 1) + min;
    randomIntString = Integer.toString(randomInt);
    return randomIntString;
  }

  public static String getDynamicDate(String value)
  {
    DateTime newDate = new DateTime();
    value = value.replace("system_date", "");
    if (value.contains("-"))
    {
      int offset = Integer.parseInt(value.replace("-", ""));
      newDate = DateTime.now().plusDays(offset);
    }
    if (value.contains("+"))
    {
      int offset = Integer.parseInt(value.replace("+", ""));
      newDate = DateTime.now().plusDays(offset);
    }

    String newDateString = newDate.toString("ddMMyyyy");
    return newDateString;
  }

  public static boolean isValidEmailAddress(String email) {
    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(email);
    return m.matches();
  }


}

