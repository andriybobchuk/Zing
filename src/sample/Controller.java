package sample;

import animatefx.animation.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.PauseTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.dialog.FontSelectorDialog;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    @FXML
    private Label progress;
    @FXML
    public static javafx.scene.control.Label label;
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
    private JFXComboBox<String> jfxcb_Font;
    @FXML
    private JFXComboBox<Integer> jfxcb_FontSize;
    @FXML
    private Button btn_msg_isSaved;
    @FXML
    private Button btn_msg_notSaved;
    @FXML
    private SplitPane sp_SpellCheckLayout;
    @FXML
    private TextArea ta_LineCounter;
    @FXML
    private TextArea ta_CheckArea;
    @FXML
    private TextArea ta_Errors;
    public boolean myWorkIsSaved = false;
    ////////////////////////////////////////THE END OF INITIALIZATION AREA/////////////////////////////////////////////



    /*=============================THE BEGINNING OF SWITCHING BETWEEN TABS METHODS===================================*/

    //Method getBtn_Write returns app setup to its normal view(closes all tabs)
    public void getBtn_Write(ActionEvent e) {
        vb_StylePan.setVisible(false); //Hides style panel
        hb_MistakePan.setVisible(false); //Hides mistakes panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        checkMistakes.setVisible(false);
        checkStyle.setVisible(false);
        checkFinish.setVisible(false);

        checkWrite.setVisible(true);

        if(sp_SpellCheckLayout.isVisible())
        {
            sp_SpellCheckLayout.setVisible(false);
            ta_TextArea.setText(ta_CheckArea.getText());
        }

        ta_Errors.setText("");

    }

    //Method getBtn_Mistakes returns app setup to Mistake Panel(closes all OTHER tabs)
    public void getBtn_Mistakes(ActionEvent e) throws IOException {
        vb_StylePan.setVisible(false); //Hides style panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        checkStyle.setVisible(false);
        checkFinish.setVisible(false);
        checkWrite.setVisible(false);

        //hb_MistakePan.setVisible(true);
        checkMistakes.setVisible(true);

        findMistakes();
    }

    //Method getBtn_Style returns app setup to Style Panel(closes all OTHER tabs)
    public void getBtn_Style(ActionEvent e) throws IOException {
        hb_MistakePan.setVisible(false); //Hides mistakes panel
        hb_FinishPan.setVisible(false); //Hides finish panel

        checkMistakes.setVisible(false);
        checkFinish.setVisible(false);
        checkWrite.setVisible(false);

        if(sp_SpellCheckLayout.isVisible())
        {
            sp_SpellCheckLayout.setVisible(false);
            ta_TextArea.setText(ta_CheckArea.getText());
        }
        ta_Errors.setText("");

        vb_StylePan.setVisible(true);
        checkStyle.setVisible(true);

        new SlideInRight(vb_StylePan).play();//animation

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

        if(sp_SpellCheckLayout.isVisible())
        {
            sp_SpellCheckLayout.setVisible(false);
            ta_TextArea.setText(ta_CheckArea.getText());
        }
        ta_Errors.setText("");

        if(myWorkIsSaved == false)//If you haven't saved your work yet, do it now.
        {
            getBtn_CreateNew();
        }
    }
    /*=============================THE END OF SWITCHING BETWEEN TABS METHODS=========================================*/



    /*=============================THE BEGINNING OF SPELLCHECK FEATURE==============================================*/

    public void findMistakes() {

        //Dictionary
        Scanner sc = null;
        try {
            sc = new Scanner(new File("D:\\DOCUMENTS\\Java_Sources\\Zing\\src\\sample\\Resources\\spellcheck\\dictionary_EN.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        String[] wordlist = lines.toArray(new String[0]);


        if(sp_SpellCheckLayout.isVisible())
        {
            ta_Errors.setText("");
        }
        else
        {
            ta_CheckArea.setText(ta_TextArea.getText());
        }

        sp_SpellCheckLayout.setVisible(true);
        //input
        String input = ta_CheckArea.getText();//textarea


        if(spellCheck(input, wordlist))
        {
            ta_Errors.setStyle("-fx-text-fill: green");
            ta_Errors.setText("Well done! No mistakes");
            System.out.println("No errors");
        }
        else
        {
            System.out.println("Errors");
        }

    }

    public boolean spellCheck (String input, String[] dic)
    {
        String currentCheck = "";
        boolean noErrors = true;
        Scanner spellChecker = new Scanner(input);
        spellChecker.useDelimiter("\\s+");

        if(!grammarCheck(input, input.length()))
        {
            noErrors = false;
        }

        while(spellChecker.hasNext())
        {
            currentCheck = spellChecker.next();
            if(!isSpecial(currentCheck))
            {
                if(!checkWord(currentCheck, dic))
                {
                    ta_Errors.setStyle("-fx-text-fill: red");
                    ta_Errors.appendText("'" + currentCheck + "'" + " is spelled incorrectly \n");
//                    ta_Errors.setText(currentCheck + " is incorrect");
                    System.out.println(currentCheck + " is spelt incorrectly");
                    noErrors = false;
                }
            }
        }
        return noErrors;
    }

    public boolean isSpecial (String input)
    {
        Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(input);
        return match.find();
    }

    public boolean checkWord(String input, String[] dic)
    {
        boolean valid = false;
        int length = dic.length;
        int i=0;

        while(!valid &&i<length)
        {
            if(input.trim().equalsIgnoreCase(dic[i]))
            {
                valid = true;
            }
            i++;
        }
        return valid;
    }

    public boolean grammarCheck(String input, int length)
    {
        boolean validGrammar = true;
        int lastCharacter = length-1;

        if(!Character.isUpperCase(input.charAt(0)))
        {
            String output = input.substring(0, 1).toUpperCase() + input.substring(1);
            ta_CheckArea.setText(output);//Sets first char to Uppercase automatically

            ta_Errors.setStyle("-fx-text-fill: red");
            ta_Errors.appendText("First letter must be an uppercase (CORRECTED!) \n");
            validGrammar = false;
        }
        return validGrammar;
    }
    /*=============================THE END OF SPELLCHECK FEATURE====================================================*/




    /*=============================THE BEGINNING OF FONT STYLE SETTINGS=============================================*/
    String selectedFont = "MV Boli";
    int selectedFontSize = 18;
    boolean bold = false;
    boolean italic = false;
    //boolean underlined = false; This feature is not ready now

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label=progress;


        ObservableList<String> fonts = FXCollections.observableArrayList(
                "MV Boli", "Segoe UI", "Pecita", "Times New Roman", "Arial", "Verdana", "Calibri", "System");
        jfxcb_Font.setItems(fonts);
        jfxcb_Font.setValue(selectedFont);
        jfxcb_Font.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                        System.out.println(t1);
                        selectedFont = (String) t1;
                    }
                });


        ObservableList<Integer> fontSizes = FXCollections.observableArrayList(
                18,20,22,24,32,48,72,100,120);
        jfxcb_FontSize.setItems(fontSizes);
        jfxcb_FontSize.setValue(selectedFontSize);

        jfxcb_FontSize.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                        System.out.println(t1);
                        selectedFontSize = (int) t1;
                    }
                });

    }

    public void getBtn_Blue1()
    {
        ta_TextArea.setStyle("-fx-text-fill: #002448");
    }
    public void getBtn_Blue2()
    {
        ta_TextArea.setStyle("-fx-text-fill:   #002f5e");
    }
    public void getBtn_Blue3()
    {
        ta_TextArea.setStyle("-fx-text-fill:   #00376e");
    }
    public void getBtn_Blue4()
    {
        ta_TextArea.setStyle("-fx-text-fill:   #090033");
    }
    public void getBtn_Blue5()
    {
        ta_TextArea.setStyle("-fx-text-fill:   #260052");
    }
    public void getBtn_Red()
    {
        ta_TextArea.setStyle("-fx-text-fill: red");
    }
    public void getBtn_Pen()
    {
        ta_TextArea.setStyle("-fx-text-fill:  #303030");
    }
    public void getBtn_Green()
    {
        ta_TextArea.setStyle("-fx-text-fill:  Green");
    }
    public void getBtn_Blue()
    {
        ta_TextArea.setStyle("-fx-text-fill:  Blue");
    }
    public void getBtn_Black()
    {
        ta_TextArea.setStyle("-fx-text-fill:  Black");
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

    /*=============================THE END OF FONT STYLE SETTINGS===================================================*/




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

            show_msg_isSaved();

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

        if(file == null){show_msg_notSaved();}

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

        if(classSelectedFile==null){show_msg_notSaved();}


        FileReader FR = new FileReader(selectedFile.getAbsolutePath().toString());
        BufferedReader BR = new BufferedReader(FR);

        StringBuilder sb = new StringBuilder();
        String myText = "";

        while ((myText = BR.readLine())!=null)
        {
            sb.append(myText + "\n");
        }

        ta_TextArea.setText(sb.toString());

        show_msg_isSaved();


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

    public void show_msg_isSaved()
    {
        if(btn_msg_isSaved.isVisible())
        {
            btn_msg_isSaved.setVisible(false);
        } else {
            btn_msg_isSaved.setVisible(true);
        }

        new FadeIn(btn_msg_isSaved).play();

        PauseTransition pause = new PauseTransition(Duration.seconds(7));
        pause.setOnFinished(e -> btn_msg_isSaved.setVisible(false));
        pause.play();

    }


    public void show_msg_notSaved() throws IOException {
        if(btn_msg_notSaved.isVisible())
        {
            btn_msg_notSaved.setVisible(false);
            getBtn_CreateNew();
        } else {
            btn_msg_notSaved.setVisible(true);
        }

        new Tada(btn_msg_notSaved).play();

        PauseTransition pause = new PauseTransition(Duration.seconds(13));
        pause.setOnFinished(e -> btn_msg_notSaved.setVisible(false));
        pause.play();

    }
    /*===========================THE END OF SAVE/OPEN/AUTOSAVE HELL=================================================*/



    /*========================================OTHER METHODS=========================================================*/
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