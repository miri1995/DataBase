package GUI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class SolutionController {

    @FXML
    private ListView<String> list = new ListView<String>();
    private  List<String> allArtists;
    @FXML
    private javafx.scene.control.Button closeButton;


    @FXML
    protected void initialize (){

        allArtists = new ArrayList<String>();

        if (GUI.Solution.getInstance(allArtists).getallArtists().size()==0){
             ObservableList<String> items = FXCollections.observableArrayList (
                     "Sorry dont find solution");
            list.setItems(items);
        }else {
            list.getItems().addAll(GUI.Solution.getInstance(allArtists).getallArtists());

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

    @FXML
    protected void back(javafx.event.ActionEvent event){
       // list.getItems().clear();
     //   GUI.Solution.getInstance(allArtists).clearallArtists();

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
}