function expedition(primary, secondary, overlay, starting) {

    //let [steps, previous, current, ways, width, height] = [
    //    1, [-1, -1], starting, [[-1, 0], [0, 1], [1, 0], [0, -1]], primary.length, primary[0].length];

    let [width, height, steps, ways, c, p] = [
        primary[0].length, primary.length, 1,
        [{dir: 'up', row: -1, col: 0}, {dir: 'down', row: 1, col: 0}, {dir: 'left', row: 0, col: 1}, {dir: 'right', row: 0, col: -1}],
        {row: starting[0], col: starting[1]},
        {row: null, col: null},
    ];

    overlay.forEach(([row, col]) => {
        secondary.forEach((secRow, sri) =>
            secRow.forEach((secCol, sci) => {
                let rowOffset = row + sri;
                let colOffset = col + sci;
                if (rowOffset < height && colOffset < width) {
                    primary[rowOffset][colOffset] ^= secondary[sri][sci]
                }
            }))
    });

    // console.log(primary);
    // console.log(`height ${height} width ${width}`);

    function move() {
        for (let way of ways) {
            let next = {row: c.row + way.row, col: c.col + way.col};
            if (inBoundaries(next) &&
                !(p.row === next.row && p.col === next.col) &&
                primary[next.row][next.col] === 0) {
                //console.log(`from ` + c.row +','+ c.col + ` to ` + next.row + ', ' + next.col);
                steps++;
                p.row = c.row; p.col = c.col;
                c.row = next.row; c.col = next.col;
                return true;
            }
        }
        return false;
    }

    function inBoundaries(p) {
        return p.row >= 0 && p.row < width && p.col >= 0 && p.col < height;
    }

    while (move()) {
        // just rolling
    }

    console.log(steps);
    if (c.row === 0) console.log(`Top`);
    else if (c.row === height - 1) console.log(`Bottom`);
    else if (c.col === 0) console.log(`Left`);
    else if (c.col === width - 1) console.log(`Right`);
    else {
        //console.log(`row is ${c.row} and half height is: ${height/2}, col is ${c.col} and half width is ${width/2}`);
        let quadrant = (c.row < height / 2) ? // if true 1 or 2 if false 3 or 4
            (c.col < width / 2 ? 2 : 1) :
            (c.col < width / 2 ? 3 : 4);
        console.log(`Dead end ` + quadrant)
    }
}

expedition([
    [1, 1, 0, 1, 1, 1, 1, 0],
    [0, 1, 1, 1, 0, 0, 0, 1],
    [1, 0, 0, 1, 0, 0, 0, 1],
    [0, 0, 0, 1, 1, 0, 0, 1],
    [1, 0, 0, 1, 1, 1, 1, 1],
    [1, 0, 1, 1, 0, 1, 0, 0]], [
    [0, 1, 1],
    [0, 1, 0],
    [1, 1, 0]], [
    [1, 1],
    [2, 3],
    [5, 3]], [0, 2]);

expedition([
    [1, 1, 0, 1],
    [0, 1, 1, 0],
    [0, 0, 1, 0],
    [1, 0, 1, 0]
], [
    [0, 0, 1, 0, 1],
    [1, 0, 0, 1, 1],
    [1, 0, 1, 1, 1],
    [1, 0, 1, 0, 1]
], [
    [0, 0],
    [2, 1],
    [1, 0]
], [2, 0]);

expedition([
        [1, 0, 0, 0, 1, 0, 0, 0],
        [1, 1, 0, 0, 0, 1, 1, 0],
        [1, 1, 0, 1, 0, 0, 0, 0],
        [1, 1, 0, 0, 1, 0, 1, 1]], [
        [1, 1, 1, 0, 1, 1, 1, 1, 1, 1],
        [0, 0, 0, 0, 1, 0, 1, 0, 0, 0],
        [1, 0, 1, 1, 1, 0, 0, 0, 1, 0],
        [0, 1, 1, 0, 0, 0, 0, 1, 1, 1],
        [1, 1, 0, 1, 1, 1, 1, 1, 0, 0],
        [0, 0, 1, 0, 0, 0, 1, 0, 0, 0],
        [1, 0, 1, 1, 0, 1, 1, 1, 0, 1],
        [0, 1, 0, 1, 1, 1, 0, 1, 1, 0],
        [1, 1, 1, 0, 0, 1, 1, 1, 1, 1],
        [1, 1, 1, 1, 1, 1, 0, 0, 1, 1]], [
        [2, 3],
        [1, 5],
        [0, 1]],
    [1, 7]);