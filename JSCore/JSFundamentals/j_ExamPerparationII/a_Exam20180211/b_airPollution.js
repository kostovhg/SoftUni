function airPollution(aMap, input) {

    let theMap = aMap.map(r => r.split(/ /).map(Number));

    let commands = {
        'breeze': (x) => theMap[x].forEach((v, i) => theMap[x][i] = Math.max(v - 15, 0)),
        'gale': (x) => theMap.forEach((r, i) => theMap[i][x] = Math.max(r[x] - 20, 0)),
        'smog': (x) => theMap.forEach((r, i) => r.forEach((v, c) => theMap[i][c] = Math.min(v + x, 1000))),
    };

    input.map(c => c.split(/ /))
        .forEach(c => {
            commands[c[0]](Number(c[1]));
        });

    let result = [];
    theMap.forEach((row, r) =>
        row.forEach((col, c) => {
            if (col >= 50) {
                result.push(`[${r}-${c}]`)
            }
        })
    );
    console.log((result.length === 0) ? `No polluted areas` : `Polluted areas: ${result.join(', ')}`);
}

airPollution([
        "5 7 72 14 4",
        "41 35 37 27 33",
        "23 16 27 42 12",
        "2 20 28 39 14",
        "16 34 31 10 24",
    ],
    ["breeze 1", "gale 2", "smog 25"]);
/* Polluted areas: [0-2], [1-0], [2-3], [3-3], [4-1] */


airPollution([
        "5 7 3 28 32",
        "41 12 49 30 33",
        "3 16 20 42 12",
        "2 20 10 39 14",
        "7 34 4 27 24",
    ],
    [
        "smog 11", "gale 3",
        "breeze 1", "smog 2"
    ]
);
/* No polluted areas */

airPollution([
        "5 7 2 14 4",
        "21 14 2 5 3",
        "3 16 7 42 12",
        "2 20 8 39 14",
        "7 34 1 10 24",
    ],
    ["breeze 1", "gale 2", "smog 35"]);
/* Polluted areas: [2-1], [2-3], [3-1], [3-3], [4-1], [4-4] */