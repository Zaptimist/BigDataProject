import java.io.File;
import java.io.IOException;

/**
 * Created by Frank on 10-1-2017.
 */
public abstract class Command {

    public String regex;
    public String endRegex;
    public String resultName;
    public String filePath = "C:\\Users\\Frank\\Desktop\\10kactorslist.txt";



    abstract String readFile(File file) throws IOException;

    static boolean findSummary(String line, String regex){
        return line.equals(regex);
    }

    boolean startsWithQuote(String line){
        return line.startsWith("\"");
    }
    boolean containsQuote(String line) { return line.contains("\"");}

    boolean containsComma(String line){
        return line.contains(",");
    }

    //methode om een comma op de index van het eerst gevonden haakje in een string te plaatsen
    String insertComma(String line){
        String result = "";
        int index = line.indexOf("(");
        if(index > -1){
            StringBuffer sb = new StringBuffer(line);
            result = sb.insert(index,",").toString();
        }
        result = removeSpaceBeforeComma(result, index);
        return result;
    }

    String removeSpaceBeforeComma(String line, int index){
        if(index > 0){
            StringBuffer sb = new StringBuffer(line);
            line = sb.deleteCharAt(index -1).toString();
        }
        return line;
    }

    String removeSpaceBeforeComma(String line){
        int index = line.indexOf(",");
        if(index > 0){
            StringBuffer sb = new StringBuffer(line);
            line = sb.deleteCharAt(index -1).toString();
        }
        return line;
    }

    String removeSpaceAfterComma(String line, int index){
        if(index > -1){
            StringBuffer sb = new StringBuffer(line);
            line = sb.deleteCharAt(index + 1).toString();
        }
        return line;
    }

    String removeSpaceAfterComma(String line){
        int index = line.indexOf(",");
        if(index > -1){
            StringBuffer sb = new StringBuffer(line);
            line = sb.deleteCharAt(index + 1).toString();
        }
        return line;
    }


    String removeSpaceAfterBracket(String line){
        int index = line.indexOf(")");
        if(index > -1){
            if(line.charAt(index + 1) == ' '){
                StringBuffer sb = new StringBuffer(line);
                line = sb.deleteCharAt(index + 1).toString();
            }
        }
        return line;
    }

    String removeBrackets(String line){
        return line.replaceAll("(?:\\D(?=[^(]*\\))|\\))","");
    }


    String replaceTabsWithComma(String line){
        String result = line.replaceFirst("\t",",");
        return result.replaceAll("\t","");
    }




}
