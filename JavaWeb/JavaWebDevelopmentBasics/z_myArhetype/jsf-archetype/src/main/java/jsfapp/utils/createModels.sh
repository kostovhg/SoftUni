#!/usr/bin/env bash
# Bash3 Boilerplate. Copiright (c) 2014, kvz.io

set -o errexit
set -o pipefail
set -o nounset
#set -o xtrace

# Set magic variables for current file & dir
__dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
__file="${__dir}/$(basename "${BASH_SOURCE[0]}")"
__base="$(basename ${__file} .sh)"
__root="$(cd "$(dirname "${__dir}")" && pwd)" # <-- change this as it depends on your app
__app_name="${__root##*\/}"

arg1="${1:-}"

trap ctrl_c INT

function ctrl_c() {
    echo "** Trapped CTRL-C"
}

# Function to return camelCase strings to lowercase_underscore string
# Not used because not used
# Function accept a parrameter in camelCase
function lowercase_underscore() {
    tn=$(echo "${1}" |sed -r 's/([a-z]+)([A-Z][a-z]+)/\1_\l\2/g')
    # Set tn to lowercase
    tn="${tn,,}"
    # Ask for table name
    echo "Give table name"
    read -e -i "$tn" input
    echo"${input:-table_name}"
}

# Read the user input
# parameter1 - Message to be printed to the user
# parameter2 - Optional default value
# Example:
#   var=$(get_input "Question to user" "Default answer")
function get_input(){
    default="${2}"
    # read -p "${1} [${2}]: " -e answer
    read -p "$(echo -e ${1} [${2}]: ) "  -e answer
    echo "${answer:-${default}}"
}

# Read the user input
# parameter1 - Message to be printed to the user
# parameter2 - Optional default value
function get_input_old(){
    
    read -p "${1} [${default}] " answer
    : ${answer:-$default}
    echo "${answer}"
}

BLACK='\E[30;47m'
RED='\E[31;47m'
GREEN='\E[32;47m'
YELLOW='\E[33;47m'
BLUE='\E[34;47m'
MAGENTA='\E[35;47m'
CYAN='\E[36;47m'
WHITE='\E[37;47m'
BOLD='\033[1m'
NOCOLOR='\e[0m'

declare -a FILEMODELS
FILEMODELS=("entities" "BindingModel" "ServiceModel" "ViewModel" "Repository" "Service" "ServiceImpl" "CreateBean")

declare -A DIRMAP
DIRMAP[entities]="../domain/entities/"
DIRMAP[BindingModel]="../domain/models/binding/"
DIRMAP[ServiceModel]="../domain/models/service/"
DIRMAP[ViewModel]="../domain/models/view/"
DIRMAP[Repository]="../repository/"
DIRMAP[Service]="../service/"
DIRMAP[ServiceImpl]="../service/"
DIRMAP[CreateBean]="../web/beans/"

declare -a ENTITIES
# Prepare sub arrays
declare -a ENTITIES_PROPERTIES
declare -a ENTITIES_TYPES

# declare -A FILESCONTENT
# DILESCONTENT[BindingModel]="some text"
# DILESCONTENT[ServiceModel]="some text"
# DILESCONTENT[ViewModel]="some text"
# DILESCONTENT[Repository]="some text"
# DILESCONTENT[Service]="some text"
# DILESCONTENT[ServiceImpl]="some text"
# DILESCONTENT[CreateBean]="some text"

# Ask the user for list of entities
entities_input=$(get_input "Give me some${RED} entities ${NOCOLOR}names separated with space" "user")

# Iterate dirs to create files
for key in ${FILEMODELS[@]}; do
    K=${key}
    # iterate necessary classes
    for e in ${entities_input} ; do
        e="${e^}"
        # Use different file name for entities
        if [[ $K != "entities" ]]; then
            file_name=${DIRMAP[$K]}${e}${K}.java
        else
            file_name=${DIRMAP[$K]}${e}.java
        fi
        
        # If the file does not exist, create it
        if [[ ! -e ${file_name} ]]; then
            
            # Get package
            package=${DIRMAP[$K]}
            package="${package/../$__app_name}"
            package="${package//\//\.}"
            package=$(echo ${package} | sed 's/.$//');
            touch ${file_name};
            
            # set the package for the file
            printf "package %s;\n\n" ${package} > ${file_name}
            
            # Switch case for different files
            case ${K} in
                
                entities)
                    printf "import javax.persistence.Entity;\nimport javax.persistence.Table;\n\n" >> ${file_name}
                    printf "@Entity\n" >> ${file_name}
                    table_name=$(get_input "Give ${RED}${e}${NOCOLOR} entity table name " "${e,,}s")
                    
                    #table_name=$(lowercase_underscore "${e}") # TODO: create such function
                    printf "@Table(name = \"%s\")\n" ${table_name} >> ${file_name}
                    printf "public class %s extends BaseEntity {\n\n" ${e} >> ${file_name}
                    parameters_array=$(get_input "Enter ${RED}${e}${NOCOLOR} parameters separated by space" "username password email")
                    index=0
                    for p in ${parameters_array[@]}; do
                        type=$(get_input "Give a type for \033[36m${p}\e[0m" "String")
                        printf "\tprivate %s %s;\n" ${type} ${p} >> ${file_name}
                        ENTITIES_PROPERTIES[${index}]=${p}
                        ENTITIES_TYPES[${index}]=${type}
                        index=$(( $index + 1 ))
                    done
                    printf "\n}" >> ${file_name}
                ;;
                BindingModel)
                    printf "public class %s {\n\n" "${e}${K}" >> ${file_name}
                    echo "That was BindingModel"
                ;;
                ServiceModel)
                    echo "That was ServiceModel"
                ;;
                ViewModel)
                    echo "That was ViewModel"
                ;;
                Repository)
                    echo "That was Repository"
                ;;
                Service)
                    echo "That was Service"
                ;;
                ServiceImpl)
                    echo "That was ServiceImpl"
                ;;
                CreateBean)
                    echo "That was CreateBean"
                ;;
                
            esac
            
            echo ${file_name} has been created
        else
            echo ${file_name} exists and wont be created
        fi
    done
done

