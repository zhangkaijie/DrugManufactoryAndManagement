package com.drugManufactoryAndManagement.spring.validator;

import java.util.regex.Pattern;

public class StringReplace {

    public static String delspecialsign(String str){
        if(str.indexOf("?")!=-1){
            str = str.replaceAll("[?]", "");
        }
        if(str.indexOf("*")!=-1){
            str = str.replaceAll("[*]", "");
        }
        if(str.indexOf("\\")!=-1){
            str = str.replaceAll("\\\\", "");//去掉\
        }
        if(str.indexOf("/")!=-1){
            str = str.replaceAll("/", "");
        }
        if(str.indexOf(":")!=-1){
            str = str.replaceAll(":", "");
        }
        if(str.indexOf("\"")!=-1){
            str = str.replaceAll("\"", "");
        }
        if(str.indexOf("<")!=-1){
            str = str.replaceAll("<", "");
        }
        if(str.indexOf(">")!=-1){
            str = str.replaceAll(">", "");
        }
        if(str.indexOf("|")!=-1){
            str = str.replaceAll("[|]", "");
        }
        str = str.replaceAll(" ", "");
        return str;
    }

    public static boolean isFloat(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isFormat(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        String regex = "[0-9]{1,3}\\-[0-9]{1,3}\\-[0-9]{1,3}";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }


}
