/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settingsbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hamza Arain
 */
public class InsertShellFile {

    
    /**
     * To input string fetched from textbox of javafx.
     * ===============================================
     * @param lastSoftwareNumber: Already fetched from shell file of last software number
     * @param softwareName
     * @param commands: Software name
     * @return str: This will gives readily string to insert in file
     */    
    public static String[] insertSoftwareCommandsInScript(int lastSoftwareNumber, String softwareName, String[] commands){
        int nextSoftwareNumber = lastSoftwareNumber + 1;
        String str1 = "                    " + nextSoftwareNumber + " \"" + softwareName + "\" off";
        
        String str2 = 
                "                            \n" +
                "                        " + nextSoftwareNumber + ")\n" +
                "                            echo \"" + softwareName + "...\" \n" 
                ;
        for (String command : commands) { 
            str2 += "                            "+ command +"\n";
        }
       
        str2 += "                            ;;";
        
        String[] strList = {str1, str2};
        return strList;
    }
    
    /**
     * This will gives last string of matched pattern.
     * ===============================================
     * @param text: Text from file
     * @param regex: Pattern
     * @return lastIndex: Last index of matched pattern
     */
    public static int printMatches(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int lastIndex = -1;
        while (matcher.find()) {
            lastIndex = matcher.start();
        }
        return lastIndex;
    }
}
