package scriptrun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Hamza Arain
 */
class CommandTools{    
    /**
     * This will execute args in linux terminal
     * =========================================
     * 
     * @author Hamza Arain
     * @param path_scriptName Take path in string
    */
    void callApplicationScript(String path_scriptName){
        String command =  path_scriptName;
        
        try {
            Process process = Runtime.getRuntime().exec("gnome-terminal -e " + command);
        
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        
            reader.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}