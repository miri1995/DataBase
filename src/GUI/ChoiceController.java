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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * ChoiceController class - responsible of the user's choices screen.
 * (the second screen)
 */
public class ChoiceController {
    ObservableList<String> genres= FXCollections.observableArrayList("");
    ObservableList<String> loudnesses = FXCollections.observableArrayList("Weak","Normal","Strong");
    ObservableList<String> tempos = FXCollections.observableArrayList("Slow","Medium","Fast");

    @FXML
    private ComboBox genre;
    @FXML
    private ChoiceBox loudness;
    @FXML
    private ChoiceBox tempo;


    /**
     * initializes the options of genre,loudness,tempo for the user.
     * the genre is being read from a file. tempo and loudness can receive 3 values.
     */
    @FXML
    protected void initialize (){
        ReadFileGenre();
        genre.setItems(genres);
        loudness.setItems(loudnesses);
        tempo.setItems(tempos);
        //   System.out.println("choice");

    }


    @FXML
    /**
     * get the user choices after he clicked "search".
     * @param event - a mouse click event.
     */
    protected void getSol(javafx.event.ActionEvent event){
        try {
            boolean isgenreEmpty = genre.getSelectionModel().isEmpty();
            boolean isloudnessEmpty = loudness.getSelectionModel().isEmpty();
            boolean istempoEmpty = tempo.getSelectionModel().isEmpty();
            // the user has to choose from all the categories
            if(isgenreEmpty || isloudnessEmpty ||istempoEmpty){
                JOptionPane.showMessageDialog(null, "You need to choose all categories", "Error in choice" , JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String genreSelected =genre.getValue().toString();
            String loudnessSelected =loudness.getValue().toString();
            String tempoSelected =tempo.getValue().toString();
            Filters filter=new Filters(genreSelected,loudnessSelected,tempoSelected);


            Parent parent = FXMLLoader.load(getClass().getResource("Priority.fxml"));
            parent.setId("pane");
            Scene scene = new Scene(parent,500,600);
            scene.getStylesheets().addAll(this.getClass().getResource("ChoiceStyle.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Singers");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * reads the file that contains the genres.
     */
    protected void ReadFileGenre(){
        BufferedReader br = null;
        String genreToAdd="";
        List<String> genreList=new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("src\\genres.txt"));
            while((genreToAdd = br.readLine()) != null) {
                genreList.add(genreToAdd);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
        this.genres = FXCollections.observableArrayList(genreList);
    }
}