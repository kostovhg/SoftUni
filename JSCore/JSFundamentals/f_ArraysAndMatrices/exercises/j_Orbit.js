function orbit(input) {
    let [width, height, x, y] = input;

    let matrix = [];
    let printMatrix = () => console.log(matrix.map(e => e.join(' ')).join('\n'));

    // create matrix skeleton
    for (let i = 0; i < height; i++) {
        matrix.push(Array(width).fill());
    }

    // The problem description does not clear which is width, height, x and y...
    // row index sounds like y (ri), and row length as width...
    // each row members index sounds like x (i)
    matrix.map((row, ri) =>
        row.map((el, i) => matrix[ri][i] = (Math.max(Math.abs(ri - x), Math.abs(i - y))) + 1));

    printMatrix();
}

orbit([4, 4, 0, 0]);
orbit([5, 5, 2, 2]);
orbit([3, 3, 2, 2]);
orbit([10, 4, 2, 5]);