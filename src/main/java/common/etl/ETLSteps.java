package common.etl;

import java.io.*;
import java.util.ArrayList;

public class ETLSteps {

    public static ArrayList al1=new ArrayList();
    public static ArrayList al2=new ArrayList();
    public static ArrayList al3=new ArrayList();

    public static String readFile(String file, String path) throws FileNotFoundException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(path + file));
        String dataRow1 = null;
        try {
            dataRow1 = CSVFile.readLine();

            while (dataRow1 != null) {
                String[] dataArray1 = dataRow1.split(",");
                for (String item1 : dataArray1) {
                    if (file.contains("1")){
                    al1.add(item1);}
                    else if (file.contains("2")){
                        al2.add(item1);}
                }
                dataRow1 = CSVFile.readLine(); // Read next line of data.
            }
            CSVFile.close();
        } catch (Exception ex) {
            return "FAIL : " + ex.toString();
        }
        return "PASS : I read the " + file + " from " + path;
    }

    public static String diffFilesAndWriteInto(String diff, String file, String path){
        int size = 0;

        if(diff.equals("file1-file2")) {
            for (Object bs : al2) {
                al1.remove(bs);
            }
            al3 = al1;
        }
        else if(diff.equals("file2-file1")) {
            for (Object bs : al1) {
                al2.remove(bs);
            }
            al3 = al2;
        }
        else if(diff.equals("file1-file2 & file2-file1")) {
            for (Object bs : al2) {
                al1.remove(bs);
            }
            for (Object bs : al1) {
                al2.remove(bs);
            }
            al1.addAll(al2);
            al3 = al1;
        }
        size =al3.size();
     try
        {
            FileWriter writer=new FileWriter(path+file);
            while(size!=0)
            {
                size--;
                writer.append("" + al3.get(size));
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        }
        catch(Exception ex)
        {
            return "FAIL : " + ex.toString();
        }
        return "PASS : I compared the files and noted File1-File2 into " + file + " at " + path;
    }

}
