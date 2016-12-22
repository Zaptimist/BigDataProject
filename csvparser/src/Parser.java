/**
 * Created by Frank on 20-12-2016.
 */
import java.io.*;
import java.util.regex.*;
public class Parser {
    public static void main(String[] args) {
        String fileData = "";
        try{

            fileData = readFile(new File("C:/Users/Frank/Desktop/actors.txt"));
            fileData = cleanData(fileData);
            if(fileData == "")
                System.out.println("no data");
            writeFile(fileData);
        }catch(Exception e){
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

   /* static String cleanData(String data){
        Pattern p = Pattern.compile("[^<\\s]");
        Matcher m = p.matcher(data);
        StringBuffer result = new StringBuffer();
        while(m.find()){
            m.appendReplacement(result, m.group() + "");
        }
        m.appendTail(result);
        return result.toString();
    } */

    static String cleanData(String data){
        //return data.replaceAll("[^a-zA-Z0-9\\s]","");
        return data.replaceAll("((?s)(<|\\[).*?(>|\\]))|\\(|\\)","");

    }

    static void writeFile(String data) throws IOException{
        File resultFile = new File("resultFile.txt");
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fileOut = new PrintWriter(bw);
        fileOut.println(data);
        fileOut.close();
        System.out.println("done");
    }

}
