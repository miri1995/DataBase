package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

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
}
