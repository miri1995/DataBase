package GUI;

import Logic.Solution;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SolutionController {
    ObservableList<String> items = FXCollections.observableArrayList (
            "Beyonce", "Taylor Swift", "Justin Bieber", "Sia");
    @FXML
    private ListView<String> list = new ListView<String>();



    @FXML
    protected void initialize (){
      //  Solution sol=new Solution();
        list.getItems().addAll(items);

    }
}