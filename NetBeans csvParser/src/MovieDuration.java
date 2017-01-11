import java.io.*;

/**
 *
 * @author Sietse
 */
public class MovieDuration extends Command
{
    public String resultName = "MovieDuration.csv";
    String readFile(File file) throws IOException 
    {
        String result = "";
        //result += "Votes,Grade,MovieTitle,Year" + "\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        String begin = "==================";
        String end = "--------------------------------------------------------------------------------";
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
                if(line.contains("\""))
                {
                    line = "";
                }
                else
                {
                    line = removeComma(line);
                    line = removeSpaceAfterBracket(line);
                    line = line.replaceAll("\\[.*?\\]","");
                    line = insertComma(line);
                    line = removeBrackets(line);
                    line = line.replaceFirst("\t",",");
                    line = line.replaceAll("((?s)(\t).*?(:))","");
                }
                if(line != "")
                {
                result += line.replaceAll("\t","") + "\n";
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
