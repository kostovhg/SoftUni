#!/bin/bash - 
#===============================================================================
#
#          FILE: processes.sh
# 
#         USAGE: ./processes.sh 
# 
#   DESCRIPTION: When executed the script will get the count of all processes on
#		 the system and store it in a local variable. Then it will 
# 		 execute a mySQL statement to store the value in the 
# 		 processes tabla in process_data database.
# 
#       OPTIONS: ---
#  REQUIREMENTS: mariadb, mariadb-server
#          BUGS: ---
#         NOTES: todo: automate count of processes that should be substracted
#        AUTHOR: Hristo Kostov (), kostovhg@gmail.com
#  ORGANIZATION: SoftUni
#       CREATED: 07/15/2017 05:10:47 PM
#      REVISION: 0
#===============================================================================

set -o nounset                              # Treat unset variables as an error

count='ps -c | wc -l'
count_=`eval $count`

# removing the colums names, the wc and pc processes (additionaly we should
# remove ssh too), and current createt by this script bash copy process
count="$(($count_ - 4 ))"

mysql -uroot -pPassword1 process_data -e "INSERT INTO processes (amount) VALUES ($count)"

