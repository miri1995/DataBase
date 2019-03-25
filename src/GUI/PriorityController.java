package GUI;

import Logic.Filters;
import Logic.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriorityController {
    ObservableList<String> genres= FXCollections.observableArrayList("high","middle","low");
    ObservableList<String> loudnesses = FXCollections.observableArrayList("high","middle","low");
    ObservableList<String> tempos = FXCollections.observableArrayList("high","middle","low");

    @FXML
    private ComboBox genrePriority;
    @FXML
    private ChoiceBox loudnessPriority;
    @FXML
    private ChoiceBox tempoPriority;


    /**
     * initializes the options of genre,loudness,tempo for the user.
     * the genre is being read from a file. tempo and loudness can receive 3 values.
     */
    @FXML
    protected void initialize (){
        genrePriority.setItems(genres);
        loudnessPriority.setItems(loudnesses);
        tempoPriority.setItems(tempos);
       // System.out.println("choice");

    }


    @FXML
    /**
     * get the user choices after he clicked "search".
     * @param event - a mouse click event.
     */
    protected void getSol(javafx.event.ActionEvent event){
        try {
            boolean isgenreEmpty = genrePriority.getSelectionModel().isEmpty();
            boolean isloudnessEmpty = loudnessPriority.getSelectionModel().isEmpty();
            boolean istempoEmpty = tempoPriority.getSelectionModel().isEmpty();
            // the user has to choose from all the categories
            if(isgenreEmpty || isloudnessEmpty ||istempoEmpty){
                JOptionPane.showMessageDialog(null, "You need to choose all categories", "Error in choice" , JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(genrePriority.getValue().toString().equals(loudnessPriority.getValue().toString()) || genrePriority.getValue().toString().equals(tempoPriority.getValue().toString()) || loudnessPriority.getValue().toString().equals(tempoPriority.getValue().toString())){
                JOptionPane.showMessageDialog(null, "You need to choose different priorities", "Error in priority" , JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String genrePrioSelected =genrePriority.getValue().toString();
            String loudnessPrioSelected =loudnessPriority.getValue().toString();
            String tempoPrioSelected =tempoPriority.getValue().toString();

            Priority priority =new Priority(genrePrioSelected,loudnessPrioSelected,tempoPrioSelected);



            Parent parent = FXMLLoader.load(getClass().getResource("Solution.fxml"));
            parent.setId("pane");
            Scene scene = new Scene(parent,500,600);
            scene.getStylesheets().addAll(this.getClass().getResource("ResultStyle.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Singers");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



}
