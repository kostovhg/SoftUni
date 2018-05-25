function spiralMatrix(n, m) {

    let matrix = [];
    let w = m - 1; // sub matrix width
    let h = n - 1; // sub matrix height
    let cRow = 0; // current row
    let cCol = -1; // current column
    let cVal = 1; // current value

    // fill the matrix skeleton
    for (let i = 0; i < n; i++) {
        matrix[i] = [];
    }

    while(w > 0 && h > 0) {
        while (cCol < w) fill(cRow, ++cCol); // left to right
        while (cRow < h) fill(++cRow, cCol); // up to down
        while (cCol > m - 1 - w) fill(cRow, --cCol); // right to left
        while (cRow - 1 > n - 1 - h) fill(--cRow, cCol); // down to up with 1 step less
        w--; // lower the width for next iteration
        h--; // lower the height for nex iteration
        if(cVal > n * m) break; // stop useless repetition
        //printMatrix(); //debug
    }

    function fill(r, c) {
        if(!matrix[r][c]) matrix[r][c] = cVal++;
    }

    function printMatrix() {
        console.log(matrix.map(e => e.join(' ')).join('\n'));
        //console.log('---') // debut
    }

    printMatrix();
    //console.log('=================') // debug
}

spiralMatrix(5, 5);

spiralMatrix(3, 3);

spiralMatrix(4, 7);

spiralMatrix(5, 3);