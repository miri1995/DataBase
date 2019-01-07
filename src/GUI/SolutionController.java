package GUI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.List;


public class SolutionController {

    @FXML
    private ListView<String> list = new ListView<String>();

    @FXML
    protected void initialize (){
        List<String> allArtists = new ArrayList<String>();
        if (GUI.Solution.getInstance(allArtists).getallArtists().size()==0){
             ObservableList<String> items = FXCollections.observableArrayList (
                     "Sorry dont find solution");

            list.setItems(items);
        }
            list.getItems().addAll(GUI.Solution.getInstance(allArtists).getallArtists());

    }
}