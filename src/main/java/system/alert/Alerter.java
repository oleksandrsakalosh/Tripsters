package main.java.system.alert;
import javafx.scene.control.Alert;

/**
 * Class for system that creates new window with message
 */
public class Alerter {
    private String title;
    private String text;

    /**
     * @param title title of window
     * @param text text to show in window
     */
    public Alerter(String title, String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Opens window and sets title and message
     */
    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
