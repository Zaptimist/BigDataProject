import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author Sietse
 */
public class MovieRating extends Command{
    public String resultName = "MovieRating.csv";

    void readFile(File file, File resultFile) throws IOException {
        String result = "";
        result = "Votes,Grade,MovieTitle,Year";
        Scanner scanner = new Scanner(file,ENCODING.name());
        String line;
        Path p = Paths.get(resultFile.getAbsolutePath());
        BufferedWriter writer = Files.newBufferedWriter(p,ENCODING);
        writer.write(result);
        writer.newLine();

        String begin = "New  Distribution  Votes  Rank  Title";
        String textInMid1 = "BOTTOM 10 MOVIES (1500+ VOTES)";
        String textInMid2 = "New  Distribution  Votes  Rank  Title";
        String textInMid3 = "MOVIE RATINGS REPORT";
        String end = "------------------------------------------------------------------------------";
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
                if (line.equals(textInMid1) || line.equals(textInMid2) || line.equals(textInMid3))
                {
                    line = "";

                }
                if(line.equals(end))
                {
                    break;
                }

                if(line.contains("\""))
                {
                    line = "";
                }

                else
                {
                    line = line.trim();
                    line = removeComma(line);
                    line = line.replaceAll("( .\\))|(:\\))|(;\\))|( ;-\\))|( :-\\))|(;-\\))|(:-\\))","");
                    line = replaceTwoSpaceWithComma(line);
                    line = insertComma(line);
                    line = removeBrackets(line);
                }
                if (line != "" )
                {
                    writer.write(line.replaceAll("^...........", ""));
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