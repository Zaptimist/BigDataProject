/**
 * Created by Frank on 20-12-2016.
 */
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {

    //fileNr bepaald welk soort bestand we gaan parsen
    public int fileNr = 0;
    String resultName;
    String filePath;
    String outPut;

    public static void main(String[] args) {
        Parser p = new Parser();
        Gui GUI = new Gui();

        GUI.setVisible(true);
        while(!GUI.hasStarted){
            try{
                p.wait(100);
            }catch(Exception e){}
        }
        System.out.println("started");
        p.filePath = GUI.file;
        p.outPut = GUI.destination;
        p.fileNr = GUI.filetype;

        File dir = new File(p.outPut);
        //File resultFile = new File(dir,"test1");
        //try {
        //    resultFile.createNewFile();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        String fileData = "";
        try{
            switch(p.fileNr){
                case 0:
                    p.doActors(p.filePath,p.outPut);
                    break;
                case 1:
                    p.doActress(p.filePath,p.outPut);
                    break;
                case 2:
                    p.doAge(p.filePath,p.outPut);
                    break;
                case 3:
                    p.doBusiness(p.filePath,p.outPut);
                    break;
                case 4:
                    p.doCountries(p.filePath,p.outPut);
                    break;
                case 5:
                    p.doMovieDuration(p.filePath,p.outPut);
                    break;
                case 6:
                    p.doRating(p.filePath,p.outPut);
                    break;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"we got an exception");
            e.printStackTrace();
            System.out.println();
        }
        JOptionPane.showMessageDialog(null,"Done, file saved to:"+ p.outPut);
        GUI.setVisible(false);
    }


    void doActors(String filePath,String outPut) throws IOException{
        Actors a = new Actors();
        this.resultName = a.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,a.resultName);
        a.readFile(new File(filePath),resultFile);
    }

    void doCountries(String filePath,String outPut) throws IOException{
        Countries c = new Countries();
        this.resultName = c.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,c.resultName);
        c.readFile(new File(filePath),resultFile);
    }

    void doBusiness(String filePath, String outPut) throws IOException{
        Business b = new Business();
        this.resultName = b.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,b.resultName);
        b.readFile(new File(filePath),resultFile);
    }

    void doAge(String filePath,String outPut) throws IOException{
        ActorsAge a = new ActorsAge();
        this.resultName = a.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,a.resultName);
        a.readFile(new File(filePath),resultFile);
    }

    void doMovieDuration(String filePath,String outPut) throws IOException{
        MovieDuration m = new MovieDuration();
        this.resultName = m.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,m.resultName);
        m.readFile(new File(filePath),resultFile);
    }

    void doRating(String filePath,String outPut) throws IOException{
        MovieRating r = new MovieRating();
        this.resultName = r.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,r.resultName);
        r.readFile(new File(filePath),resultFile);
    }

    void doActress(String filePath, String outPut) throws IOException{
        Actress a = new Actress();
        this.resultName = a.resultName;
        File f = new File(outPut);
        File resultFile = new File(f,a.resultName);
        a.readFile(new File(filePath),resultFile);
    }

}
