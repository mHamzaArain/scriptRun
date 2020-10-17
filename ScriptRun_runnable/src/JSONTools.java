/*
 * Everything all JSON related methods
 */
package scriptrun;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ayesha Ghani, Hamza Arain
 */
public class JSONTools {
    /**
     * This call after btn_1-8 striked to reach separated paths from json file
     * ========================================================================
     * 
     * @author Ayesha Ghani
     * 
     * @param event Mouse pressed
     * @param configFilePath json file path in String
     */
    void jsonExecutor(ActionEvent event, String configFilePath){
        //// Read json
        //JSON parser object to parse read file
        int button_number =  Integer.parseInt((String) event.toString().subSequence(46, 47));
        
        JSONParser jsonParser = new JSONParser();
        JSONArray buttons = null;
        try (FileReader reader = new FileReader(configFilePath))
        {
            // Read JSON file
            Object obj = jsonParser.parse(reader);
            buttons = (JSONArray) obj;
            buttons.forEach(each -> {
                parseBtnObj( (JSONObject) each, button_number);
            } );
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }   
    } 
    
    
    /**
     * This will redirect string path from jsonExecutor(ActionEvent, String)
     *  to appLauncherButtonExecution(String)
     * =======================================================================
     * 
     * @author Ayesha Ghani
     * 
     * @param b JSONObject
     * @param i button number 
     */
    void parseBtnObj(JSONObject b,int i) {
        //Get employee object within list
        JSONObject btn_attr = (JSONObject) b.get("button-attributes");
        JSONObject btn_n = (JSONObject) btn_attr.get("button-" + i);
        appLauncherButtonExecution((String) btn_n.get("path"));
    }
    
    
    /**
     * This is will send path to call execution
     * ==========================================
     * 
     * @author Hamza Arain
     * @param path String path
     */
    void appLauncherButtonExecution(String path){
//         System.out.println(path);
        CommandTools ct = new CommandTools();
        ct.callApplicationScript(path);
    }
}

