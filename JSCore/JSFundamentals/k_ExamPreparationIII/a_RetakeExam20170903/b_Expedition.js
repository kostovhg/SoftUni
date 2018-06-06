function expedition(primary, secondary, overlay, starting) {

    let [steps, previous, current, ways, height, width] = [
        1, [-1, -1], starting, [[-1, 0], [0, 1], [1, 0], [0, -1]], primary.length, primary[0].length];


    overlay.forEach(([primRow, primCol]) => {
        secondary.forEach((secRow, sri) =>
            secRow.forEach((secCol, sci) => {
                let rowOffset = primRow + sri;
                let colOffset = primCol + sci;
                if (rowOffset < height && colOffset < width) {
                    primary[rowOffset][colOffset] ^= secondary[sri][sci]
                }
            }))
    });

    function move() {
        for (let way of ways) {
            let next = [current[0] + way[0], current[1] + way[1]];
            if (inBoundaries(next) &&
                !(previous[0] === next[0] && previous[1] === next[1]) &&
                primary[next[0]][next[1]] === 0) {
                //console.log(`move from ` + current + ` to ` + next);
                steps++;
                previous = current;
                current = [next[0], next[1]];
                return true;
            }
        }
        return false;
    }

    function inBoundaries([r, c]) {
        return r >= 0 && r < height && c >= 0 && c < width;
    }

    while (move()) {
        // just rolling
    }

    console.log(steps);
    if (current[0] === 0) console.log(`Top`);
    else if (current[0] === height - 1) console.log(`Bottom`);
    else if (current[1] === 0) console.log(`Left`);
    else if (current[1] === width - 1) console.log(`Right`);
    else {
        let quadrant = (current[0] < height / 2) ? // if true 1 or 2 if false 3 or 4
            (current[1] < width / 2 ? 2 : 1) :
            (current[1] < width / 2 ? 3 : 4);
        console.log(`Dead end ` + quadrant)
    }
}
/*
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
*/
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



