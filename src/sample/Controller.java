package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

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
    private TextArea ta_TextArea;
    @FXML
    private VBox vb_StylePan;
    @FXML
    private HBox hb_MistakePan;
    @FXML
    private HBox hb_FinishPan;

    public void getBtn_Write(ActionEvent e)
    {
        vb_StylePan.setVisible(false);
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(false);
    }

    public void getBtn_Mistakes(ActionEvent e)
    {
        vb_StylePan.setVisible(false);
        hb_FinishPan.setVisible(false);

        if(hb_MistakePan.isVisible())
        {
            hb_MistakePan.setVisible(false);
        } else {
            hb_MistakePan.setVisible(true);
        }
    }

    public void getBtn_Style(ActionEvent e)
    {
        hb_MistakePan.setVisible(false);
        hb_FinishPan.setVisible(false);

        if(vb_StylePan.isVisible())
        {
            vb_StylePan.setVisible(false);
        } else {
            vb_StylePan.setVisible(true);
        }
    }
//    public void getBtn_Open(ActionEvent e) throws IOException //Opens and reads a new file
//    {
//        Stage stage = new Stage();
//        FileChooser chooser = new FileChooser();
//        chooser.setTitle("Open File");
//        File selectedFile = chooser.showOpenDialog(stage);
//        FileReader FR = new FileReader(selectedFile.getAbsolutePath().toString());
//        BufferedReader BR = new BufferedReader(FR);
//
//        StringBuilder sb = new StringBuilder();
//        String myText = "";
//
//        while ((myText = BR.readLine())!=null)
//        {
//            sb.append(myText + "\n");
//        }
//        txtOutput.setText(sb.toString());
//
//    }
    public void getBtn_Finish(ActionEvent e) throws IOException //Saves file as..
    {
        vb_StylePan.setVisible(false);
        hb_MistakePan.setVisible(false);

        if(hb_FinishPan.isVisible())
        {
            hb_FinishPan.setVisible(false);
        } else {
            hb_FinishPan.setVisible(true);
        }

        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save as");
        chooser.setInitialFileName("New File - Zing");

        //Set Extension Filters
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File file = chooser.showSaveDialog(stage);

        if(file != null){
            FileWriter FW = new FileWriter(file.getAbsolutePath());
            FW.write(ta_TextArea.getText().toString());
            FW.close();

        }


//        public void saveItem () {
//        File file;
//        boolean toClear = false;
//        String path = null;
//        String text;
//
//        if(path ==null){
//            chooser.setTitle("Save As");
//            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//            chooser.getExtensionFilters().add(extFilter);
//            file = chooser.showSaveDialog(this.getScene().getWindow());
//        }
//        else{
//            file= new File(path);
//        }
//        if (file !=null)
//        {
//            path=file.getAbsolutePath();
//            Main.getStage().setTitle(path);
//            text= ta_TextArea.getText();
//            if(!(text.isEmpty())){
//                toClear= writeItemHighStream(file);
//            }
//            if(toClear)
//            {
//                toClear=false;
//            }
//        }
//    }










//        FileOutputStream out = null;
//
//        String text = ta_TextArea.getText();
//        byte b[] = text.getBytes();
//        String outputFileName = System.getProperty("user.home", File.separatorChar + "home" + File.separatorChar + "monica") + File.separatorChar + "text.txt";
//        out = new FileOutputStream(outputFileName);
//        out.write(b);
//        out.close();
//        //Clear text field
//        tf_TextArea.setText("");

    }


}
