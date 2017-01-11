import java.io.*;

/**
 * Created by Frank on 10-1-2017.
 */
public class Actors extends Command {
    public String regex = "----\t\t\t------";
    public String endRegex = "-----------------------------------------------------------------------------";
    public String resultName = "Actors.csv";

    String readFile(File file) throws IOException {
        String result = "";
        result += "Surname,Name,Movie,Year" + "\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        String actorName = "";
        boolean hasFoundRegex = true;
        while((line = br.readLine()) != null){
            if(hasFoundRegex)
            {
                if(findSummary(line,endRegex))
                    break;
                System.out.println(line);
                if(line.length() > 0)
                {
                    //vind de index van het eerst voorkomende haakje en zet daar een comma voor
                    int in = line.indexOf("(");
                    if(in > -1)
                    {
                        StringBuffer sb =new StringBuffer(line);
                        line = sb.insert(in,",").toString();
                    }
                    if(Character.isWhitespace(line.charAt(0)))
                    {
                        if(line.contains("\"")){
                            line = "";
                        }else{
                            line = line.replaceFirst("\t",",");
                            line = line.replaceAll("\t","");
                            line = actorName + line;
                        }
                    }else{
                        actorName = line.split("\t",2)[0];
                        actorName = actorName.replaceFirst(" ","");
                        System.out.println(actorName);
                        if(line.contains("\""))
                            line = "";
                        if(line.contains("(")){
                            if(line.indexOf(",") > -1) {
                                line = line.replaceFirst(" ", "");
                                line = line.replaceAll("((?s)(\t).*?([^a-zA-Z0-9\\\\s]))", ",");
                            }else{
                                line = line.replaceFirst("\t",",");
                                line = line.replaceAll("\t","");
                            }
                        } else {
                            line = line.replaceAll("\t","");
                        }
                    }
                    line = line.replaceAll("(?:\\D(?=[^(]*\\))|\\)\\s*)","");
                    line = line.trim().replaceAll("  +","");
                    int ind = line.lastIndexOf(" ");
                    if(ind >= 0){
                        line = new StringBuilder(line).replace(ind,ind+1,"").toString();
                    }
                    if(hasFoundRegex && line != "")
                        result += line.replaceAll("((?s)(<|\\[|\").*?(>|\\]|\\}))","") + "\n";
                }
            } else {
                System.out.print("no regex");
                hasFoundRegex = findSummary(line,regex);
            }
        }
        br.close();
        return result;
    }
}