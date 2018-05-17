function figureOf4Squares(n) {
    let rows = !(n % 2) ? n - 1 : n;
    let result = '';
    let border = `-`.repeat(n - 2);
    let space = ` `.repeat(n - 2);

    function borderRow(i) {
        return i === 1 || i === rows || i === (rows + 1) / 2;
    }

    for (let row = 1; row <= rows; row++) {
        if(borderRow(row, rows)) {
            result += (`+` + border).repeat(2) + `+\n`;
        } else {
            result += (`|` + space).repeat(2) + `|\n`;
        }
    }

    console.log(result)
}

figureOf4Squares(4);
figureOf4Squares(5);
figureOf4Squares(6);
figureOf4Squares(7);
