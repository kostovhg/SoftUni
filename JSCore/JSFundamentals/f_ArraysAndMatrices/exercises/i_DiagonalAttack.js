function diagonalAttack(input) {

    input.map((row, i) => input[i] = row.split(' ').map(Number));

    let diagSum = 0;
    input.map((row, arr) => diagSum += Number(row.filter((el, i) => arr === i )));
    let backDiagSum = 0;
    input.map((row, arr) => backDiagSum += Number(row.filter((el, i) => arr === row.length - 1 - i )));

    if(diagSum === backDiagSum){
        input.forEach((row, ri) =>
            row.forEach((el, i) => input[ri][i] =
                (i === ri || ri === row.length - 1 - i) ? el : diagSum))
    }

    function printMatrix() {
        console.log(input.map(e => e.join(' ')).join('\n'));
    }

    printMatrix();
}

diagonalAttack([
    '5 3 12 3 1',
    '11 4 23 2 5',
    '101 12 3 21 10',
    '1 4 5 2 2',
    '5 22 33 11 1'
]);
diagonalAttack([
    '1 1 1',
    '1 1 1',
    '1 1 0'
]);

