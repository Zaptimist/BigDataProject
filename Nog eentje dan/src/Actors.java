import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
 
/**
 * Created by Frank on 10-1-2017.
 */
public class Actors extends Command {
    public String regex = "----\t\t\t------";
    public String endRegex = "-----------------------------------------------------------------------------";
    public String resultName = "Actors.csv";
 
    void readFile(File file,File resultFile) throws IOException {
        String result = "";
        result = "Surname,Name,Movie,Year";
        Scanner scanner = new Scanner(file,ENCODING.name());
        String line;
        String actorName = "";
        Path p = Paths.get(resultFile.getAbsolutePath());
        BufferedWriter writer = Files.newBufferedWriter(p,ENCODING);
        writer.write(result);
        writer.newLine();
        boolean hasFoundRegex = false;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
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
                            line = line.replaceAll("\\(\\);", "]");
                            line = replaceTabsWithComma(line);
                            line = removeWrongBracket(line);
                            line = line.replaceAll("\\<3","");
                            line = line.replaceAll("( .\\))|(:\\))|(;\\))|( ;-\\))|( :-\\))|(;-\\))|(:-\\))","");
                            line = insertComma(line);
                            line = line.replaceAll("< ","");
                            line = removeBrackets(line);
                            line = actorName + line;
                        }
                    } else {
                        String[] lines = line.split("\t",2);
                        if(lines.length > 0){
                            actorName = lines[0];
                            actorName = removeSpaceAfterComma(actorName);
                            actorName = actorName.replaceFirst("\\.,", "");
                            actorName = actorName.replaceAll("\"", "");
                            if (actorName.trim().endsWith(",")) {
                                actorName = actorName.substring(0, actorName.lastIndexOf(","));
                               
                            }
                           
                        }
                        if(!containsComma(actorName))
                            actorName += ",";
                        line = lines[1];
                        if(containsQuote(line))
                            line = "";
                        else{
                            line = line.replaceAll("\\(\\);", "]");
                            if(containsComma(line)){
                                line = line.replaceAll(",","");
                            }
                            if(!line.startsWith("\t")){
                                line = "," + line;
                            }
                            line = replaceTabsWithComma(line);
                            line = removeWrongBracket(line);
                            line = line.replaceAll("\\<3","");
                            line = line.replaceAll("< ", "");
                            line = line.replaceAll("( .\\))|(:\\))|(;\\))|( ;-\\))|( :-\\))|(;-\\))|(:-\\))","");
                            line = insertComma(line);
                            line = removeBrackets(line);
                            line = actorName + line;
                        }
                    }
                    if(hasFoundRegex && line != ""){
                        line = line.replaceAll("< ", "");
                        line = removeJunk(line);
                        line = line.trim();
                        if (endsWithComma(line)) {
                            line += "NULL";
                        }
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } else {
                //System.out.print("no regex");
                hasFoundRegex = findSummary(line,regex);
            }
        }
        scanner.close();
        writer.close();
    }
 
    String removeJunk(String line){
        return line.replaceAll("((?s)(<|\\[).*?(>|\\]))","");
    }
 
}