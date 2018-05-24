function printArrayWithGivenDelimiter(input) {
    let delimiter = input.pop();

    console.log(input.join(delimiter))
}

printArrayWithGivenDelimiter([
    'One',
    'Two',
    'Three',
    'Four',
    'Five',
    '-'
]);

printArrayWithGivenDelimiter([
    'How about no?',
    'I',
    'will',
    'not',
    'do',
    'it!',
    '_'
]);

