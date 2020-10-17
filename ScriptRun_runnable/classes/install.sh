#! /bin/bash

# ====================================================
# By: Muhammad Hamza Hanif
# Email: hamzaarain1999@gmail.com

# Follow me on Github: https://github.com/mHamzaHanif
# Follow me on Youtube: https://www.youtube.com/channel/UCHKc6xH3J-WA3PwZsUhWqmg?view_as=subscriber

# ##################### Thanks ####################### 


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
                    3 "Xdman" off
                    4 "VLC Player" off
                    5 "Monodevelop" off
                    6 "Pycharm" off
                    7 "IntelliJ" off
                    8 "" off
                    9 "Arduino-1.8.9" off
                    10 "Sublime Text 3*" off
                    11 "Anaconda" off
                    12 "Zoom" off
                    13 "MySql Workbench" off
                    14 "VNC Viewer" off
                    15 "Gluon Scenebuilder(Java)" off
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
                            pip3 install numpy pandas matplotlib scikit-learn tensorflow nltk keras seaborn beautifulsoup4 requests
                            ;;                            
                        2)
                            echo "Preparing python essentials..." 
                            apt-get update
                            npm install 
                            ;;                            
                        3)
                            echo "Xdman..." 
                            /media/hamza/linux1/Softwares/xdm-2018-x64/install.sh
                            ;;                            
                        4)
                            echo "VLC Player..." 
                            apt-get update
                            snap install vlc
                            ;;                            
                        5)
                            echo "Monodevelop..." 
                            # Ubuntu 18.04 (i386, amd64, armhf)
                            sudo apt install apt-transport-https dirmngr
                            sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 3FA7E0328081BFF6A14DA29AA6A19B38D3D831EF
                            echo "deb https://download.mono-project.com/repo/ubuntu vs-bionic main" | sudo tee /etc/apt/sources.list.d/mono-official-vs.list
                            sudo apt update
                            
                            # Installation
                            sudo apt-get install monodevelop
                            ;;                            
                        6)
                            echo "Pycharm..." 
                            /media/hamza/linux/Software/pycharm-community-2019.3.1/bin/pycharm.sh
                            apt-get update
                            ;;                            
                        7)
                            echo "IntelliJ..." 
                            /media/hamza/linux/Software/ideaIC-2019.3.1/idea-IC-193.5662.53/bin/idea.sh
                            apt-get update
                            ;;                            
                        8)
                            echo "..." 
                            # Installing git
                            apt install git-all
                            
                            # Installing smartgit-linux-19_1_4
                            /media/hamza/linux/Software/smartgit-linux-19_1_4/smartgit/bin/smartgit.sh
                            ;;                            
                        9)
                            echo "Arduino-1.8.9..." 
                            # Installing arduino-1.8.9
                            /media/hamza/linux/Software/arduino-1.8.9/install.sh
                            ;;                            
                        10)
                            echo "Sublime Text 3*..." 
                            add-apt-repository ppa:webupd8team/sublime-text-3 -y
                            apt update
                            apt install sublime-text-installer -y
                            ;;                            
                        11)
                            echo "Anaconda..." 
                            # Intalling Anaconda v2019.10-x86_64
                            bash /media/hamza/linux1/Softwares/Anaconda3-2019.10-Linux-x86_64.sh
                            apt-get update
                            source ~/.bashrc
                            conda install -c conda-forge keras
                            git clone --recursive https://github.com/dmlc/xgboostcd xgboost
                            mkdir build
                            cd build
                            apt-get install cmake
                            cmake ..
                            make -j4 
                            ;;                            
                        12)
                            echo "Zoom..." 
                            sudo dpkg -i /media/hamza/linux1/Softwares/zoom_amd64.deb
                            ;;                            
                        13)
                            echo "MySql Workbench..." 
                            sudo dpkg -i /media/hamza/linux1/Softwares/mysql-workbench-community_8.0.18-1ubuntu18.04_amd64.deb 
                            ;;                            
                        14)
                            echo "VNC Viewer..." 
                            sudo dpkg -i /media/hamza/linux1/Softwares/VNC-Viewer-6.20.113-Linux-x64.deb
                            ;;                            
                        15)
                            echo "Gluon Scenebuilder(Java)..." 
                            sudo dpkg -i /media/hamza/linux1/Softwares/scenebuilder-11.0.0.deb
                            ;;                                                                                                                                                                                                             
        esac
	done
fi
