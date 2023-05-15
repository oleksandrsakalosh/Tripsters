package main.java.system.exception;

/**
 * Exception for cases when user entered wrong city name
 */
public class UserNotFound extends Exception{
    private final String text;

    //default text of exception
    public UserNotFound(){
        this.text = "No such user found";
    }

    /**
     * @return text of exception
     */
    public String getText(){
        return this.text;
    }
}