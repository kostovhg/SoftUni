function main(matrix) {

    let magic = true;
    let row = 0;
    let sum = matrix[row].reduce((a, b) => a + b);
    /**
     * check the next rows
     */
    while(matrix[row]){
        magic = (sum === matrix[row++].reduce((a, b) => a + b));
        if(!magic) break;
    }

    /**
     * check the columns
     */
    if(magic) {
        let columnsSum = matrix.reduce((r, a) => {
            a.forEach((b, i) => {
                r[i] = (r[i] || 0) + b;
            });
            return r;
        }, []);

        let col = 0;
        while (columnsSum[col]) {
            magic = sum === columnsSum[col++];
        }
    }



    console.log(magic);
}
/*
array.reduce(function (r, a) {
        a.forEach(function (b, i) {
            r[i] = (r[i] || 0) + b;
        });
        return r;
 */

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
    [0, 0, 0, 1]
]);