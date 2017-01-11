import java.io.*;

/**
 * Created by Frank on 10-1-2017.
 */
public class Countries extends Command {
    public String resultName = "Countries.csv";
    public String regex = "==============";

    String readFile(File file) throws IOException {
        String result = "";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        boolean hasFoundRegex = false;
        while((line = br.readLine()) != null){
            if(hasFoundRegex){
                if(line.length() > 0){
                    if(!startsWithQuote(line)){
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
