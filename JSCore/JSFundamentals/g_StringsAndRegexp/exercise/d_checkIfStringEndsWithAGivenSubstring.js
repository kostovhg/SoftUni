function checkIfStringEndsWithAGivenSubstring(input) {

    console.log(arguments[0].endsWith(arguments[1]));
}

checkIfStringEndsWithAGivenSubstring('This sentence ends with fun?', 'fun?');
checkIfStringEndsWithAGivenSubstring('This is Houston, we have…','We have…');
checkIfStringEndsWithAGivenSubstring('The new iPhone has no headphones jack.','o headphones jack.');
