package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setTitle("Singers for you");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

      //  primaryStage.setScene(new Scene(root, 300, 275));

        //Label label1= new Label("This is the first scene");
   //     Button button1= new Button("Go to scene 2");
     //   button1.setOnAction(e -> primaryStage.setScene(new Scene(root, 600, 275)));
       // VBox layout1 = new VBox(20);
        //layout1.getChildren().addAll(label1, button1);
        //scene1= new Scene(layout1, 300, 250);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
