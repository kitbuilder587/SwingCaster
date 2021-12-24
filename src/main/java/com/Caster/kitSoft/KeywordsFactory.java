package com.Caster.kitSoft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class KeywordsFactory {
    public static String[] getKeywords(){
        ArrayList<String>  keywords= new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("comands.txt"));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine().split(" ")[0];
                keywords.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return keywords.toArray(new String[keywords.size()]);
    }
}
