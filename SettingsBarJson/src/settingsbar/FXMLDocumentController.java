/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settingsbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//import settingsbar.Launch;
//import settingsbar.helpers.ProgramState;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Hamza Arain
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ImageView btn_appLauncher, btn_appInstaller, btn_library, btn_minimize, btn_exit;
            
    @FXML
    private AnchorPane anchor_appLauncher, anchor_appInstaller, anchor_libraryInstaller, anchor_topbar;

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == btn_library){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(true);
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
        }      
        
        else if(event.getTarget() == btn_minimize){
            anchor_appLauncher.setVisible(false);
            anchor_appInstaller.setVisible(false);
            anchor_libraryInstaller.setVisible(false);
        }
    }

    @FXML
    private void close(MouseEvent event) {
        Stage s = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        s.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}