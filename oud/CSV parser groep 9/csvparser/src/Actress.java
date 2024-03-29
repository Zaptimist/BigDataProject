import java.io.*;

/**
 * Created by Frank & Ruud on 10-1-2017.
 */
public class Actress extends Command {
    public String regex = "----\t\t\t------";
    public String endRegex = "-----------------------------------------------------------------------------";
    public String resultName = "Actress.csv";

    String readFile(File file) throws IOException {
        String result = "";
        result += "Surname,Name,Movie,Year" + "\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        String actorName = "";
        boolean hasFoundRegex = false;
        while((line = br.readLine()) != null){
            if(hasFoundRegex)
            {
                if(findSummary(line,endRegex))
                    break;
                if(line.length() > 0)
                {
                    if(Character.isWhitespace(line.charAt(0))){
                        if(containsQuote(line))
                            line = "";
                        else{
                            if(containsComma(line)){
                                line = line.replaceAll(",","");
                            }

                            line = replaceTabsWithComma(line);
                            line = insertComma(line);
                            line = removeBrackets(line);
                            line = actorName + line;
                        }
                    } else {
                        String[] lines = line.split("\t",2);
                        actorName = lines[0];
                        actorName = removeSpaceAfterComma(actorName);
                        if(!containsComma(actorName))
                            actorName += ",";
                        line = lines[1];
                        if(containsQuote(line))
                            line = "";
                        else{
                            if(containsComma(line)){
                                line = line.replaceAll(",","");
                            }
                            if(!line.startsWith("\t")){
                                line = "," + line;
                            }
                            line = replaceTabsWithComma(line);
                            line = insertComma(line);
                            line = removeBrackets(line);
                            line = actorName + line;
                        }
                    }
                    if(hasFoundRegex && line != ""){
                        line = removeJunk(line);
                        result += line + "\n";
                    }
                }
            } else {
                //System.out.print("no regex");
                hasFoundRegex = findSummary(line,regex);
            }
        }
        br.close();
        return result;
    }

    String removeJunk(String line){
        return line.replaceAll("((?s)(<|\\[).*?(>|\\]))","");
    }

}