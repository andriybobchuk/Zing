package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zing");
        primaryStage.setScene(new Scene(root, 932, 660));
        primaryStage.show();

        //Call method autosave() from class Controller
//        Controller cont = new Controller();
//        cont.autosave();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
