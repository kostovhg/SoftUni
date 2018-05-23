function diagonalSums(matrix) {

    let diag = [0, 0];
    matrix
        .forEach((r, i) =>
            r.forEach((c, j) => {
                diag[0] += (i === j) ? matrix[i][j] : 0;
                diag[1] += (i === matrix.length - j - 1) ? matrix[i][j] : 0
            }));
    console.log(diag.join(' '));

}

diagonalSums([
    [20, 40],
    [10, 60]
]);

diagonalSums([
    [3, 5, 17],
    [-1, 7, 14],
    [1, -8, 89]
]);
