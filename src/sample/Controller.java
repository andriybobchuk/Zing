package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXColorPicker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.FontSelectorDialog;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    private JFXColorPicker cp_pen;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane checkWrite;
    @FXML
    private Pane checkMistakes;
    @FXML
    private Pane checkStyle;
    @FXML
    private Pane checkFinish;
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
    @FXML
    private JFXComboBox jfxcb_Font;
    @FXML
    private JFXComboBox jfxcb_FontSize;

    //private ObjectProperty<Font> fontStyle;





    public boolean myWorkIsSaved = false;

    /*----------------------------------------------------------------------------------------------------------*/
    String selectedFont = "Pecita";
    int selectedFontSize = 18;
    boolean bold = false;
    boolean italic = false;
    //boolean underlined = false; This feature is not ready now

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList fonts = FXCollections.observableArrayList(
                "Times New Roman", "Arial", "Pecita", "Verdana", "Cambria", "Calibri", "System");
        jfxcb_Font.setItems(fonts);
        jfxcb_Font.setValue(selectedFont);
        jfxcb_Font.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                        System.out.println(t1);
                        selectedFont = (String) t1;
                        //ta_TextArea.setFont(Font.font(selectedFont, selectedFontSize));
                    }
                });


        ObservableList fontSizes = FXCollections.observableArrayList(
                18,24,48,72,100,120);
        jfxcb_FontSize.setItems(fontSizes);
        jfxcb_FontSize.setValue(selectedFontSize);
        //jfxcb_FontSize.setEditable(true);

        jfxcb_FontSize.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                        System.out.println(t1);
                        selectedFontSize = (int) t1;
                        //ta_TextArea.setFont(Font.font(selectedFont, selectedFontSize));
                    }
                });


    }

    public void getBtn_Bold(ActionEvent e)
    {
        if(bold==false)
        {
            bold = true;
        } else {
            bold = false;
        }
    }

    public void getBtn_Italic(ActionEvent e)
    {

        if(italic==false)
        {
            italic = true;
        } else {
            italic = false;
        }
    }

//    public void getBtn_Underlined(ActionEvent e)
//    {
//
//        if(underlined==false)
//        {
//            underlined = true;
//        } else {
//            underlined = false;
//        }
//    }

    public void onClickApply(ActionEvent e)
    {
        if(bold==false&&italic==false)
        {
            ta_TextArea.setFont(Font.font(selectedFont, selectedFontSize));
        }

        if(bold==true&&italic==false)
        {
            ta_TextArea.setFont(Font.font(selectedFont, FontWeight.BOLD, selectedFontSize));
        }

        if(bold==false&&italic==true)
        {
            ta_TextArea.setFont(Font.font(selectedFont, FontPosture.ITALIC, selectedFontSize));
        }

        if(bold==true&&italic==true)
        {
            ta_TextArea.setFont(Font.font(selectedFont, FontWeight.BOLD, FontPosture.ITALIC, selectedFontSize));
        }
    }

    public void fontSettings (ActionEvent e)
    {
        FontSelectorDialog dialog = new FontSelectorDialog(null);
        dialog.setTitle("Zing - Font chooser");
        Optional<Font> response = dialog.showAndWait();
        System.out.println(response.get().getFamily());
        System.out.println(response.get().getName());
        System.out.println(response.get().getSize());

        ta_TextArea.setFont(dialog.getResult());
    }



    /*----------------------------------------------------------------------------------------------------------*/


//    public void changeColor(ActionEvent e)
//    {
//        Color selectedColor = cp_pen.getValue();
//
//        //ta_TextArea.setStyle("-fx-fill: red");
//    }



//        jfxcb_Font.getItems().add(new Label("Pecita"));
//        jfxcb_Font.getItems().add(new Label("Java 1.7"));
//        jfxcb_Font.getItems().add(new Label("Java 1.6"));
//        jfxcb_Font.getItems().add(new Label("Java 1.5"));


//        jfxcb_FontSize.getItems().add(new Label("1"));
//        jfxcb_FontSize.getItems().add(new Label("Java 1.7"));
//        jfxcb_FontSize.getItems().add(new Label("Java 1.6"));
//        jfxcb_FontSize.getItems().add(new Label("Java 1.5"));

        //jfxs_FontSize.setValue(18);



//        jfxs_Font.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                ta_TextArea.setFont(Font.font("Pecita", jfxs_Font.getValue()));
//            }
//        });

//        jfxcb_FontSize.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                ta_TextArea.setFont(Font.font(String.valueOf(jfxcb_FontSize.getItems()), 18));
//            }
//        });

//        jfxcb_Font.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                ta_TextArea.setFont(Font.font(jfxcb_Font.getItems(), 18));
//            }
//        });

        //ta_TextArea.setFont(Font.font("Pecita", jfxs_FontSize.getValue()));


//    public void initialize ()
//    {
//        slider.setMax(100);
//        slider.setMin(0);
//        slider.setValue(18);
//
//        slider.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                ta_TextArea.setFont(Font.font("Pecita", slider.getValue()));
//            }
//        });
//    }


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

//    @FXML
//    void onActionFont(ActionEvent event) {
//        FontSelectorDialog dialog = new FontSelectorDialog(fontProperty.get());
//        dialog.showAndWait()
//                .ifPresent((t) -> {
//                    fontProperty.set(t);
//                });
//    }
//



















//    public void ChangeFontStyle() {
//        fontstyle.setOnAction(e -> {
//                    FontSelectorDialog fontSelectorDialog = new FontSelectorDialog(null);
//                    fontSelectorDialog.setTitle("Select Font");
//                    fontSelectorDialog.show();
//                    textarea.setFont(fontSelectorDialog.getResults());
//                }
//        );
//    }

    /*=============================THE BEGINNING OF PRINTER TASKS===================================================*/

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
    /*=============================THE END OF PRINTER TASKS========================================================*/



    //Method getBtn_Write returns app setup to its normal view(closes all tabs)
    public void getBtn_Write(ActionEvent e) {
        vb_StylePan.setVisible(false); //Hides style panel
        hb_MistakePan.setVisible(false); //Hides mistakes panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        checkMistakes.setVisible(false);
        checkStyle.setVisible(false);
        checkFinish.setVisible(false);

        checkWrite.setVisible(true);

//        if(checkWrite.isVisible()){
//            checkWrite.setVisible(false);
//        } else{
//            checkWrite.setVisible(true);
//        }

    }

    //Method getBtn_Mistakes returns app setup to Mistake Panel(closes all OTHER tabs)
    public void getBtn_Mistakes(ActionEvent e) {
        vb_StylePan.setVisible(false); //Hides style panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        checkStyle.setVisible(false);
        checkFinish.setVisible(false);
        checkWrite.setVisible(false);

        hb_MistakePan.setVisible(true);
        checkMistakes.setVisible(true);

        //Hide/Show this panel (Mistakes) on double click
//        if (hb_MistakePan.isVisible()) {
//            hb_MistakePan.setVisible(false);
//            checkMistakes.setVisible(false);
//        } else {
//            hb_MistakePan.setVisible(true);
//            checkMistakes.setVisible(true);
//        }
    }

    //Method getBtn_Style returns app setup to Style Panel(closes all OTHER tabs)
    public void getBtn_Style(ActionEvent e) throws IOException {
        hb_MistakePan.setVisible(false); //Hides mistakes panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        checkMistakes.setVisible(false);
        checkFinish.setVisible(false);
        checkWrite.setVisible(false);

        vb_StylePan.setVisible(true);
        checkStyle.setVisible(true);

//        ta_TextArea.setLayoutX(28);
//        ta_TextArea.setLayoutY(59);
//        ta_TextArea.setPrefWidth(644);
//        ta_TextArea.setPrefHeight(613);

        //Hide/Show this panel (Style) on double click
//        if (vb_StylePan.isVisible()) {
//            vb_StylePan.setVisible(false);
//            checkStyle.setVisible(false);
//        } else {
//            vb_StylePan.setVisible(true);
//            checkStyle.setVisible(true);
//
////            ta_TextArea.setStyle("fx: layoutX=\"25.0\" layoutY=\"55.0\" prefHeight=\"605.0\" prefWidth=\"602.0\" scrollTop=\"1.0\" wrapText=\"true\" AnchorPane.bottomAnchor=\"-0.6000000000000227\" AnchorPane.leftAnchor=\"25.0\" AnchorPane.rightAnchor=\"304.6\" AnchorPane.topAnchor=\"55.0\"");
//        }
    }

    //Method getBtn_Finish returns app setup to Finish Panel(closes all OTHER tabs)
    public void getBtn_Finish(ActionEvent e) throws IOException {
        //Hide all other tabs
        vb_StylePan.setVisible(false);
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(true);

        checkWrite.setVisible(false);
        checkMistakes.setVisible(false);
        checkStyle.setVisible(false);
        checkFinish.setVisible(true);

        if(myWorkIsSaved == false)//If you haven't saved your work yet, do it now.
        {
            getBtn_CreateNew();
        }
    }


    /*=============================THE BEGINNING OF SAVE/OPEN/AUTOSAVE HELL========================================*/

    //1) Methods getBtn_CreateNew and the following getBtn_Open are executed when the user chooses either
    // "CREATE new file" btn or "OPEN existing" btn on a splash screen.
    //2) Methods autoSave and autoSaveImportedFile save automatically file with a given timer. The difference between
    // these two methods is that autoSave saves file where you have chosen while creating new file and
    // autoSaveImportedFile saves it where you opened it from.

    public File classFile;//to use the file directory variable in method autoSave FOR NEW FILES which is outside
    // of getBtn_CreateNew

    public File classSelectedFile;//to use the file directory variable in method autoSave FOR OPENED FILES
    // which is outside of getBtn_Open

    public void getBtn_CreateNew() throws IOException {
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

            //Sets autosave timing and calls autosave method for CREATED FILE(Yes, autosave methods for new and
            // opened files differ)
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

    public void autoSave() throws IOException {
        System.out.println("New file saved");

        myWorkIsSaved = true; //That means while you press the FINISH btn you will not need to save it
        // one more time. (Cuz AUTOSAVE do it for you!)

         if (classFile != null) {
            FileWriter FW = new FileWriter(classFile.getAbsolutePath());
            FW.write(ta_TextArea.getText().toString());
            FW.close();
        }
    }

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
        classSelectedFile=selectedFile;

        FileReader FR = new FileReader(selectedFile.getAbsolutePath().toString());
        BufferedReader BR = new BufferedReader(FR);

        StringBuilder sb = new StringBuilder();
        String myText = "";

        while ((myText = BR.readLine())!=null)
        {
            sb.append(myText + "\n");
        }
        ta_TextArea.setText(sb.toString());


        //Sets autosave time ad calls autosave method for IMPORTED FILE
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            try {
                autoSaveImportedFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);

    }

    public void autoSaveImportedFile() throws IOException {
        System.out.println("Imported file saved");

        myWorkIsSaved = true; //That means while you press the FINISH btn you will not need to save it
        // one more time. (Cuz AUTOSAVE do it for you!)

        if (classSelectedFile != null) {
            FileWriter FW = new FileWriter(classSelectedFile.getAbsolutePath());
            FW.write(ta_TextArea.getText().toString());
            FW.close();
        }
    }

    /*===========================THE END OF SAVE/OPEN/AUTOSAVE HELL============================================*/





    //Method shows you my site in your default browser when you click on a link in a splash screen.
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



}