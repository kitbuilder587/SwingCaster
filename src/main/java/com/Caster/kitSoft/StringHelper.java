package com.Caster.kitSoft;

public class StringHelper {
    public static String getStringFrom1ToN(int n){
        StringBuilder res = new StringBuilder("<html>");
        for(int i=0;i<n;i++){
            res.append((i+1)+"<br>");
        }
        return res.toString();
    }
    public static int getLinesCount(String s){
        int res = 1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '\n') res++;
        }
        return res;
    }
}