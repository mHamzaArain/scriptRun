package settingsbar;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class PassCommand{
    public static void callApplicationScript(String path_scriptName){
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