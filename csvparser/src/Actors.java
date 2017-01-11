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
                                System.out.println("found comma");
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
                                System.out.println("found comma");
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