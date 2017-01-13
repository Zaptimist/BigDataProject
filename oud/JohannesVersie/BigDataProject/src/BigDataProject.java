/**
 * Created by Frank on 20-12-2016.
 */
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigDataProject {
    public static void main(String[] args) {
        String fileData = "";
        try{
 
            fileData = readFile(new File("C:/Users/Johanymous/Desktop/smalllistactors.list"));
            changeFile(fileData);
            if(fileData == "")
                System.out.println("no data");
            writeFile(fileData);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
 
    }
 
 
    static String readFile(File file) throws IOException {
        String result = "";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        while((line = br.readLine()) != null){
            //System.out.println(line);
            result += line + "\n";
        }
        
        br.close();
        return result;
    }
 
    static String changeFile(String fileData){
        
       
        
        String[] parts = fileData.split("\n");
    
        
        int i=0;
        while(parts != null){
            //System.out.println(parts[i] + "is een Backslash \n");
            
            if (parts[i].contains("\n")) {
             parts[i] = parts[i].replace("\n", " ");
        }
        
            i++;
            
        }
        
        
        
   
        
        
               
        ///^([\s\S]*)\(([\d{4}]*|\?*)(?:\/)?([\w]*)?\)(\s*{([\w!\s:;\/\.\-\'"?`_&@$%^*<>~+=\|\,\(\)]*)(\s*\(#([\d]*)\.([\d]*)\))?})?\s*([\d{4}]*)?(?:-)?([\d{4}]*)?/iu

        String replace = fileData.replace(" ", ",");
        String oneComma = replace.replace(",,", ",");
        //System.out.println(oneComma);
        return oneComma;
    }
    
    static void writeFile(String data) throws IOException{
        File resultFile = new File("resultFile.txt");
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fileOut = new PrintWriter(bw);
        //fileOut.println(data);
        fileOut.close();
        //System.out.println("done");
    }
 
 
}