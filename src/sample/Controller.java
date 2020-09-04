package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.JobSettings;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;

public class Controller<font> {

    @FXML
    private Button btn_Write;
    @FXML
    private Button btn_Mistakes;
    @FXML
    private Button btn_Style;
    @FXML
    private Button btn_Finish;
    @FXML
    private TextArea ta_TextArea;
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

    @FXML
    public void onActionPageSetup(ActionEvent e) {
        PrinterJob printerJob = createPrinterJob();
        printerJob.showPageSetupDialog(ta_TextArea
                .getScene()
                .getWindow());
        JobSettings jobSettings = printerJob.getJobSettings();
    }

    private PrinterJob createPrinterJob() {
        PrinterJob printerJob = PrinterJob
                .createPrinterJob();
        JobSettings jobSettings = null;
        if (jobSettings == null) {
            jobSettings = printerJob.getJobSettings();
        } else {
            setPrinterJobSettings(printerJob
                            .getJobSettings(),
                    jobSettings);
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

    public void onActionPrint(ActionEvent event) {
        PrinterJob printerJob = createPrinterJob();

        boolean result = printerJob
                .showPrintDialog(ta_TextArea
                        .getScene()
                        .getWindow());
        if (result) {
            Text text = new Text(ta_TextArea.getText());
            text.setFont(ta_TextArea.getFont());
            TextFlow textFlow = new TextFlow(text);
            boolean printed = printerJob
                    .printPage(textFlow);
            if (printed) {
                printerJob.endJob();
            }
        }
    }

    public void getBtn_Write(ActionEvent e) {
        vb_StylePan.setVisible(false);
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(false);
    }

    public void getBtn_Mistakes(ActionEvent e) {
        vb_StylePan.setVisible(false);
        hb_FinishPan.setVisible(false);

        if (hb_MistakePan.isVisible()) {
            hb_MistakePan.setVisible(false);
        } else {
            hb_MistakePan.setVisible(true);
        }
    }

    public void getBtn_Style(ActionEvent e) {
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(false);

        if (vb_StylePan.isVisible()) {
            vb_StylePan.setVisible(false);
        } else {
            vb_StylePan.setVisible(true);
        }
    }

    public void getBtn_Finish(ActionEvent e)  //Saves file as..
    {
        vb_StylePan.setVisible(false);
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(true);

    }

    public void getBtn_CreateNew(ActionEvent e) throws IOException {
        hb_Menu.setDisable(false);
        ta_TextArea.setDisable(false);

        p_Hello.setVisible(false);

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

        if (file != null) {
            FileWriter FW = new FileWriter(file.getAbsolutePath());
            FW.write(ta_TextArea.getText().toString());
            FW.close();
        }


    }

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
}