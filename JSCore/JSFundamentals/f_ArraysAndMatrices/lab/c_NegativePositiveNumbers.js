function negativePositiveNumbers(input) {

    let result = [];
    input.forEach(x => (x < 0) ? result.unshift(x) : result.push(x));
    result.forEach(x => console.log(x));

}

negativePositiveNumbers([7, -2, 8, 9]);
negativePositiveNumbers([3, -2, 0, -1]);