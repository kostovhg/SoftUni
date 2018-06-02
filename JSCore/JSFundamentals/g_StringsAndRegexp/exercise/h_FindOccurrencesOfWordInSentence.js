function findOccurrencesOfWordInSentence(input) {

    let text = arguments[0].split(/\s+/);
    let count = 0;
    let r = '^' + arguments[1] + '[.,!?:;]*$';
    let regex = new RegExp(r, 'i');

    for (let word of text) {
        if(regex.test(word)) count++;
    }

    console.log(count);
}

findOccurrencesOfWordInSentence('The waterfall was so high, that the child couldn’t see its peak.', 'the');

findOccurrencesOfWordInSentence('How do you plan on achieving that? How? How can you even think of that?', 'how');

findOccurrencesOfWordInSentence('There was one. Therefore I bought it. I wouldn’t buy it otherwise.', 'there');
