package utils;

import java.util.HashMap;
import java.util.Map;

import exceptions.WrongLineFormatException;

/**
 * Format contains helper functions aimed to transform or output String data.
 * @author Eduardo Augsuto da Silva Leite
 */
public class Format {
    
    /**
     * Clears the terminal screen
     */
    public static void cls(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /**
     * Splits a line of strings based on a pattern and checks if the length of the resulting array
     * matches the desired length
     * @param str
     * @return String[]
     * @throws WrongLineFormatException
     */
    public static String[] splitLine(String str) throws WrongLineFormatException {
        return splitLine(str, 2);
    }

    /**
     * Split a line of strings based on a pattern and checks if the length of the resulting array
     * matches the desired length 
     * @param str
     * @param ammount
     * @return
     * @throws WrongLineFormatException
     */
    public static String[] splitLine(String str, int ammount) throws WrongLineFormatException {
        String[] credentials = str.split(" ");
        if(credentials.length != ammount){
            throw new WrongLineFormatException("credentials must contain only one space");
        }
        return credentials;
    }

    /**
     * Adds to text terminal colors.<br>
     * Possible colors: red, green and yellow
     * @param text
     * @param color
     * @return String
     */
    public static String color(String text, String color){
        
        Map<String, String> colors = new HashMap<>();

        colors.put("red", "\033[31m");
        colors.put("green",   "\033[32m");
        colors.put("yellow",  "\033[33m");
        colors.put("neutral", "\033[0m");
        
        String code = colors.get("neutral");
        if(colors.containsKey(color)){
            code = colors.get(color);
        }

        return code + text + colors.get("neutral");

    }

    /**
     * Prints text with color option
     * @param data
     * @param color
     */
    public static void print(String data, String color){
        print(color(data, color));
    }

    /**
     * Prints text on terminal using System.out.print
     * @param data
     */
    public static void print(String data){
        System.out.print(data);
    }

    /**
     * Prints text on terminal with a newline
     * @param data
     */
    public static void println(String data){
        print(data+"\n");
    }

    /**
     * Prints text on terminal with newline and color option
     * @param data
     * @param color
     */
    public static void println(String data, String color){
        print(color(data, color)+"\n");
    }

}
