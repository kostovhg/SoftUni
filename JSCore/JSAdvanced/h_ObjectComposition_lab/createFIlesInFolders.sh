#!/bin/bash
for d in *; do
    if [[ -d $d ]]; then
        SUBSTR=${d#???}
        LOWERLETTER=$(echo "${d:2:1}" | tr [A-Z] [a-z])
        FUNCTION=$LOWERLETTER$SUBSTR
        FILE=$d/$FUNCTION.js
        if [[ ! -f $FILE ]]; then
            CONTENT=$"function ${FUNCTION}(input){\n\n}"
            echo -e $CONTENT > $FILE
        fi
    fi
done

