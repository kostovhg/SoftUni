#!/bin/bash - 
#===============================================================================
#
#          FILE: archiver.sh
# 
#         USAGE: ./archiver.sh 
# 
#   DESCRIPTION: Create archive tar.gz of <folder_to_archivate> <destination>
# 
#       OPTIONS: ---
#  REQUIREMENTS: <folder_to_archivate> should exist
#          BUGS: ---
#         NOTES: ---
#        AUTHOR: Hristo Kostov (student), kostovhg@gmail.com 
#  ORGANIZATION: 
#       CREATED: 07/14/2017 01:34:18 PM
#      REVISION: 0
#===============================================================================

set -o nounset                              # Treat unset variables as an error

if [ $# -ne 2 ]; then
	echo "Please provide two parameters: folder to be archivated and destination"
	echo "Usage: $0 <folder_to_archivate> <destination>"
	exit 1;
fi


if [ ! $1 ]; then
	echo "Folder $1 doesn't exist. Please provide existing folder name and path"
	echo "For example: #0 /etc/ ~/backups/etc_backup"
	exit 1;
fi


if [ -e $2 ]; then
	echo "File $2 already exist. Please provide name and path that"
	echo "don't exist."
	exit 1;
fi

destination=$2
des_dir=$(dirname "${destination}")
filename=$(basename "$destination")
extension="${filename##*.}"
file="${filename%.*}"
cdir=$(pwd)

# echo "directory: $des_dir"
# echo "full filename: $filename"
# echo "extension: $extension"
# echo "file name: $file"

mkdir -p "$cdir/$des_dir";

tar -cvzf "$cdir/${des_dir}/${file}.tar.gz" $1

exit 0
