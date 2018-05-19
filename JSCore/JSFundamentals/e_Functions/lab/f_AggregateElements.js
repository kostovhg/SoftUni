function aggregateElements(arr) {
    aggregate(arr, 0, (a, b) => a + b);
    aggregate(arr, 0, (a, b) => a + 1 / b);
    aggregate(arr, '', (a, b) => a + b);

    function aggregate(inputArray, initialValue, func) {
        for (let i = 0; i < inputArray.length; i++) {
            initialValue = func(initialValue, inputArray[i]);
        }
        console.log(initialValue);
    }
}


aggregateElements([10, 20, 30]);