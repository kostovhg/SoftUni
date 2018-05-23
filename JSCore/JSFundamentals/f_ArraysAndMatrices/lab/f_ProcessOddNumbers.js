function processOddNumbers(input) {

    console.log(input
        .filter((e, i) => i % 2 !== 0)
        .map(e => e * 2)
        .reverse()
        .join(' '));

}

processOddNumbers([10, 15, 20, 25]);
processOddNumbers([3, 0, 10, 4, 7, 3]);
