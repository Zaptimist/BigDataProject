/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johannes
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Business extends Command {
    public String regex = "=============";
    public String endRegex = "----------------------------------------------------------------------------";
    public String resultName = "Business.csv";


    String readFile(File file) throws IOException {

        String result = "";
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        boolean hasFoundRegex = false;
        boolean addBegin = true;

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
                    if (addBegin) {
                        result = "Movie,Year,Currency,Gains\n";
                        addBegin = !addBegin;
                    }

                    line = line.replaceFirst("\\(",",");

                    if (line.startsWith("MV: ")) {
                        mv = line.replaceAll("MV: ", "") + ",";
                    }

                    if (line.startsWith("BT:")) {
                        bt = line.replaceAll("BT: ", "").replaceAll("[,]", "").replaceAll(" ", ",");

                    }

                    if(hasFoundRegex){

                        if (mv != ""&& bt != "")
                        {

                            mv = mv.replaceAll("[)]|[(]", "").replaceAll("[\\s][,]", ",");
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