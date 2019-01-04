package GUI;


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
        list.getItems().addAll(GUI.Solution.getInstance(allArtists).getallArtists());
    }
}