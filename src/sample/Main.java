package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zing note");
        primaryStage.setScene(new Scene(root, 932, 660));
        primaryStage.show();



        //Call method autosave() from class Controller
//        Controller cont = new Controller();
//        cont.autosave();


//        Text text = new Text(ta_TextArea.getText());
//        Font font = Font.loadFont("file:Resources/fonts/NinaCTT.ttf", 45);
//        text.setFont(font);
//        text.setFill(Color.AQUAMARINE);



//        Controller cont = new Controller();
//        TextArea ta_textarea = cont.ta_TextArea;
//        ta_textarea.setWrapText(true);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
