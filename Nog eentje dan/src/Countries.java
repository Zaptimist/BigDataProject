import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Frank on 10-1-2017.
 */
public class Countries extends Command{
    public String resultName = "Countries.csv";
    public String regex = "==============";
    public String endRegex = "--------------------------------------------------------------------------------";

    void readFile(File file,File resultFile) throws IOException {
        String result = "";
        result = "Movie,Year,Country";
        Scanner scanner = new Scanner(file,ENCODING.name());
        String line;
        Path p = Paths.get(resultFile.getAbsolutePath());
        BufferedWriter writer = Files.newBufferedWriter(p,ENCODING);
        writer.write(result);
        writer.newLine();
        boolean hasFoundRegex = false;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(hasFoundRegex){
                if(findSummary(line, endRegex))
                    break;
                if(line.length() > 0){
                    if(!startsWithQuote(line)){
                        line = line.replaceAll("\\(\\d{4}\\s\\-\\s\\d{4}\\)", "");
                        line = line.replaceAll("\\(\\d{4}\\-\\d{2}", "");
                        line = line.replaceAll("\"", "");
                        line = removeComma(line);// goed

                        line = removeSpaceAfterBracket(line);//goed
                        line = insertComma(line); // PROBLEMEN
                        line = line.replaceAll("\\((\\?{4})\\)", "NULL"); // goed
                        line = replaceTabsWithComma(line);

                        line = removeBrackets(line);                        
                        line = removeCurly(line);
                        line = line.replaceAll(",,", ",NULL,");
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } else {
                hasFoundRegex = findSummary(line, regex);
            }
        }
        writer.close();
        scanner.close();
    }

}
