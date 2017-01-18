import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author Ruud
 */
public class ActorsAge extends Command{
    public String resultName = "ActorsAge.csv";

    void readFile(File file,File resultFile) throws IOException {
        String result = "";
        result = "Surname,Firstname,DateOfBirth,DateOfDeath";
        Scanner scanner = new Scanner(file,ENCODING.name());
        String line;
        Path p = Paths.get(resultFile.getAbsolutePath());
        BufferedWriter writer = Files.newBufferedWriter(p,ENCODING);
        writer.write(result);
        writer.newLine();

        String begin = "BY: Fab";
        String end = "\n{2}";

        String nm = "";
        String db = "";
        String dd = "";
        boolean foundBegin = false;
        while(scanner.hasNextLine())
        {
            line = scanner.nextLine();
            if(line.contains(end))
            {
                line = "";
                break;
            }

            if(foundBegin)
            {
                line = line.replaceAll("-{79}:", "");
                line = line.replaceAll("(?m)^(?!(NM|DB|DD):).*", "");
                line = line.replaceFirst("\\s", "");

                if (line.contains("DB:"))
                {
                    if(nm != "" && db != "")
                    {
                        result = nm +","+ db +", NULL";
                        writer.write(result);
                        writer.newLine();
                        nm = "";
                        db = "";
                        dd = "";
                    }

                    db = line.replaceAll("DB:","");
                    db = db.split(",",2)[0];
                }else if (line.contains("NM:") && line.contains(","))
                {
                    if(nm != "" && db != "")
                    {
                        result = nm +","+ db +", NULL";
                        writer.write(result);
                        writer.newLine();
                        nm = "";
                        db = "";
                        dd = "";
                    }

                    nm = line.replaceAll("NM:", "");
                } else if (line.contains("DD"))
                {
                    dd = line.replaceAll(",.*$|DD:|(?:\\D(?=[^(]*\\))|\\))", "");
                }

                if(line.contains(end))
                {
                    line = "";
                    break;
                }
                if (line.length() > 0)
                {
                    if(nm != "" && db != "" && dd != "")
                    {
                        result = nm +","+ db + "," + dd;
                        writer.write(result);
                        writer.newLine();
                        nm = "";
                        db = "";
                        dd = "";
                    }
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