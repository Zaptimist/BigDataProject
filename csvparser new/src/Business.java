/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johanymous
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Business extends Command {
    public String regex = "=============";
    public String endRegex = "----------------------------------------------------------------------------";
    public String resultName = "Business.csv";


    void readFile(File file, File resultFile) throws IOException {
        String result = "Movie,Year,Currency,Gains";
        Scanner scanner = new Scanner(file,ENCODING.name());
        String line;
        Path p = Paths.get(resultFile.getAbsolutePath());
        BufferedWriter writer = Files.newBufferedWriter(p,ENCODING);
        writer.write(result);
        writer.newLine();
        boolean hasFoundRegex = false;
        boolean serieCheck = false;

        //Strings
        String mv = "";
        String bt = "";

        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(hasFoundRegex)
            {
                if(findSummary(line,endRegex))
                    break;
                if(line.length() > 0)
                {
                    line = line.replaceAll("\\s\\(",",(").replaceAll("\\)(.*)",")");

                    if (line.startsWith("MV: ")) {
                        if (line.contains("\"")) {
                            mv = "";
                            serieCheck = true;
                        }
                        else{
                            mv = line.replaceAll("MV: ", "") + ",";
                            mv = removeComma(mv);
                        }

                    }

                    if (serieCheck && line.startsWith("BT:")) {
                        bt = "";
                        serieCheck = !serieCheck;
                    }

                    else{
                        if (line.startsWith("BT:")) {
                            bt = line.replaceAll("BT: ", "").replaceAll(",", "").replaceAll(" ", ",");
                        }
                    }

                    if(hasFoundRegex){
                        if (mv != ""&& bt != "")
                        {
                            //Removes the ( and ) and all non digits
                            mv = mv.replaceAll("(?:\\D(?=[^(]*\\))|\\)\\s*)","");
                            //Removes all the spaces in the price
                            bt = bt.replaceAll("[\\s{5}]*[,]$", "");
                            result = mv + bt;
                            writer.write(result);
                            writer.newLine();
                            mv = "";
                            bt = "";
                        }
                    }
                }
            }
            else {
                System.out.print("no regex");
                hasFoundRegex = findSummary(line,regex);
            }
        }
        scanner.close();
        writer.close();
    }
}