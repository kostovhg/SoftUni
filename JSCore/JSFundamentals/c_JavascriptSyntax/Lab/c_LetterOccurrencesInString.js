function letterOccurrencesInString(str, letter) {
    let count = 0;
    str.split('').forEach(l => {
        if (l === letter)
            count++;
    });
    console.log(count);
}

letterOccurrencesInString('hello', 'l');
letterOccurrencesInString('panther', 'n');