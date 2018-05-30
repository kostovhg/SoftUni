function printLetters(input) {

    input.split('')
        .forEach((x, i) =>
            console.log(`str[${i}] -> ${x}`))

}

printLetters('Hello, World!');
printLetters('SoftUni');
