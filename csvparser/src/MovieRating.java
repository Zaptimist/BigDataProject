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
        String end = "------------------------------------------------------------------------------";
        boolean foundBegin = false;
        while((line = br.readLine()) != null)
        {
            if(line.equals(end))
            {
                line = "";
                break;
            }
            if(foundBegin)
            {
                if(line.equals(end))
                {
                    line = "";
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
                result += line.replaceAll("^...........", "") + "\n";
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
