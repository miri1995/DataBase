package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartController {

    @FXML
    /**
     *in charge on the gui that opens after the user clicked play.
     * @param event - a mouse click event.
     */
    protected void play(javafx.event.ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Choice.fxml"));
            Scene scene = new Scene(parent,720,600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Choice");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
