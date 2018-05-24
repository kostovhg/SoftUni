function printEveryNthElementFromAnArray(input) {
    let step = input.pop();
    input.filter((x, i) => i % step === 0).forEach(x => console.log(x));
}

printEveryNthElementFromAnArray([5, 20, 31, 4, 20, 2]);
printEveryNthElementFromAnArray(['dsa', 'asd', 'test', 'tset', 2]);
printEveryNthElementFromAnArray([1, 2, 3, 4, 5, 6]);