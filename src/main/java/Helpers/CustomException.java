package Helpers;

public class CustomException extends RuntimeException {

    public CustomException(Exception e, String message){

        System.out.println(message + e.getMessage());
    }
}
