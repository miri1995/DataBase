package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setTitle("Singers for you");

        root.setId("pane");
        Scene scene = new Scene(root, 600, 600);

        scene.getStylesheets().addAll(this.getClass().getResource("MainStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("music.png")));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
