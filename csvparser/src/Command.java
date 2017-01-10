import java.io.File;
import java.io.IOException;

/**
 * Created by Frank on 10-1-2017.
 */
public abstract class Command {

    public String regex;
    public String endRegex;
    public String resultName;
    public String filePath = "C:\\Users\\Frank\\Documents\\GitHub\\BigDataProject\\Ruud\\stringTestText1.txt";

    abstract String readFile(File file) throws IOException;

    static boolean findSummary(String line, String regex){
        return line.equals(regex);
    }

}
