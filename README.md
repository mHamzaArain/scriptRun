# Script Run

![Contributors](svg_img/test.svg) ![BuildStatus](svg_img/contrib.svg)

## Phenomenon
The phenomenon of this project is "Automation".

## About Project
We have divided this project into three features.

## Basic Script
This is a BASH script that will auto install applications on Linux OS.
This is very simple script that everything you will enter application & application libraries. 
   ```
   Filename: install.sh
   ``` 

   ```   
   if [[ $EUID -ne 0 ]]; then
      echo "This script must be run as root" 
      exit 1

   else
      Update and Upgrade
      echo "Updating and Upgrading"
      apt-get update && sudo apt-get upgrade -y

      sudo apt-get install dialog
      cmd=(dialog --separate-output --checklist "Press Spacebar to select Software you want to install:" 22 76 16)
      options=(
                     1 "Python essentials and its libraries" off
                     2 "JavaScript essentials and its libraries" off
                  )
         choices=$("${cmd[@]}" "${options[@]}" 2>&1 >/dev/tty)
         clear
      for choice in $choices
         do
            case $choice in    
                           1)
                              echo "Preparing python essentials..."
                              # Installing pip3
                              apt-get install python3-pip


                              # Instaling libraries...
                              apt-get update
                              pip3 install
                              ;;                            
                           2)
                              echo "Preparing python essentials..." 
                              apt-get update
                              npm install
                              ;;
         esac
      done
   fi 
   ```

### Features

#### Feature 1: Application Launcher
To launch application rahter than going inside main root then run script to launch application.

##### TODO: Implementations
* [x] Edit Button(Many buttons can be edit inside pane) 
* Note: Add/Removal of button handled with json file.
   
   ```Filename: buttonConfig.json```

   ```
   [{
    "button-attributes":{
        "button-1": {
            "button_no": 1,
            "active": false,
            "position": "(0, 0)",
            "name": "None",
            "path": "None"
        },
        "button-2": {
            "button_no": 2,
            "active": false,
            "position": "(0, 1)",
            "name": "None",
            "path": "None"
        },
        "button-3": {
            "button_no": 3,
            "active": false,
            "position": "(1, 0)",
            "name": "None",
            "path": "None"
        },
        "button-4": {
            "button_no": 4,
            "active": false,
            "position": "(1, 1)",
            "name": "None",
            "path": "None"
        },
        "button-5": {
            "button_no": 5,
            "active": false,
            "position": "(2, 0)",
            "name": "None",
            "path": "None"
        },
        "button-6": {
            "button_no": 6,
            "active": false,
            "position": "(2, 1)",
            "name": "None",
            "path": "None"
        },
        "button-7": {
            "button_no": 7,
            "active": false,            
            "position": "(3, 0)",
            "name": "None",
            "path": "None"
        },
        "button-8": {
            "button_no": 8,
            "active": false,
            "position": "(3, 1)",
            "name": "None",
            "path": "None"
        }
    }
   }]
   ```

#### Feature 2: Installation of Software
This will record the software you install in Linux Machine next time reinstallation of softwares can be done by running master Bash script.
##### TODO: Implementations
* [x] Text field of software name.
* [x] Text area for software installation commands.
* [x] Insert button.

#### Feature 3: Languages library 
Installation of libraries when you reinstall software.
##### TODO: Implementations
* [x] Text field for Python     -> Add library name 
* [x] Text field for Javascript -> Add library name

## Requirements
### Front End
* JavaFX(JDK 14/JDK 1.8 with javaFX 12) 
* Gluon Scene builder

### Back End
* Core java 
* Bash script 

## Compilation & Run in Linux Machine
As javaFX is hectic for Linux machine we will compile it manually by following steps:
1.  Make folder called `Project`.
2.  Inside this folder `Project` make two more projects called `src` and `classes`.
3. Directory must be `Project` to follow other steps.
4. Paste only `*.java` files inside `src` folder.
5. Give permission to all *.java file for compilation.
    ```
    sudo chmod 777 src/*.java
    ```
6. For compilation of `*.java` files
   
   ```
   javac --module-path $PATH_TO_FX --add-modules="javafx.controls","javafx.fxml" -cp  "json-simple-1.1.jar"  -d   classes src/*.java
   ```
7. To run java file
   
   ```
   cd classes; java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp "../json-simple-1.1.jar:." settingsbar.SettingsBar
   ```
## Execution of ```SettingsBar.jar``` File 
   To run this application.

   ```
   java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp "lib/json-simple-1.1.jar:."  -jar SettingsBar.jar
   ```

## Liscense
GNU

