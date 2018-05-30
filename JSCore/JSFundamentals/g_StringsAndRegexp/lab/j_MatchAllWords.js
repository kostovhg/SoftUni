function matchAllWords(input) {

    console.log(input.split(/[^a-zA-Z0-9_]+/).filter(x => x !== '' ).join('|'))
}

matchAllWords('A Regular Expression needs to have the global flag in order to match all occurrences in the text');
matchAllWords('_(Underscores) are also word characters');