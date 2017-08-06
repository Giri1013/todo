package Helpers;

public class StringUtils {

    public static String concatWordsInitLower(String text){
        text = text.replaceAll("\\s","");
        text = text.substring(0,1).toLowerCase()+text.substring(1);
        return text;
    }
}
