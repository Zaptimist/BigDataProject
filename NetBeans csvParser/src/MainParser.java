/**
 * Created by Frank on 20-12-2016.
 * a.k.a GODCLASS
 */
import javax.swing.*;
import java.io.*;
public class MainParser {

    //fileNr bepaald welk soort bestand we gaan parsen
    public static int fileNr = 3;
    String resultName;

    public static void main(String[] args) {
        MainParser p = new MainParser();
        String fileData = "";
        try{
            switch(fileNr){
                case 0:
                    fileData = p.doActors();
                    break;
                case 1:
                    fileData = p.doCountries();
                    break;
                case 2:
                    fileData = p.doMovieRating();
                    break;
                case 3:
                    fileData = p.doMovieDuration();
                    break;
            }
            if(fileData != ""){
                p.writeFile(fileData);
            }else{
                JOptionPane.showMessageDialog(null,"No data");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
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
    
    String doMovieRating() throws IOException{
    MovieRating mr = new MovieRating();
    this.resultName = mr.resultName;
    return mr.readFile(new File(mr.filePath));
    }
    
    String doMovieDuration() throws IOException{
    MovieDuration md = new MovieDuration();
    this.resultName = md.resultName;
    return md.readFile(new File(md.filePath));
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
