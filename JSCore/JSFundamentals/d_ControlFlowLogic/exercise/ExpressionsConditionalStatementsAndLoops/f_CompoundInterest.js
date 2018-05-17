function compoundInterests(inputArray) {
    // the principal sum
    let P = inputArray[0];
    // the nominal interest rate (to percent)
    let i = inputArray[1] / 100;
    // the compounding frequency (from period in months to yearly frequency)
    let n = 12 / inputArray[2];
    // overall length of time the interest is applied
    let t = inputArray[3];
    // compound interest
    let F = P * ((1 + (i / n)) ** (n * t));

    console.log(F.toFixed(2))
}

compoundInterests([1500, 4.3, 3, 6]); //1938.84
compoundInterests([100000, 5, 12, 25]);