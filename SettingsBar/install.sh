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
	options=(1 "Sublime Text 3" off    # any option can be set to default to "on"
	         2 "Xdman" off
	         3 "Vlc" off
             4 "Monodevelop" off
             5 "Python pip3-libraries Data Science." off
             6 "Anaconda v2019.10-x86_64(path given)" off
             7 "pycharm-community-2019.3.1(path given)" off
             8 "IntelliJ IDEA v2019.3.1(path given)" off
             9 "git/smartgit-v19_1_4(path given)" off
             10 "arduino-1.8.9(path given)" off
			 )
        choices=$("${cmd[@]}" "${options[@]}" 2>&1 >/dev/tty)
		clear
    for choice in $choices
		do
		    case $choice in
	        1)
	            # Installing Sublime Text 3*
				echo "Installing Sublime Text"
				add-apt-repository ppa:webupd8team/sublime-text-3 -y
				apt update
				apt install sublime-text-installer -y
				;;

            2)
                # Installing Xdman
                echo "Installing Xdman"
                add-apt-repository ppa:noobslab/apps -y        
                apt-get update        
                apt-get install xdman 
                ;;

            3)
                # Installing Vlc
                echo "Installing Vlc"
                apt-get update 
                snap install vlc 
                ;;
            
            4)
                # Installing Monodevelop
                apt install apt-transport-https dirmngr
                apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 3FA7E0328081BFF6A14DA29AA6A19B38D3D831EF -y
                echo "deb https://download.mono-project.com/repo/ubuntu vs-bionic main" | sudo tee /etc/apt/sources.list.d/mono-official-vs.list
                apt-get update
                apt-get install monodevelop -y
                ;;

            5)
                # Preparing python essentials...
                # Installing pip3
                apt-get install python3-pip

                # Instaling libraries...
                apt-get update
                pip3 install numpy pandas matplotlib scikit-learn tensorflow nltk keras seaborn beautifulsoup4 requests
                ;;
            
            6)
                # Intalling Anaconda v2019.10-x86_64
                cd /media/hamza/linux/Software
                bash Anaconda3-2019.10-Linux-x86_64.sh
                apt-get update
                ;;
            
            7)
                # Installing Pycharm-community-2019.3.1
                cd /media/hamza/linux/Software/pycharm-community-2019.3.1/bin
                ./pycharm.sh
                apt-get update
                ;;
            
            8)
                # Installing IntelliJ IDEA v2019.3.1
                cd /media/hamza/linux/Software/ideaIC-2019.3.1/idea-IC-193.5662.53/bin
                ./idea.sh
                apt-get update
                ;;
            
            9)
                # Installing git
                apt install git-all

                # Installing smartgit-linux-19_1_4
                cd /media/hamza/linux/Software/smartgit-linux-19_1_4/smartgit/bin
                ./smartgit.sh
                ;;
            
            10)
                # Installing arduino-1.8.9
                cd /media/hamza/linux/Software/arduino-1.8.9
                ./install.sh
                ;;            
            11)
                echo "..." 
                
                ;;            

        esac
	done
fi
