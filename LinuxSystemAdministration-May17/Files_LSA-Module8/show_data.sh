#!/bin/bash

#
# show_data.sh
#

# The script receive a file_name
# read its lines and print tehem
# in format "Row #1:" + line

if [ $# -ne 1 ]; then
	echo "Usage: $0 <file_name>"
	exit 1
fi

i=1

while IFS= read -r line; do
	echo "Row #$i: $line"
	i=$((i+1))
done < $1

exit 0
