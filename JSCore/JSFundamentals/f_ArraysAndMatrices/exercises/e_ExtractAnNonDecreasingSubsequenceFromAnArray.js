function extractAnNonDecreasingSubsequenceFromAnArray(input) {


    console.log(input.filter((x, i) => Math.max(...input.slice(0, i)) < x).join('\n'));
    //console.log((input.slice(0, 1)).concat(input.filter((x, i) => input.slice(0, i).pop() < x)).join('\n'));
    // for (const i in input) {
    //     console.log(`${i}: ${input.slice(0, i)}`)
    // }

}

extractAnNonDecreasingSubsequenceFromAnArray([1, 3, 8, 4, 10, 12, 3, 2, 24]);
extractAnNonDecreasingSubsequenceFromAnArray([1, 2, 3, 4]);
extractAnNonDecreasingSubsequenceFromAnArray([20, 3, 2, 15, 6, 1]);

