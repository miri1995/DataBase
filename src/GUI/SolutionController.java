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


/**
 * SolutionController class - responsibe of the result's screen.
 * (the final screen)
 */
public class SolutionController {

    @FXML
    private ListView<String> list = new ListView<String>();
    private  List<String> allArtists;
    @FXML
    private javafx.scene.control.Button closeButton;


    /**
     * presents the singer's list according to the user's choices before;
     */
    @FXML
    protected void initialize (){
        allArtists = new ArrayList<String>();
        if (Logic.Solution.getInstance(allArtists).getallArtists().size()==0){
            ObservableList<String> items = FXCollections.observableArrayList (
                    "Sorry dont find solution");
            list.setItems(items);
        }else {
            list.getItems().addAll(Logic.Solution.getInstance(allArtists).getallArtists());

        }
    }

    @FXML
    /**
     * after the results are presented the user can click exit.
     * @param event - a mouse click event.
     */
    protected void exit(ActionEvent event){
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void back(javafx.event.ActionEvent event){

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