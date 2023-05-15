package main.java.system.exception;

public class SignInException extends Exception{
    private final String text;

    //default text of exception
    public SignInException(){
        this.text = "Login is already taken";
    }

    /**
     * @return text of exception
     */
    public String getText(){
        return this.text;
    }
}