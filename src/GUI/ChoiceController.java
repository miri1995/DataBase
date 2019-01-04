package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.util.ResourceBundle;

public class ChoiceController {
    @FXML
    private ChoiceBox genre;

    /**
     *defines the board size,player's colors,player's names
     *according the read file.
     */
    private void initialize (){
        //GameSettings gameSettings = SettingsReader.readFile();
        String[] g={"h","l"};
        //genre.setItems(g);
        genre.setValue(g);
       // firstPlayerColor.setValue(gameSettings.getPlayer1Color());
        //secondPlayerColor.setValue(gameSettings.getPlayer2Color());
        /*if(gameSettings.getStartingPlayerStr().equals(SettingsInterpreter.kBlack)) {
            firstPlayer.setSelected(true);
        } else {
            secondPlayer.setSelected(true);
        }*/
    }

    @FXML
    /**
     *in charge on the gui that opens after the user clicked play.
     * @param event - a mouse click event.
     */
    protected void getSol(javafx.event.ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Solution.fxml"));
            Scene scene = new Scene(parent,720,600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Singers");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
