import java.io.*;

/**
 * Created by Frank on 10-1-2017.
 */
public abstract class Command {

    public String regex;
    public String endRegex;
    public String resultName;
    public String filePath = "D:\\IMDB\\BiographyTest1.txt";

    abstract String readFile(File file) throws IOException;

    static boolean findSummary(String line, String regex){
        return line.equals(regex);
    }
    boolean startsWithQuote(String line){
        return line.startsWith("\"");
    }

    //methode om een comma op de index van het eerst gevonden haakje in een string te plaatsen
    String insertComma(String line){
        String result = "";
        int index = line.indexOf("(");
        if(index > -1){
            StringBuffer sb = new StringBuffer(line);
            result = sb.insert(index,",").toString();
        }
        if(index > 0 && result != ""){
            StringBuffer sb = new StringBuffer(result);
            result = sb.deleteCharAt(index-1).toString();
        }
        return result;
    }

    String removeBrackets(String line){
        return line.replaceAll("(?:\\D(?=[^(]*\\))|\\))","");
    }


    String replaceTabsWithComma(String line){
        String result = line.replaceFirst("\t",",");
        return result.replaceAll("\t","");
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
            if(line.charAt(index + 1) == ' ')
            {
                StringBuffer sb = new StringBuffer(line);
                line = sb.deleteCharAt(index + 1).toString();
            }
        }
        return line;
    }
}
