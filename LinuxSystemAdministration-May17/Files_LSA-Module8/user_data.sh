#!/bin/bash

#
# Script user_data.sh
#
# The script reads user first_name,
# last_name and place_of_birth
# and append the information to 
# /tmp/user_data.dat
#

read -p "Please enter your first name: " first_name

read -p "Now your last_name: " last_name

read -p "And place of birth: " place_of_birth

echo "$first_name;$last_name;$place_of_birth" >> /tmp/user_data.dat
 
