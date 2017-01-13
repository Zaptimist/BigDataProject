import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Frank on 10-1-2017.
 */
public class Actress extends Command {
    public String regex = "----\t\t\t------";
    public String endRegex = "-----------------------------------------------------------------------------";
    public String resultName = "Actress.csv";

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