package GUI;

import Logic.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import javax.swing.*;

public class ChoiceController {
    ObservableList<String> genres = FXCollections.observableArrayList("hip_hop","salsa","rock");
    ObservableList<String> loudnesses = FXCollections.observableArrayList("Weak","Normal","Strong");
    ObservableList<String> tempos = FXCollections.observableArrayList("Slow","Medium","Fast");
    @FXML
    private ChoiceBox genre;
    @FXML
    private ChoiceBox loudness;
    @FXML
    private ChoiceBox tempo;


    /**
     *defines the board size,player's colors,player's names
     *according the read file.
     */
    @FXML

    protected void initialize (){
        //GameSettings gameSettings = SettingsReader.readFile();

        genre.setItems(genres);
        loudness.setItems(loudnesses);
        tempo.setItems(tempos);

    }


    @FXML
    /**
     *in charge on the gui that opens after the user clicked play.
     * @param event - a mouse click event.
     */
    protected void getSol(javafx.event.ActionEvent event){
        try {
            boolean isgenreEmpty = genre.getSelectionModel().isEmpty();
            boolean isloudnessEmpty = loudness.getSelectionModel().isEmpty();
            boolean istempoEmpty = tempo.getSelectionModel().isEmpty();
            if(isgenreEmpty || isloudnessEmpty ||istempoEmpty){
                JOptionPane.showMessageDialog(null, "You need to choose all categories.", "Error in choice" , JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String genreSelected =genre.getValue().toString();
            String loudnessSelected =loudness.getValue().toString();
            String tempoSelected =tempo.getValue().toString();
            Filters filter=new Filters(genreSelected,loudnessSelected,tempoSelected);


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
