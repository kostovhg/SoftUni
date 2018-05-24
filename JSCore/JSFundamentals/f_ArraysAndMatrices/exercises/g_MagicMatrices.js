function main(matrix) {
    /**
     * A function that returns sum of all elements of array
     * @param x - one dimensional array
     * @returns {*} - sum of elements
     */
    let arrSum = x => x.reduce((a, b) => a + b);
    /**
     * A function that sums the columns of two arrays;
     * @param arr1 - Array to which members will be added those of
     * @param arr2 - Array which elements will be added to arr1
     * @returns {*} - returns array;
     */
    let colSum = (arr1, arr2) => arr1.map((val, index) => val + arr2[index]);

    let sum = arrSum(matrix[0]);
    let magic = true;

    // check the rows
    matrix.forEach(row => magic = magic && (sum === arrSum(row)));

    // check columns
    if (magic) {
        magic = matrix.reduce((a, b) => colSum(a, b)).every(x => x === sum);
    }
    console.log(magic);
}

main([
    [4, 5, 6],
    [6, 5, 4],
    [5, 5, 5]
]);

main([
    [11, 32, 45],
    [21, 0, 1],
    [21, 1, 1]
]);

main([
    [1, 0, 0],
    [0, 0, 1],
    [0, 1, 0]
]);

main([
    [1, 0, 0],
    [0, 0, 1],
    [0, 1, 0, 1]
]);