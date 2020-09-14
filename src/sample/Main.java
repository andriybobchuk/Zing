package sample;

import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {


    private static final int COUNT_LIMIT = 500000;

    @Override
    public void start(Stage primaryStage) throws Exception{
      //  primaryStage.getIcons().add(new Image("file: D:/DOCUMENTS/Java_Sources/Zing/src/sample/Resources/images/2.png"));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zing 1.78");
        primaryStage.setScene(new Scene(root, 932, 660));
        primaryStage.show();

        new FadeIn(root).play();




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


//    @Override
//    public void init() throws Exception
//    {
//        for(int i = 0; i < COUNT_LIMIT; i++)
//        {
//            double progress = (100 * i) / COUNT_LIMIT;
//            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
//        }
//    }

    public static void main(String[] args) {
        launch(args);

        //LauncherImpl.launchApplication(Main.class, Preloader.class, args);
    }
}
