import java.io.*;
import java.util.regex.*;

public class Javaparser 
{
    
    static Javaparser p = new Javaparser();
    static Parser GUI = new Parser();
    
    public static void main(String[] args) {
        
        
        
        GUI.setVisible(true);
       
         while(!GUI.hasStarted)
        {
          try
          {
              p.wait(100);
          }
          catch(Exception e)
          {
              
          }
        }
        
        
        String fileData = "";
        try
        {
 
            fileData = readFile(new File(GUI.file));
            fileData = cleanData(fileData);
            if(fileData == "")
            System.out.println("no data");
            writeFile(fileData);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
       
}
 
 
    static String readFile(File file) throws IOException {
        String result = "";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
            result += line + "\n";
        }
        br.close();
        return result;
    }
    static String cleanData(String data)
    {
    //return data.replaceAll("[^a-zA-Z0-9\\s]","");
    return data.replaceAll("(?s)(<|\\[).*?(>|\\])","");
    }
    
    static void writeFile(String data) throws IOException
    {   
        String filename = "resultFile.csv";
        File directory = new File(GUI.destination);
        File resultFile = new File(directory, filename);
        
        resultFile.createNewFile();
        
        
        FileWriter fw = new FileWriter(resultFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fileOut = new PrintWriter(bw);
        fileOut.println(data);
        fileOut.close();
        System.out.println("done");
    }
 
 
 
}