import java.io.*;
import java.util.regex.*;
public class Parser 
{
    public static void main(String[] args)
    {
        String fileData = "";
        
        try
        {
            fileData = readFile(new File("C:\\Users\\Sietse\\Desktop\\testmap\\ratingstest.list"));
            
            if(fileData == "")
            {
                System.out.println("no data");
                
                
            }
            writeFile(fileData);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    static String readFile(File file) throws IOException 
    {
        String result = "";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        String begin = "New  Distribution  Votes  Rank  Title";
        String end = "------------------------------------------------------------------------------";
        boolean foundBegin = false;
        while((line = br.readLine()) != null)
        {
            if(line.contains(end))
            {
                line = "";
                break;
            }      
            if(foundBegin)
            {
                if(line.equals(end))
                {
                    break;
                    
                }
                
                if(line.contains("\""))
                {
                    line = "";
                }
                else
                {
                   line = line.replaceAll("\t","");
                   line = line.replaceAll("  ", "");
                }
                result += line.replaceAll(" ", ",") + "\n"; 
            }
           else 
           {
             foundBegin = (line.equals(begin));
           }
           
        } 
        br.close();
        return result;
    }
     static void writeFile(String data) throws IOException
    {
        File resultFile = new File("C:\\Users\\Sietse\\Desktop\\testmap\\resultFile.txt");
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fileOut = new PrintWriter(bw);
        fileOut.println(data);
        fileOut.close();
        System.out.println("done");
    }
}
