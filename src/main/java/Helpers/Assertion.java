package Helpers;

public class Assertion {

    public static Boolean assertEquals(String summary,String expected,String actual,Boolean ignoreCase){
        if(ignoreCase)
           return expected.equalsIgnoreCase(actual);
        return expected.equals(actual);

    }
}
