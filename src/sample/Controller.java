package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.JobSettings;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML
    private Button btn_Write;
    @FXML
    private Button btn_Mistakes;
    @FXML
    private Button btn_Style;
    @FXML
    private Button btn_Finish;
    @FXML
    public TextArea ta_TextArea;
    @FXML
    private VBox vb_StylePan;
    @FXML
    private HBox hb_MistakePan;
    @FXML
    private HBox hb_FinishPan;
    @FXML
    private ComboBox cb_Font;
    @FXML
    private Button btn_Print;
    @FXML
    private HBox hb_Menu;
    @FXML
    private Pane p_Hello;
    @FXML
    private Hyperlink hl;
    @FXML
    private Stage app;

    public boolean firstSaveDone = false;


//    public void getTa_Text() {
//        Text text = new Text(ta_TextArea.getText());
//        Font font = Font.loadFont("file:resources/fonts/NinaCTT.ttf", 45);
//        text.setFont(font);
//    }

//    static ObservableList<String> jvmChoices = FXCollections.observableArrayList(
//            "Java",
//            "Kotlin",
//            "Groovy",
//            "Scala",
//            "Clojure"
//    );
//
//    public void getCb_Font(ActionEvent e) {
//        cb_Font.setItems(jvmChoices);
//
//    }

    //onActionPageSetup, createPrinterJob, setPrinterJobSettings, onActionPrint are the four methods for printing
    @FXML
    public void onActionPageSetup(ActionEvent e) {
        PrinterJob printerJob = createPrinterJob();
        printerJob.showPageSetupDialog(ta_TextArea.getScene().getWindow());
        JobSettings jobSettings = printerJob.getJobSettings();
    }

    private PrinterJob createPrinterJob() {
        PrinterJob printerJob = PrinterJob
                .createPrinterJob();
        JobSettings jobSettings = null;
        if (jobSettings == null) {
            jobSettings = printerJob.getJobSettings();
        } else {
            setPrinterJobSettings(printerJob.getJobSettings(), jobSettings);
        }
        return printerJob;
    }

    public void setPrinterJobSettings(JobSettings from, JobSettings to) {
        to.setCollation(from.getCollation());
        to.setCopies(from.getCopies());
        to.setJobName(from.getJobName());
        to.setPageLayout(from.getPageLayout());
        to.setPageRanges(from.getPageRanges());
        to.setPaperSource(from.getPaperSource());
        to.setPrintColor(from.getPrintColor());
        to.setPrintQuality(from.getPrintQuality());
        to.setPrintResolution(from.getPrintResolution());
        to.setPrintSides(from.getPrintSides());
    }

    //Main print method (
    public void onActionPrint(ActionEvent event) {
        PrinterJob printerJob = createPrinterJob();

        boolean result = printerJob.showPrintDialog(ta_TextArea.getScene().getWindow());
        if (result) {
            Text text = new Text(ta_TextArea.getText());
            text.setFont(ta_TextArea.getFont());
            TextFlow textFlow = new TextFlow(text);
            boolean printed = printerJob.printPage(textFlow);
            if (printed) {
                printerJob.endJob();
            }
        }
    }

    //Method getBtn_Write returns app setup to its normal view(closes all tabs)
    public void getBtn_Write(ActionEvent e) {
        vb_StylePan.setVisible(false); //Hides style panel
        hb_MistakePan.setVisible(false); //Hides mistakes panel
        hb_FinishPan.setVisible(false); //Hides finish panel
    }

    //Method getBtn_Mistakes returns app setup to Mistake Panel(closes all OTHER tabs)
    public void getBtn_Mistakes(ActionEvent e) {
        vb_StylePan.setVisible(false); //Hides style panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        //Hide/Show this panel (Mistakes) on double click
        if (hb_MistakePan.isVisible()) {
            hb_MistakePan.setVisible(false);
        } else {
            hb_MistakePan.setVisible(true);
        }
    }

    //Method getBtn_Style returns app setup to Style Panel(closes all OTHER tabs)
    public void getBtn_Style(ActionEvent e) throws IOException {
        hb_MistakePan.setVisible(false); //Hides mistakes panel
        hb_FinishPan.setVisible(false); //Hides finish panel

       // Stage stage = (Stage) app.getScene.getWindow();

//        Main main = new Main();
//        Stage primarystage = main.primaryStage;
//        primarystage.setResizable(false);

//        Parent blah = FXMLLoader.load(getClass().getResource("Controller.fxml"));
//        Scene scene = new Scene(blah);
//        Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        appStage.setScene(scene);
//        appStage.setResizable(false);




        //Hide/Show this panel (Style) on double click
        if (vb_StylePan.isVisible()) {
            vb_StylePan.setVisible(false);
        } else {
            vb_StylePan.setVisible(true);
        }
    }

    //Method getBtn_Finish returns app setup to Finish Panel(closes all OTHER tabs)
    public void getBtn_Finish(ActionEvent e)
    {
        //Hide all other tabs
        vb_StylePan.setVisible(false);
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(true);

    }



    //Methods getBtn_CreateNew and the following getBtn_Open are executed when the user chooses either
    // "CREATE new file" btn or "OPEN existing" btn on a splash screen
    public void getBtn_CreateNew(ActionEvent e) throws IOException {
        //At first the whole notepad is disabled (only a splash screen is active so user
        //choose an action OPEN or CREATE. That is why after clicking the button we should enable the notepad.
        hb_Menu.setDisable(false);
        ta_TextArea.setDisable(false);

        ta_TextArea.setWrapText(true);//Wraps text
        p_Hello.setVisible(false);//Hides the splash screen

        //Open save dialog/saves new file
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save as");
        chooser.setInitialFileName("New File - Zing");

        //Set Extension Filters
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("Word document", "*.docx"),
                new FileChooser.ExtensionFilter("Rich Text Format", "*.rtf"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File file = chooser.showSaveDialog(stage);
        classFile=file;

        if (file != null) {
            FileWriter FW = new FileWriter(file.getAbsolutePath());
            FW.write(ta_TextArea.getText().toString());
            FW.close();

            //Sets autosave time
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleWithFixedDelay(() -> {
                try {
                    autoSave();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }, 0, 2, TimeUnit.SECONDS);
        }

    }

    public File classFile;//to use this variable in method autoSave which is outside of getBtn_CreateNew

    public void autoSave() throws IOException {
        System.out.println("File saved");

        if (classFile != null) {
            FileWriter FW = new FileWriter(classFile.getAbsolutePath());
            FW.write(ta_TextArea.getText().toString());
            FW.close();
        }
    }

//    public void lastSave() throws IOException {
//        if (classFile != null) {
//            FileWriter FW = new FileWriter(classFile.getAbsolutePath());
//            FW.write(ta_TextArea.getText().toString());
//            FW.close();
//            System.out.println("File saved for the last time");
//        }
//    }

    public void getBtn_Open(ActionEvent e) throws IOException {
        //At first the whole notepad is disabled (only a splash screen is active so user
        //choose an action OPEN or CREATE. That is why after clicking the button we should enable the notepad.
        hb_Menu.setDisable(false);
        ta_TextArea.setDisable(false);

        ta_TextArea.setWrapText(true);//Wraps text
        p_Hello.setVisible(false);//Hides the splash screen

        //Opens open dialog/opens new file
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File selectedFile = chooser.showOpenDialog(stage);
        FileReader FR = new FileReader(selectedFile.getAbsolutePath().toString());
        BufferedReader BR = new BufferedReader(FR);

        StringBuilder sb = new StringBuilder();
        String myText = "";

        while ((myText = BR.readLine())!=null)
        {
            sb.append(myText + "\n");
        }
        ta_TextArea.setText(sb.toString());
    }









    //Method shows you my site in default browser
    public void getURL (ActionEvent e)
    {
        try {
            Desktop.getDesktop().browse(new URL("https://www.andriybobchuk.com/").toURI());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    
//    //Hyperlink hl = new Hyperlink();
//        hl.setTooltip(new Tooltip("https://www.andriybobchuk.com/"));
//        hl.setOnAction((ActionEvent event) -> {
//        Hyperlink h = (Hyperlink) event.getTarget();
//        String s = h.getTooltip().getText();
//        getHostServices().showDocument(hl.getText());
//        event.consume();
//    });

//    public void autosave() throws IOException {
//        Stage stage = new Stage();
//        FileChooser chooser = new FileChooser();
//        chooser.setTitle("Save as");
//        chooser.setInitialFileName("New File - Zing");
//
//        //Set Extension Filters
//        chooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Text", "*.txt"),
//                new FileChooser.ExtensionFilter("Word document", "*.docx"),
//                new FileChooser.ExtensionFilter("Rich Text Format", "*.rtf"),
//                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
//                new FileChooser.ExtensionFilter("All Files", "*.*"));
//
//        File file = chooser.showSaveDialog(stage);
//        if (file != null) {
//            FileWriter FW = new FileWriter(file.getAbsolutePath());
//            FW.write(ta_TextArea.getText().toString());
//            FW.close();
//        }
//    }


    Stage thisStage;

    public void setStage (Stage stage){
        thisStage = stage;
    }

    public void showStage(){
        thisStage.setTitle("Titel in der MainController.java geändert");
        thisStage.show();
    }


}