package GUI;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class StartController {

    @FXML
    /**
     *in charge on the gui that opens after the user clicked play.
     * @param event - a mouse click event.
     */
    protected void play(javafx.event.ActionEvent event){
        try {
          //  Parent parent = FXMLLoader.load(getClass().getResource("reversiGame.fxml"));
          //  Scene scene = new Scene(parent,720,600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Reversi game");
           // stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
