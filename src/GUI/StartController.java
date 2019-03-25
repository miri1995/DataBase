package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * StartController class - responsible of the opening screen.
 */
public class StartController {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    /**
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
          //  System.out.println("start");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     *exit the screen.
     * @param event - a mouse click event.
     */
    protected void exit(ActionEvent event){
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }
}