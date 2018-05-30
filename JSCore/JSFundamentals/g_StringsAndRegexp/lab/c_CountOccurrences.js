function countOccurrences(what, text) {

    let result = 0;
    let cIndex = 0;

    while ((cIndex = text.indexOf(what, ++cIndex)) > -1){
        ++result;
    }

    console.log(result);
}


countOccurrences('the', 'The quick brown fox jumps over the lay dog.');
countOccurrences('ma', 'Marine mammal training is the training and caring for marine life such as, ' +
    'dolphins, killer whales, sea lions, walruses, and other marine mammals. ' +
    'It is also a duty of the trainer to do mental and physical exercises to keep the animal healthy and happy.');
