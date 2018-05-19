function wordsUppercase(input) {
    let res = input.split(/\W+/)
        .filter(e => e)
        .map(w => w.toUpperCase())
        .join(', ');

    console.log(res);
}

wordsUppercase('Hi, how are you?');