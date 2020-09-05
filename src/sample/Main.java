package sample;

import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
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
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zing 1.78");
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



//        URL location = getClass().getResource("sample.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(location);
//        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
//        Parent root1 = (Parent) fxmlLoader.load(location.openStream());
//        Scene scene = new Scene(root1);
//        primaryStage.setScene(scene);
//        Controller mainController = fxmlLoader.getController();
//        mainController.setStage(primaryStage);
//        mainController.showStage();


        Controller cont = new Controller();//Gets Controller class
//        boolean firstsavedone = cont.firstSaveDone;
//        if(firstsavedone)
//        {
//            //Sets autosave
//            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//            executorService.scheduleWithFixedDelay(() -> cont.autoSave(), 0, 1, TimeUnit.SECONDS);
//        }

        //Exits autosave process on app exit
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
//                System.out.println("File saved for the last time here");
//                try {
//                    cont.lastSave();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//                System.out.println("File saved for the last time there");
                Platform.exit();
                System.exit(0);
            }
        });



    }


    public static void main(String[] args) {
        launch(args);
    }
}
