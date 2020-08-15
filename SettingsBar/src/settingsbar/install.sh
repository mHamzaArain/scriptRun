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
                            echo "Preparing python essentials... 
                            apt-get update
                            npm install
                            ;;                                                                                                                                                                                 
        esac
	done
fi