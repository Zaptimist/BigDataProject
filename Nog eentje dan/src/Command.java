import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
 * Created by Frank on 10-1-2017.
 */
public abstract class Command {

    public Charset ENCODING = StandardCharsets.ISO_8859_1;
    public String regex;
    public String endRegex;
    public String resultName;
    public String filePath = "C:\\Users\\Frank\\Desktop\\10kactorslist.txt";



    abstract void readFile(File file,File resultFile) throws IOException;

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
        while(index >= 0){
            if(line.length() > index +1){
                if(Character.isDigit(line.charAt(index+1)) && Character.isDigit(line.charAt(index+2)) && Character.isDigit(line.charAt(index+3)) && Character.isDigit(line.charAt(index+4))){
                    StringBuffer sb = new StringBuffer(line);
                    result = sb.insert(index,",").toString();
                    break;
                }
            }
            index = line.indexOf("(",index+1);
        }
        result = removeSpaceBeforeComma(result, index);
        return result;
    }

    String removeSpaceBeforeComma(String line, int index){
        if(index > 0){
            if(line.charAt(index-1) == ' '){
                StringBuffer sb = new StringBuffer(line);
                line = sb.deleteCharAt(index -1).toString();
            }
        }
        return line;
    }

    String removeSpaceBeforeComma(String line){
        int index = line.indexOf(",");
        if(index > 0){
            if(line.charAt(index -1) == ' '){
                StringBuffer sb = new StringBuffer(line);
                line = sb.deleteCharAt(index -1).toString();
            }
        }
        return line;
    }

    String removeSpaceAfterComma(String line, int index){
        if(index > -1){
            StringBuffer sb = new StringBuffer(line);
            if(line.length() > index + 1)
                line = sb.deleteCharAt(index + 1).toString();
        }
        return line;
    }

    String removeSpaceAfterComma(String line){
        int index = line.indexOf(",");
        if(index > -1){
            StringBuffer sb = new StringBuffer(line);
            if(line.length() > index + 1)
                line = sb.deleteCharAt(index + 1).toString();
        }
        return line;
    }

    /**
     * Author Sietse
     * removes all comma's
     * @param line
     * @return a string without comma's
     */
    String removeComma (String line){
        return line.replaceAll(",", "");
    }

    /**
     * Author Sietse
     * @param line
     * @return a string where 2 spaces are replaced wit 1 comma
     */
    String replaceTwoSpaceWithComma (String line){
        return line.replaceAll("\\s{2,}", ",");
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
    
    String removeJunk(String line){
        return line.replaceAll("((?s)(<|\\[).*?(>|\\]))","");
    }
    
    //Haalt {{ en }} weg
    String removeCurly(String line){
        return line.replaceAll("\\{.*?\\}\\}","");
    }
    
    //Haalt (4getallen - 4getallen) weg
    String removeBeginEndYear(String line){
        return line.replaceAll("(?:\\D(?=[^(]*\\)))","").replaceAll("[0-9]{8}", "");
    }
    
    //(24/7)
    String removeDay(String line){
        return line.replaceAll("\\([0-9]{2}\\/[0-9]{1}\\)","");
    }
   

}
