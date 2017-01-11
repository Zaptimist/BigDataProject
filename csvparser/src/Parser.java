/**
 * Created by Frank on 20-12-2016.
 */
import javax.swing.*;
import java.io.*;
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

        p.filePath = GUI.file;
        p.outPut = GUI.destination;
        p.fileNr = GUI.filetype;
        String fileData = "";
        try{
            switch(p.fileNr){
                case 0:
                    fileData = p.doActors(p.filePath);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    fileData = p.doBusiness(p.filePath);
                    break;
                case 4:
                    fileData = p.doCountries(p.filePath);
                    break;
                case 5:
                    break;
            }
            if(fileData != ""){
                p.writeFile(fileData,p.outPut);
            }else{
                JOptionPane.showMessageDialog(null,"No data");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"we got an exception");
            System.out.println();
        }
        JOptionPane.showMessageDialog(null,"Done, file saved to:"+ p.outPut);
        GUI.setVisible(false);
    }


    String doActors(String filePath) throws IOException{
        Actors a = new Actors();
        this.resultName = a.resultName;
        return a.readFile(new File(filePath));
    }

    String doCountries(String filePath) throws IOException{
        Countries c = new Countries();
        this.resultName = c.resultName;
        return c.readFile(new File(filePath));
    }

    String doBusiness(String filePath) throws IOException{
        Business b = new Business();
        this.resultName = b.resultName;
        return b.readFile(new File(filePath));
    }

    void writeFile(String data,String path) throws IOException{
        File dir = new File(path);
        File resultFile = new File(dir,resultName);
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fileOut = new PrintWriter(bw);
        fileOut.println(data);
        fileOut.close();
        System.out.println("done");
    }

}
