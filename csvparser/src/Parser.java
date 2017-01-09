/**
 * Created by Frank on 20-12-2016.
 */
import java.io.*;
import java.util.regex.*;
public class Parser {
    public static void main(String[] args) {
        String fileData = "";
        try{
            fileData = readFile(new File("C:\\Users\\Frank\\Documents\\GitHub\\BigDataProject\\Ruud\\stringTestText1.txt"));
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
        String actorName = "";
        String regex = "----\t\t\t------";
        String endRegex = "-----------------------------------------------------------------------------";
        boolean hasFoundRegex = false;
        while((line = br.readLine()) != null){
            if(hasFoundRegex)
            {
                if(line.equals(endRegex))
                    break;
                System.out.println(line);
                    if(line.length() > 0)
                    {

                        if(line.contains("\"")){
                            //line = line.replaceAll("((?s)(\").*?(\\}))","");
                            //System.out.println(line);
                        }

                        if(Character.isWhitespace(line.charAt(0)))
                        {
                            //result += line + ",";
                            //line = line.replace("((?s)(\t).*?(.*))",",");
                            //result += line;
                            //System.out.println(line.replace("\t",","));
                            if(line.contains("\"")){
                                line = "";
                            }else{
                                line = line.replaceFirst("\t",",");
                                line = line.replaceFirst("\\(",",");
                                line = line.replaceAll("\t","");
                                line = actorName + line;
                            }

                            //line = line.replaceAll("\t","");
                            //line += ",";

                            //result += line;

                        }else{
                            //result += "\n";
                            //assign actor name value

                            actorName = line.split("\t",2)[0];
                            actorName = actorName.replaceFirst(" ","");
                            System.out.println(actorName);
                            if(line.contains("\""))
                                line = line.replaceAll("((?s)(\").*?(\\}))","");
                            if(line.contains("(")){
                                if(line.indexOf(",") > -1) {
                                    line = line.replaceFirst(" ", "");
                                    line = line.replaceAll("((?s)(\t).*?([^a-zA-Z0-9\\\\s]))", ",");
                                }else{
                                    line = line.replaceFirst("\t",",");
                                    line = line.replaceAll("\t","");
                                }
                                line = line.replaceFirst("\\(",",");
                            } else {
                                line = line.replaceAll("\t","");
                            }
                        }
                        line = line.trim().replaceAll("  +","");
                        int ind = line.lastIndexOf(" ");
                        if(ind >= 0){
                            line = new StringBuilder(line).replace(ind,ind+1,"").toString();
                        }
                        if(hasFoundRegex)
                            result += line.replaceAll("((?s)(<|\\[|\").*?(>|\\]|\\}))|\\(|\\)","") + "\n";
                }

            } else {
                System.out.print("no regex");
                hasFoundRegex = (line.equals(regex));
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
        return data.replaceAll("(?m)\".*\r?\n|\t", "");
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
