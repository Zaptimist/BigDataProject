/**
 * Created by Frank on 20-12-2016.
 */
import javax.swing.*;
import java.io.*;
public class Parser {

    //fileNr bepaald welk soort bestand we gaan parsen
    public static int fileNr = 0;
    String resultName;

    public static void main(String[] args) {
        Parser p = new Parser();
        String fileData = "";
        try{
            switch(fileNr){
                case 0:
                    fileData = p.doActors();
                    break;
                case 1:
                    fileData = p.doCountries();
                    break;
            }
            if(fileData != ""){
                p.writeFile(fileData);
            }else{
                JOptionPane.showMessageDialog(null,"No data");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"we got an exception");
            System.out.println();
        }
    }


    String doActors() throws IOException{
        Actors a = new Actors();
        this.resultName = a.resultName;
        return a.readFile(new File(a.filePath));
    }

    String doCountries() throws IOException{
        Countries c = new Countries();
        this.resultName = c.resultName;
        return c.readFile(new File(c.filePath));
    }

    void writeFile(String data) throws IOException{
        File resultFile = new File(resultName);
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fileOut = new PrintWriter(bw);
        fileOut.println(data);
        fileOut.close();
        System.out.println("done");
    }

}
