# Script Run

![Contributors](svg_img/test.svg) ![BuildStatus](svg_img/contrib.svg)

## Phenomenon
The phenomenon of this project is "Automation".

## About Project
We have divided this project into three features.

### Features

#### Feature 1: Application Launcher
To launch application rahter than going inside main root then run script to launch application.

##### TODO: Implementations
* [ ] Add Button(Many buttons can be added inside pane) 
* [ ] Add Button -> Add software name
* [ ] Add Button -> Add software path
* [ ] Add Button -> Add software image
* [ ] Remove Button(Many buttons can be removed inside pane)

#### Feature 2: Installation of Software
This will record the software you install in Linux Machine next time reinstallation of softwares can be done by running master Bash script.
##### TODO: Implementations
* [ ] Software name
* [ ] Software installation commands(If applicable)
* [ ] Software path(If applicable)

#### Feature 3: Languages library 
Installation of libraries when you reinstall software
##### TODO: Implementations
* [ ] Python     -> Add library name 
* [ ] Javascript -> Add library name

## Requirements
### Front End
* JavaFX(JDK 14 with javaFX 12) 
* Gluon Scene builder

### Back End
* Core java 
* Bash script 
* MySQL or MongoDB as database
* Docker for making images

## Compilation & Run in Linux Machine
As javaFX is hectic for Linux machine we will compile it manually by following steps:
1.  Make folder called `Project`.
2.  Inside this folder `Project` make two more projects called `src` and `classes`.
3. Directory must be `Project` to follow other steps.
4. Paste only `*.java` files inside `src` folder.
5. Give permission to all *.java file for compilation.
    ```sudo chmod 777 src/*.java```
6. For compilation of `*.java` files
    ```javac --module-path $PATH_TO_FX --add-modules="javafx.controls","javafx.fxml" -d classes src/*.java```
7. To run java file
    ```cd classes ;  java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml ClassPath.className```

## Liscense
GNU

