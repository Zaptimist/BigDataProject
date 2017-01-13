import java.io.*;

/**
 *
 * @author Ruud
 */
public class ActorsAge extends Command{
    public String resultName = "ActorsAge.csv";
    String readFile(File file) throws IOException {
        String result = "";
        result += "Surname,Firstname,Date Of Birth,dateOfDeath" + "\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;

        String begin = "BY: Fab";
        String end = "\n{2}";

        String nm = "";
        String db = "";
        String dd = "";
        boolean foundBegin = false;
        while((line = br.readLine()) != null)
        {
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
                        result += nm +","+ db +", NULL" + "\n";
                        nm = "";
                        db = "";
                        dd = "";
                    }

                    db = line.replaceAll("DB:","");
                    db = db.split(",",2)[0];
                }

                if (line.contains("NM:") && line.contains(","))
                {
                    if(nm != "" && db != "")
                    {
                        result += nm +","+ db +", NULL" + "\n";
                        nm = "";
                        db = "";
                        dd = "";
                    }

                    nm = line.replaceAll("NM:", "");
                }
                if (line.contains("DD"))
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
                        result += nm +","+ db + "," + dd + "\n";
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
        br.close();

        return result;
    }
}