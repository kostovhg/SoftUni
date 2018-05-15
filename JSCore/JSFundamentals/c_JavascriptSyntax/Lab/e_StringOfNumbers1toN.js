function stringOfNumbers1toN(input) {
    let n = parseInt(input);
    let result = '1';
    for(let i = 2; i <= n; i++) {
        result += i;
    }
    console.log(result);
}

stringOfNumbers1toN('11');
stringOfNumbers1toN('2');