function equalNeighbors(matrix) {

    let neighbors = 0;
    matrix
        .forEach((row, r) =>
            row.forEach((element, c) => {
                if (r < matrix.length - 1 && element === matrix[r + 1][c])
                    neighbors++;
                if (c < row.length - 1 && element === matrix[r][c + 1])
                    neighbors++;
            }));
    console.log(neighbors);

}

equalNeighbors([
    ['2', '3', '4', '7', '0'],
    ['4', '0', '5', '3', '4'],
    ['2', '3', '5', '4', '2'],
    ['9', '8', '7', '5', '4']
]);

equalNeighbors([
    ['test', 'yes', 'yo', 'ho'],
    ['well', 'done', 'yo', '6'],
    ['not', 'done', 'yet', '5']
]);

equalNeighbors([
    [2, 2, 5, 7, 4],
    [4, 0, 5, 3, 4],
    [2, 5, 5, 4, 2]
]);