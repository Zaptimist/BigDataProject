import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author Sietse
 */
public class MovieDuration extends Command
{
    public String resultName = "MovieDuration.csv";
    void readFile(File file,File resultFile) throws IOException
    {
        String result = "";
        result = "Movie,Year,Duration";
        Scanner scanner = new Scanner(file,ENCODING.name());
        String line;
        Path p = Paths.get(resultFile.getAbsolutePath());
        BufferedWriter writer = Files.newBufferedWriter(p,ENCODING);
        writer.write(result);
        writer.newLine();
        String begin = "==================";
        String end = "--------------------------------------------------------------------------------";
        boolean foundBegin = false;
        while(scanner.hasNextLine())
        {
            line = scanner.nextLine();
            if(line.equals(end))
            {
                break;
            }
            if(foundBegin)
            {
                if(line.contains("\""))
                {
                    line = "";
                }
                else
                {
                    line = line.replaceAll("\\(\\d{4}\\s\\-\\s\\d{4}\\)", "");
                    line = line.replaceAll("\\(\\d{4}\\-\\d{2}", "");
                    line = removeComma(line);
                    line = removeSpaceAfterBracket(line);
                    line = line.replaceAll("\\[.*?\\]","");
                    line = insertComma(line);
                    line = removeBrackets(line);
                    line = line.replaceFirst("\t",",");
                    line = line.replaceAll(",,", ",NULL,");
                }
                if(line != "")
                {
                    //result += line.replaceAll("\t","") + "\n";
                    writer.write(line.replaceAll("\t",""));
                    writer.newLine();
                }

            }
            else
            {
                foundBegin = (line.equals(begin));
            }
        }
        scanner.close();
        writer.close();
    }
}
