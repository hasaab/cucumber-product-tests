package common.util;

import org.apache.commons.io.FileUtils;

import java.io.File;

import static common.util.DataHelp.storedTexts;

public class FileHelp {

    public static String renameFile(String fileName, String newName)
    {
        try
        {
            File source = new File(System.getProperty("filePath") + fileName);
            File destination = new File(System.getProperty("filePath") + newName);
            FileUtils.copyFile(source, destination);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String checkDownLoad(String expectedName)
    {
        try
        {
            File downloadFolder = new File(System.getProperty("downloadPath"));
            File dir = downloadFolder;
            dir.mkdirs();
            File[] listOfFiles = downloadFolder.listFiles();
            Boolean found = false;
            for(File fileX : listOfFiles)
            {
                if(fileX.getName().equals(expectedName))
                    found = true;{
                    fileX.delete();}
            }

            if(found == true)
                return "PASS";
            else return "FAIL";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String deleteFile(String fileName, String path)
    {
        try
        {
            File folder = new File(path);
            File dir = folder;
            dir.mkdirs();
            File[] listOfFiles = folder.listFiles();
            Boolean found = false;
            for(File fileX : listOfFiles)
            {
                for(String nameX : storedTexts) {
                    if (nameX.equals(fileName))
                    {
                        found = true;
                        fileX.delete();
                    }
                }
            }

            if(found == true)
                return "PASS";
            else return "FAIL";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }
}
