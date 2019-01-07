package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class StartController {

    @FXML
    private javafx.scene.control.Button closeButton;
    
    @FXML
    /**
     *in charge on the gui that opens after the user clicked play.
     * @param event - a mouse click event.
     */
    protected void play(javafx.event.ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Choice.fxml"));

            parent.setId("pane");

            Scene scene = new Scene(parent,500,600);
            scene.getStylesheets().addAll(this.getClass().getResource("ChoiceStyle.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Choice");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     *in charge on the gui that opens after the user clicked play.
     * @param event - a mouse click event.
     */
    protected void exit(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
