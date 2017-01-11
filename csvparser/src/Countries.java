import java.io.*;

/**
 * Created by Frank on 10-1-2017.
 */
public class Countries extends Command {
    public String resultName = "Countries.csv";
    public String regex = "==============";
    public String endRegex = "--------------------------------------------------------------------------------";

    @Override
    String readFile(File file) throws IOException {
        String result = "";
        result += "Movie,Year,Country" + "\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        boolean hasFoundRegex = false;
        while((line = br.readLine()) != null){
            if(hasFoundRegex){
                if(findSummary(line, endRegex))
                    break;
                if(line.length() > 0){
                    if(!startsWithQuote(line)){
                        line = removeSpaceAfterBracket(line);
                        line = insertComma(line);
                        line = removeBrackets(line);
                        line = replaceTabsWithComma(line);
                        result += line + "\n";
                    }
                }
            } else {
                hasFoundRegex = findSummary(line, regex);
            }
        }
        br.close();
        return result;
    }

}
