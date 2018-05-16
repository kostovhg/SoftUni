function binaryLogarithm(arr) {
    numbers = arr.map(Number);
    for (let number of numbers) {
        if(number !== 0)
            console.log(Math.log2(number));
    }

}

binaryLogarithm(['1024', '1048576', '256', '1', '2', '50', '100', 0]);