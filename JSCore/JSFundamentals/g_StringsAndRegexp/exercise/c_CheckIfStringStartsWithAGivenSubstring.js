function checkIfStringStartsWithAGivenSubstring(input) {

    let [text, what] = [arguments[0], arguments[1]];

    console.log(text.startsWith(what));
}

checkIfStringStartsWithAGivenSubstring(
    'How have you been?',
    'how');
checkIfStringStartsWithAGivenSubstring(
    'The quick brown fox…',
    'The quick brown fox…');
checkIfStringStartsWithAGivenSubstring(
    'Marketing Fundamentals, starting 19/10/2016',
    'Marketing Fundamentals, sta');