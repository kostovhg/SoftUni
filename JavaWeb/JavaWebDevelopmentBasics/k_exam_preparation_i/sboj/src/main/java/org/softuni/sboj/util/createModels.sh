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

function get_file() {
    # IFS='' (or IFS=) prevents leading/trailing whitespace from being trimmed.
    # -r prevents backslash escapes from being interpreted.
    # || [[ -n $line ]] prevents the last line from being ignored if it doesn't end with a \n (since  read returns a non-zero exit code when it encounters EOF).
    while IFS='' read -r line || [[ -n "$line" ]]; do
        echo "Text read from file: $line"
    done < "$1"
}

DEFAULT_FOREGROUND='\e[39m'
BLACK='\e[30m'
RED='\e[31m'
GREEN='\e[32m'
YELLOW='\e[33m'
BLUE='\e[34m'
MAGENTA='\e[35m'
CYAN='\e[36m'
LIGHT_GRAY='\e[37m'
DARK_GRAY='\e[90m'
LIGHT_RED='\e[91m'
LIGHT_GREEN='\e[92m'
LIGHT_YELLOW='\e[93m'
LIGHT_BLUE='\e[94m'
LIGHT_MAGENTA='\e[95m'
LIGHT_CYAN='\e[96m'
WHITE='\e[97m'
BOLD='\033[1m'
RESET_BOLD='\e[21m'
UNDERLINE='\e[4m'
RESET_UNDERLINE='\e[24m'
INVERTED='\e[7m'
NOCOLOR='\e[0m'

declare -a FILEMODELS
FILEMODELS=("entities" "BindingModel" "ServiceModel" "ViewModel" "Repository" "Service" "ServiceImpl" "Bean")

declare -A DIRMAP
DIRMAP[entities]="../domain/entities/"
DIRMAP[BindingModel]="../domain/models/binding/"
DIRMAP[ServiceModel]="../domain/models/service/"
DIRMAP[ViewModel]="../domain/models/view/"
DIRMAP[Repository]="../repository/"
DIRMAP[RepositoryImpl]="../repository/"
DIRMAP[Service]="../service/"
DIRMAP[ServiceImpl]="../service/"
DIRMAP[Bean]="../web/beans/"

declare -a ENTITIES

# declare -A FILESCONTENT
# DILESCONTENT[BindingModel]="some text"
# DILESCONTENT[ServiceModel]="some text"
# DILESCONTENT[ViewModel]="some text"
# DILESCONTENT[Repository]="some text"
# DILESCONTENT[Service]="some text"
# DILESCONTENT[ServiceImpl]="some text"
# DILESCONTENT[CreateBean]="some text"

# Ask the user for list of entities
entities_input=$(get_input "Give me some ${RED}entities${NOCOLOR} names separated with space" "user")

# Iterate dirs to create files
for key in ${FILEMODELS[@]}; do
    K=${key}
    # iterate necessary classes
    for e in ${entities_input} ; do

        e="${e^}"
        # Use different file name for entities
        if [[ ${K} = "entities" ]]; then
            file_name=${DIRMAP[$K]}${e}.java
        elif [[ ${K} = "BindingModel" ]]; then
            file_name=${DIRMAP[$K]}${e}Create${K}.java
        else
            file_name=${DIRMAP[$K]}${e}${K}.java
        fi

        # Prepare sub arrays
#        declare -a ENTITIES_PROPERTIES
#        declare -a ENTITIES_TYPES

        # If the file does not exist, create it
        if [[ ! -e ${file_name} ]]; then
            
            # Get package
            package=${DIRMAP[$K]}
            package="${package/../$__app_name}"
            package="${package//\//\.}"
            package=$(echo ${package} | sed 's/.$//');
            package="org.softuni.${package}"
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
                    parameters_array=$(get_input "Enter ${BLUE}${e}${NOCOLOR} parameters separated by space" "username password email")
                    # index=0
                    for p in ${parameters_array[@]}; do
                        type=$(get_input "Give a type for \033[36m${p}\e[0m" "String")
                        printf "\tprivate %s %s;\n" ${type} ${p} >> ${file_name}
                        #ENTITIES_PROPERTIES["${p}"]="${type}"
                        # ENTITIES_TYPES[${index}]=${type}
                        # index=$(( $index + 1 ))
                    done
                    printf "\n}" >> ${file_name}
                ;;
                BindingModel)
                    printf "public class %s {\n\n" "${e}Create${K}" >> "${file_name}"
                    printf "\n}" >> ${file_name}
                ;;
                ServiceModel)
                    printf "public class %s {\n\n" "${e}${K}" >> ${file_name}
                    printf "\n}" >> ${file_name}
                ;;
                ViewModel)
                    printf "public class %s {\n\n" "${e}${K}" >> ${file_name}

                    printf "\n}" >> ${file_name}
                ;;
                Repository)
                    printf "public class %s extends GenericRepository<%s, String> {\n\n" "${e}${K}" "${e}" >> ${file_name}
                    printf "\n}" >> ${file_name}
                ;;
                RepositoryImpl)
                    printf "public class %s implements %s {\n\n" "${e}${K}" "${e}" >> ${file_name}
                    printf "\n}" >> ${file_name}
                ;;
                Service)
                    printf "public interface %s {\n\n" "${e}${K}" >> ${file_name}
                    printf "\n}" >> ${file_name}
                ;;
                ServiceImpl)
                    printf "public class %s implements %s {\n\n" "${e}${K}" "${e}Service" >> ${file_name}
                    printf "\n}" >> ${file_name}
                ;;
                Bean)
                    printf "import org.modelmapper.ModelMapper;\nimport %s.domain.models.binding.%s;\n" "${__app_name}" "${e}CreateBindingModel" >> ${file_name}
                    for entity in ${entities_input} ; do
                        printf "import %s.service.%sService;\n" "${__app_name}" "${entity^}" >> ${file_name}
                    done

                    printf "\nimport javax.enterprise.context.RequestScoped;\nimport javax.faces.context.FacesContext;\n" >> ${file_name}
                    printf "import javax.inject.Inject;\n" >> ${file_name}
                    printf "import javax.inject.Named;\n" >> ${file_name}
                    printf "import java.io.IOException;\n" >> ${file_name}
                    printf "import java.util.stream.Collectors;\n" >> ${file_name}
                    printf "import java.util.List;\n" >> ${file_name}

                    bean_name=$(get_input "Give ${RED}${e}${NOCOLOR} bean name " "${e,}Bean")
                    printf "\n@Named(\"%s\")\n@RequestScoped\n" ${bean_name} >> ${file_name}
                    printf "public class %s {\n\n" "${e}Bean" >> ${file_name}

                    printf "\tprivate %sCreateBindingModel model;\n\n" ${e} >> ${file_name}
                    printf "\tprivate %sService %sService;\n" ${e} ${e,,} >> ${file_name}
                    printf "\tprivate ModelMapper modelMapper;\n\n" >> ${file_name}
                    printf "\tpublic %s() {\n\t}\n\n" "${e}Bean" >> ${file_name}
                    printf "\t@Inject\n" >> ${file_name}
                    printf "\tpublic %s(%sService %sService, ModelMapper modelMapper) {\n" "${e}Bean" ${e} ${e,,} >> ${file_name}
                    printf "\t\tthis.%sService = %sService;\n" ${e,,} ${e,,} >> ${file_name}
                    printf "\t\tthis.modelMapper = modelMapper;\n\t}\n" >> ${file_name}

                    printf "\n}" >> ${file_name}
                ;;
                
            esac
            
            echo ${file_name} has been created
        else
            echo ${file_name} exists and wont be created
        fi
    done
done

