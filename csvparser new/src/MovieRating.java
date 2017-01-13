import java.io.*;

/**
 *
 * @author Sietse
 */
public class MovieRating extends Command{
    public String resultName = "MovieRating.csv";
    String readFile(File file) throws IOException {
        String result = "";
        result += "Votes,Grade,MovieTitle,Year" + "\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        String begin = "New  Distribution  Votes  Rank  Title";
        String textInMid1 = "BOTTOM 10 MOVIES (1500+ VOTES)";
        String textInMid2 = "New  Distribution  Votes  Rank  Title";
        String textInMid3 = "MOVIE RATINGS REPORT";
        String end = "------------------------------------------------------------------------------";
        boolean foundBegin = false;
        while((line = br.readLine()) != null)
        {
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
                    line = replaceTwoSpaceWithComma(line);
                    line = insertComma(line);
                    line = removeBrackets(line);
                }
                if (line != "" )
                {
                    result += line.replaceAll("^...........", "") + "\n";
                }
            }
            else
            {
                foundBegin = (line.equals(begin));
            }
        }
        br.close();

        return result;
    }
}