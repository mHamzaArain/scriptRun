/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptrun;


import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author Ayesha Hamza Arain, Zehra Riaz
 */
class GeneralTools {
    /**
     * To close application
    */
    void exitSoftware(MouseEvent event){
        Stage s = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        s.close();   
    }
 
    /**
     * To write file
    */    
    void fileWrite(String filePath, String str){
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(str);
            file.flush();   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


