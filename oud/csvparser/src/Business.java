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
import java.util.ArrayList;
import java.util.List;

public class Business extends Command {
    public String regex = "=============";
    public String endRegex = "----------------------------------------------------------------------------";
    public String resultName = "Business.csv";


    String readFile(File file) throws IOException {

        String result = "Movie,Year,Currency,Gains\n";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        boolean hasFoundRegex = false;
        boolean serieCheck = false;

        //Strings
        String mv = "";
        String bt = "";

        while((line = br.readLine()) != null){
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
                        else
                            mv = line.replaceAll("MV: ", "") + ",";
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
                            result += mv + bt + "\n";
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
        br.close();
        return result;
    }
}