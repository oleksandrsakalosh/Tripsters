package main.java.system.exception;

/**
 * Exception for cases when user entered wrong city name
 */
public class CityNotFound extends Exception{
    private final String text;

    //default text of exception
    public CityNotFound(){
        this.text = "No such city found";
    }

    /**
     * @return text of exception
     */
    public String getText(){
        return this.text;
    }
}