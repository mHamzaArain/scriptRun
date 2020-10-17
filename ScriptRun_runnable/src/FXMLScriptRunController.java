/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptrun;

// JavaFx libraries
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

// Essential libraries
import java.net.URL;
import java.util.ResourceBundle;

// Error handling libraies
import java.io.FileReader;
import java.io.IOException;

// Read libraries
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;

// JSON libraries
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 *
 * @author Ayesha Ghani, Hamza Arain, Nimra Akram, Zehra Riaz
 */
public class FXMLScriptRunController implements Initializable {
    @FXML
    private ImageView btn_appLauncher, btn_appInstaller, btn_library,
            btn_minimize, btn_exit, btn_configExit, btn_configSave,
            btn_shellExit, btn_shellFileSave, btn_shell;
            
    @FXML
    private AnchorPane anchor_appLauncher, anchor_appInstaller,
            anchor_libraryInstaller, anchor_topbar,anchor_appLauncherJSON,
            anchor_shellFile;
    
    @FXML
    private  Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8,
            btn_addApp, btn_insertLibrary, btn_softwareInstall;

    @FXML
    private Tab tab_pane, tab_pane1;

    @FXML
    private TextArea textArea_softwareCommands, textArea_shellFile,
            textArea_appLauncher;
    @FXML
    private TextField textField_python, textField_javaScript,
            textField_softwareName;
            
           
    // File paths
    final String configFilePath = "buttonConfig.json";
    final String shellFilePath = "install.sh";
    
    // General class
    GeneralTools gt  = new GeneralTools();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Dynamic application launcher buttons appears
        showAppLauncher();
    }    

    /**
     * This will responsible for anchor visibility
     * ============================================
     * 
     * @author  Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse pressed
     */
    @FXML
    private void handle_anchor_topbar_btns(MouseEvent event){
        // AnchorPane on topbar buttons 
        if(event.getTarget() == btn_appLauncher){
            anchor_appLauncher.setVisible(true);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(false);
            anchor_appLauncherJSON.setVisible(false);
        } 
        
        else if(event.getTarget() == btn_appInstaller){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(true);
            anchor_libraryInstaller.setVisible(false);
            anchor_appLauncherJSON.setVisible(false);
        }   
        
        else if(event.getTarget() == btn_library){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(true);
            anchor_appLauncherJSON.setVisible(false);
        }
        
        else if(event.getTarget() == btn_shell) { 
            if (anchor_shellFile.isVisible() == false){
                anchor_shellFile.setVisible(true);
                anchor_appLauncherJSON.setVisible(false);
                shellTextArea();  // This fill textArea of anchor_shellFile
            }
            else if(anchor_shellFile.isVisible() == true){
                anchor_shellFile.setVisible(false);
            } 
        }
           
        else if(event.getTarget() == btn_minimize){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(false);
            anchor_appLauncherJSON.setVisible(false);
            anchor_shellFile.setVisible(false);
        }
     
        // Closing application
        else if(event.getTarget() == btn_exit){
            gt.exitSoftware(event);
        }
        
        // AnchorPane on configFile button
        // To close anchorpane of configFile
        else if(event.getTarget() == btn_configExit){
            if (anchor_appLauncher.isVisible() == true){
                anchor_appLauncherJSON.setVisible(false);
            }
        }
        
        // AnchorPane on shell buttons
        // To close anchorpane of shellFile
        else if(event.getTarget() == btn_shellExit){
            if(anchor_shellFile.isVisible() == true){
                anchor_shellFile.setVisible(false);
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////
//////////////////////////  App Launcher ///////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    // JSONTools class
    JSONTools jt = new JSONTools();
    
    /**
     * This will execute app launcher respective button
     * =================================================
     * 
     * @author Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse event 
     */
    @FXML
    private void btn_appLauncher_softwareAction(ActionEvent event) {      
        /**
         * Send event & String path of respective stroke button
         */
        jt.jsonExecutor(event, configFilePath);
    }
    
    /**
     * Dynamic display button on appLauncher anchorpane
     * =================================================
     * 
     * @author Ayesha Ghani Chhipa, Hamza Arain
     * @param b JSONObject
     */
    void parseJSONButtonObject(JSONObject b)  {
        
        JSONObject btn_attr = (JSONObject) b.get("button-attributes");
        btn_1.setVisible(false);
        btn_2.setVisible(false);
        btn_3.setVisible(false);
        btn_4.setVisible(false);
        btn_5.setVisible(false);
        btn_6.setVisible(false);
        btn_7.setVisible(false);
        btn_8.setVisible(false);
        for (int i = 1; i <= 8; i++) {
            JSONObject btn_n = (JSONObject) btn_attr.get("button-" + i);
            if ( (boolean) btn_n.get("active") == true){
                switch(i){
                    case 1:
                        btn_1.setVisible(true);
                        btn_1.setText((String) btn_n.get("name"));
                        break;
                    case 2:
                        btn_2.setVisible(true);
                        btn_2.setText((String) btn_n.get("name"));
                        break;
                    case 3:
                        btn_3.setVisible(true);
                        btn_3.setText((String) btn_n.get("name"));
                        break;
                    case 4:
                        btn_4.setVisible(true);
                        btn_4.setText((String) btn_n.get("name"));
                        break;
                    case 5:
                        btn_5.setVisible(true);
                        btn_5.setText((String) btn_n.get("name"));
                        break;
                    case 6:
                        btn_6.setVisible(true);
                        btn_6.setText((String) btn_n.get("name"));
                        break;
                    case 7:
                        btn_7.setVisible(true);
                        btn_7.setText((String) btn_n.get("name"));
                        break;
                    case 8:
                        btn_8.setVisible(true);
                        btn_8.setText((String) btn_n.get("name"));
                        break;
                }
            }
        }
    }

    /**
     * This allows to edit json file on anchor_appLauncherJSON 
     * =======================================================
     * 
     * @author Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse pressed
     */
    @FXML
    private void btn_editAppAction(ActionEvent event) {
        // Anchorpane on appLauncher
        if ( anchor_appLauncher.isVisible() == true){
            anchor_appLauncherJSON.setVisible(true);
            anchor_shellFile.setVisible(false);
            textArea_appLauncher.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(configFilePath)))) {
                String line;
                while ((line = reader.readLine()) != null){
//                    System.out.println(line);
                    textArea_appLauncher.appendText(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Udate appLauncher buttons
            showAppLauncher();
        }
    }
    
    /**
     * This will write any changes happened in file via text 
     * =====================================================
     * 
     * @author Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse Pressed
     */
    @FXML
    private void handleBtnConfigSaveAction(MouseEvent event) {
       String wr =  textArea_appLauncher.getText();
        gt.fileWrite(configFilePath, wr);    
        // Udate appLauncher buttons
        showAppLauncher();
    }
    
    /**
     * Update buttons on anchor_appLauncher buttons
     * =============================================
     * 
     * @author Ayesha Ghani, Hamza Arain
     */
    private void showAppLauncher() {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseJSONButtonObject( (JSONObject) each ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////// Software Installation ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
    BASHTools bt = new BASHTools();
    
    
    /**
     * This will insert application in file
     * =====================================
     * 
     * @author Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse event
     */
    @FXML
    private void btn_insertAppInShellFile_action(ActionEvent event) {
        // splits commands by newline
        String[] arguments = textArea_softwareCommands.getText().split("\n");
        bt.softwareInsertion(shellFilePath, arguments, textField_softwareName.getText());

        shellTextArea();
        
        // To clear all fields
        textArea_softwareCommands.clear();
        textField_softwareName.clear();   
    }
    

    /**
     * This will insert python & javascript libraries
     * ===============================================
     * 
     * @author Ayesha Ghani, Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse pressed
     */
    @FXML
    private void btn_insertLibraryInShellFile_action(ActionEvent event) {
        bt.libraryInsertion(shellFilePath, textField_python.getText(), textField_javaScript.getText());
        shellTextArea();
        
        textField_python.clear();
        textField_javaScript.clear();
    }


    /**
     * This allow to change shell file via text
     * =========================================
     * 
     * @author Hamza Arain, Nimra Akram, Zehra Riaz
     * @param event Mouse pressed
     */
    @FXML
    private void handleBtnShellSaveAction(MouseEvent event) {  
        String wr =  textArea_shellFile.getText();
        gt.fileWrite(shellFilePath, wr);
        shellTextArea();
        showAppLauncher();
    }

    
    /**
    * This read text from file and display it on text area including white spaces.
    * ============================================================================
    * 
    * @author Hamza Arain
    */
    private void shellTextArea(){
        textArea_shellFile.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(shellFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
                textArea_shellFile.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
