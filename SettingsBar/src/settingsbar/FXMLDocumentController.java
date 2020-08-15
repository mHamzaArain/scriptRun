/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settingsbar;


// JavaFx libraries
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.stage.Stage;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Read libraries
import java.io.BufferedReader;
import java.io.File;

// JSON libraries
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Insert input libraries
import static settingsbar.InsertShellFile.insertSoftwareCommandsInScript;
import static settingsbar.InsertShellFile.printMatches;

/**
 *
 * @author Hamza Arain
 */
public class FXMLDocumentController implements Initializable {
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
    private final String shellFilePath = "src\\settingsbar\\install.sh";
    private final String configFilePath = "src\\settingsbar\\buttonConfig.json";

    // Event actions
    // top bat 
    @FXML
    private void handleButtonAction(MouseEvent event) {
        // AnchorPane on topbat buttons 
        if(event.getTarget() == btn_library){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(true);
            anchor_appLauncherJSON.setVisible(false);
        }
        
        else if(event.getTarget() == btn_appLauncher){
            anchor_appLauncher.setVisible(true);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(false);
        } 
        
        else if(event.getTarget() == btn_appInstaller){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(true);
            anchor_libraryInstaller.setVisible(false);
            anchor_appLauncherJSON.setVisible(false);
        }      
        
        else if(event.getTarget() == btn_minimize){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(false);
            anchor_appLauncherJSON.setVisible(false);
            anchor_shellFile.setVisible(false);
        }
        
        else if(event.getTarget() == btn_shell) { 
            if (anchor_shellFile.isVisible() == false){
                anchor_shellFile.setVisible(true);
                anchor_appLauncherJSON.setVisible(false);
                shellTextArea();
            }
            else if(anchor_shellFile.isVisible() == true){
                anchor_shellFile.setVisible(false);
            } 
        }
         
        // AnchorPane on shell buttons
        else if(event.getTarget() == btn_shellExit){
            if(anchor_shellFile.isVisible() == true){
                anchor_shellFile.setVisible(false);
            }
        }

        /**
         * Extract text from text area & write it on shell file to save it.
         */
        else if(event.getTarget() == btn_shellFileSave){
            String wr =  textArea_shellFile.getText();
            try (FileWriter file = new FileWriter(shellFilePath)) {
                file.write(wr);
                file.flush();   
            } catch (IOException e) {
                e.printStackTrace();
            }
            shellTextArea();
        }
        
        // AnchorPane on configFile buttons
        else if(event.getTarget() == btn_configExit){
            if (anchor_appLauncher.isVisible() == true){
                anchor_appLauncherJSON.setVisible(false);
            }
        }
    }

    // Close button
    @FXML
    private void close(MouseEvent event) {
        Stage s = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        s.close();
    } 

    /*
    * This read test from file and display it on text area including white spaces.
    */
    private void shellTextArea(){
        textArea_shellFile.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(shellFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
//                System.out.println(line);
                textArea_shellFile.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
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
            buttons.forEach( each -> parseButtonObject( (JSONObject) each ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    
    private void parseButtonObject(JSONObject b) 
    {
        //Get employee object within list
        JSONObject btn_attr = (JSONObject) b.get("button-attributes");
       
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
    
    
    public void appLauncherButtonExecution(String path){
        System.out.println(path);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAppLauncher();
    }  

    @FXML
    private void btn_1_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 1 ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }        
    }

    @FXML
    private void btn_2_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 2 ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btn_3_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 3 ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btn_4_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 4 ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btn_5_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 5 ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btn_6_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 6 ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btn_7_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 7) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btn_8_action(ActionEvent event) {
        //// Read json
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(configFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray buttons = (JSONArray) obj;
             
            //Iterate over employee array
            buttons.forEach( each -> parseBtnObj( (JSONObject) each, 8) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    private void parseBtnObj(JSONObject b,int i) 
    {
        //Get employee object within list
        JSONObject btn_attr = (JSONObject) b.get("button-attributes");
        JSONObject btn_n = (JSONObject) btn_attr.get("button-" + i);
//        System.out.print((String) btn_n.get("path"));
    }

    @FXML
    private void btn_edit_app_action(ActionEvent event) throws Exception {
        if (anchor_appLauncher.isVisible() == true){
            anchor_appLauncherJSON.setVisible(true);
            anchor_shellFile.setVisible(false);
            textArea_appLauncher.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(configFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
//                System.out.println(line);
                textArea_appLauncher.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            }
            showAppLauncher();
        }
    }
    
    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }


    @FXML
    private void handleBtnConfigSaveAction(MouseEvent event) {
       String wr =  textArea_appLauncher.getText();
//       System.out.println(wr);
       try (FileWriter file = new FileWriter(configFilePath)) {
            file.write(wr);
            file.flush();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        showAppLauncher();
    }

    @FXML
    private void btn_insertAppInShellFile_action(ActionEvent event) {
        // splits commands by newline
        String[] arguments= textArea_softwareCommands.getText().split("\n");

        String str = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(shellFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
                str = str + line + "\n";
            }
            
            // Last software number extracted from shell file
            int indexOfLastSoftwareNumber = printMatches(str, "([0-9]*[0-9] \")") + 1;
            String lastNumber = (String) str.subSequence( indexOfLastSoftwareNumber-1, indexOfLastSoftwareNumber+1 );
            int lastSoftwareNumber = Integer.parseInt(lastNumber.trim());
            
            int middleIndex = str.indexOf("\n", indexOfLastSoftwareNumber);

            String[] input = insertSoftwareCommandsInScript(lastSoftwareNumber, textField_softwareName.getText(), arguments);
            
            // Last double semi colon extracted from shell file
            int indexOfLastSemiColon = printMatches(str, "([;;])") + 1;     
            
            String firstStr = (String) str.subSequence(0, middleIndex+1);
            String SecondStr = (String) str.subSequence( middleIndex, indexOfLastSemiColon );
            String lastStr = (String) str.subSequence( indexOfLastSemiColon, str.lastIndexOf("fi")+2 );
        
            str = firstStr + input[0] + SecondStr + input[1] + lastStr;
        } 
        catch (IOException e) {
            e.printStackTrace();
        }  
        
        try (FileWriter file = new FileWriter(shellFilePath)) {
            file.write(str);
            file.flush();   
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        shellTextArea();
        
        // To clear all fields
        textArea_softwareCommands.clear();
        textField_softwareName.clear();
    }

    @FXML
    private void btn_insertLibrary_action(ActionEvent event) {
        String str = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(shellFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
                str = str + line + "\n";
            }
            if (!textField_python.getText().equals("")){
                String firstStr = (String) str.subSequence( 0, str.indexOf("pip3 install") );
                
//                System.out.println(str.indexOf("pip3 install"));
                int ind = str.indexOf(";;", 1082); // 1082 index of "pip3 install" 

                String input = (String) str.subSequence( str.indexOf("pip3 install"), str.indexOf(";;") );
                input = input.trim() + " " + textField_python.getText() + "\n";

                String lastStr = (String) str.subSequence( str.indexOf(";;", 1082), str.lastIndexOf("fi")+2 );
                lastStr = "                            " + lastStr;
                str = firstStr + input  + lastStr;
                
                try (FileWriter file = new FileWriter(shellFilePath)) {
                    file.write(str);
                    file.flush();   
                } 
                catch (IOException e) { e.printStackTrace(); }
                
            }
            
            if(!textField_javaScript.getText().equals("")){
                String firstStr = (String) str.subSequence( 0, str.indexOf("npm install") );
                
//                System.out.println(str.indexOf("npm install")); 1614
//                int ind = str.indexOf(";;", 1614); // 1614 index of "npm install" 

                String input = (String) str.subSequence( str.indexOf("npm install"), str.indexOf(";;", 1614) );
                input = input.trim() + " " + textField_javaScript.getText() + "\n";

                String lastStr = (String) str.subSequence( str.indexOf(";;", 1614), str.lastIndexOf("fi")+2 );
                lastStr = "                            " + lastStr;
                str = firstStr + input  + lastStr; 
                try (FileWriter file = new FileWriter(shellFilePath)) {
                    file.write(str);
                    file.flush();   
                } 
                catch (IOException e) { e.printStackTrace(); }
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        shellTextArea();
    }
}
