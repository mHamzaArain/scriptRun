/*
 * BASH related methods here
 */
package scriptrun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hamza Arain
 */
public class BASHTools {
    // Essential patterns
    private final String LAST_SOFTWARE_NUMBER_PATTERN = "([0-9]*[0-9] \")"; 
    private final String DOUBLE_SEMI_COLON_PATTERN = ";;";
    private final String fi_PATTERN = "fi";
    private final String DOUBLE_SEMI_COLON_BRACKET_PATTERN = "([;;])";
    
    private final String PYTHON_LIBRARY_PIP3_PATTERN = "pip3 install";
    private final String JAVASCRIPT_LIBRARY_NPM_PATTERN = "npm install";
    
    
    /**
     * To input string fetched from textbox of javafx.
     * ===============================================
     * 
     * @author Hamza Arain
     * 
     * @param lastSoftwareNumber: Already fetched from shell file of last software number
     * @param softwareName
     * @param commands: Software name
     * @return str: This will gives readily string to insert in file
     */    
    protected String[] insertSoftwareCommandsInScript(int lastSoftwareNumber, String softwareName, String[] commands){
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
     * 
     * @author Hamza Arain
     * 
     * @param text: Text from file
     * @param regex: Pattern
     * @return lastIndex: Last index of matched pattern
     */
    protected int printMatches(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int lastIndex = -1;
        while (matcher.find()) {
            lastIndex = matcher.start();
        }
        return lastIndex;
    }
    
    /**
     * This will insert software name and commands
     * ============================================
     * 
     * @author Hamza Arain
     * 
     * @param shellFilePath shell path
     * @param arguments commands line args
     * @param textField textField_softwareName.getTExt() method
     */
    void softwareInsertion(String shellFilePath, String[] arguments, String textField){
        String str = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(shellFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
                str = str + line + "\n";
            }
            
            // Last software number extracted from shell file
            int indexOfLastSoftwareNumber = printMatches(str, this.LAST_SOFTWARE_NUMBER_PATTERN) + 1;
            String lastNumber = (String) str.subSequence( indexOfLastSoftwareNumber-1, indexOfLastSoftwareNumber+1 );
            int lastSoftwareNumber = Integer.parseInt(lastNumber.trim());
            
            int middleIndex = str.indexOf("\n", indexOfLastSoftwareNumber);

            String[] input = insertSoftwareCommandsInScript(lastSoftwareNumber, textField, arguments);
            
            // Last double semi colon extracted from shell file
            int indexOfLastSemiColon = this.printMatches(str, this.DOUBLE_SEMI_COLON_BRACKET_PATTERN) + 1;     
            
            String firstStr = (String) str.subSequence(0, middleIndex+1);
            String SecondStr = (String) str.subSequence( middleIndex, indexOfLastSemiColon );
            String lastStr = (String) str.subSequence( indexOfLastSemiColon, str.lastIndexOf(this.fi_PATTERN)+2 );
        
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
    }
    
    /**
     * This will insert python & javascript libraries
     * ===============================================
     * 
     * @author Hamza Arain
     * @param shellFilePath
     * @param textFieldPython
     * @param textFieldJavaScript 
     */
    void libraryInsertion(String shellFilePath, String textFieldPython, String textFieldJavaScript){
        String str = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(shellFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null){
                str = str + line + "\n";
            }
            if (!textFieldPython.equals("")){
                String firstStr = (String) str.subSequence( 0, str.indexOf(this.PYTHON_LIBRARY_PIP3_PATTERN) );
                
//                System.out.println(str.indexOf("pip3 install"));
                int ind = str.indexOf(this.DOUBLE_SEMI_COLON_PATTERN, 1082); // 1082 index of "pip3 install" 

                String input = (String) str.subSequence( str.indexOf(this.PYTHON_LIBRARY_PIP3_PATTERN), str.indexOf(this.DOUBLE_SEMI_COLON_PATTERN) );
                input = input.trim() + " " + textFieldPython + "\n";

                String lastStr = (String) str.subSequence( str.indexOf(this.DOUBLE_SEMI_COLON_PATTERN, 1082), str.lastIndexOf(this.fi_PATTERN)+2 );
                lastStr = "                            " + lastStr;
                str = firstStr + input  + lastStr;
                
                try (FileWriter file = new FileWriter(shellFilePath)) {
                    file.write(str);
                    file.flush();   
                } 
                catch (IOException e) { e.printStackTrace(); }
            }
            
            if(!textFieldJavaScript.equals("")){
                String firstStr = (String) str.subSequence( 0, str.indexOf(this.JAVASCRIPT_LIBRARY_NPM_PATTERN) );
//                System.out.println(firstStr);
//                System.out.println(str.indexOf("npm install")); //1614
//                System.out.println(str.substring(2132, 2132+12));
                String input = (String) str.subSequence( str.indexOf(this.JAVASCRIPT_LIBRARY_NPM_PATTERN), str.indexOf(this.DOUBLE_SEMI_COLON_PATTERN, 2144) );
//                System.out.println(input);
                input = input.trim() + " " + textFieldJavaScript + "\n";
//                System.out.println(input);
                String lastStr = (String) str.subSequence( str.indexOf(this.DOUBLE_SEMI_COLON_PATTERN, 2144), str.lastIndexOf(this.fi_PATTERN)+2 );
                lastStr = "                            " + lastStr;
//                    System.out.println(lastStr);
                str = firstStr + input  + lastStr; 
//                System.out.println(str);
                try (FileWriter file = new FileWriter(shellFilePath)) {
                    file.write(str);
                    file.flush();   
                } 
                catch (IOException e) { e.printStackTrace(); }
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}