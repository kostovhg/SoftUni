function chessBord(n) {
    n = Math.floor(n);
    console.log(`<div class="chessboard">`);
    let black = true;
    for (let i = 0; i < n; i++) {
        console.log(` <div>`);
        for (let j = 0; j < n; j++) {
            console.log(`  <span class="${(black ? 'black' : 'white')}"></span>`);
            black = !black;
        }
        i % 2 === 0 ? black = false : black = true;
        console.log(` </div>`);
    }
    console.log(`</div>`)
}

chessBord(3);
chessBord(7);
chessBord(4.5);