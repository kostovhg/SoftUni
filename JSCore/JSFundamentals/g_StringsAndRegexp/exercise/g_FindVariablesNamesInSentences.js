function findVariablesNamesInSentences(input) {

    let regex = /^((?:_)([a-zA-Z0-9]+))$/;
    let result = [], match;

    for (const el of input.split(/\s+/).filter(e => e !== '')) {
        //console.log(el);
        if(match = regex.exec(el)){
            result.push(match[2])
        }
    }

    console.log(result.join(','));

}

findVariablesNamesInSentences('The _id and _age variables are both integers.');
findVariablesNamesInSentences('Calculate the _area of the _perfectRectangle object.');
findVariablesNamesInSentences('__invalidVariable _evenMoreInvalidVariable_ _validVariable');
