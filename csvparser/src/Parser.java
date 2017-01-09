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
            //fileData = cleanData(fileData);
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
        //String name;
        while((line = br.readLine()) != null){
            System.out.println(line);
            if(line.length() > 0)
            {
                if(Character.isWhitespace(line.charAt(0)))
                {
                    //result += line + ",";
                    //line = line.replace("((?s)(\t).*?(.*))",",");
                    //result += line;
                    //System.out.println(line.replace("\t",","));
                    line = line.replaceFirst("\t",",");
                    line = line.replaceFirst("\\(",",");
                    line = line.replaceAll("\t","");
                    //line = line.replaceAll("\t","");
                    //line += ",";

                    //result += line;

                }else{
                    result += "\n";
                    line = line.replaceFirst("\\(",",");
                    line = line.replaceAll("((?s)(\t).*?([^a-zA-Z0-9\\\\s]))",",");
                    //result += line;
                }
                line = line.trim().replaceAll("  +","");
                int ind = line.lastIndexOf(" ");
                if(ind >= 0){
                    line = new StringBuilder(line).replace(ind,ind+1,"").toString();
                }
                result += line.replaceAll("((?s)(<|\\[).*?(>|\\]))|\\(|\\)","");
            }

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
