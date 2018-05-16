function chessBord(n) {
    n = Math.floor(n);
    console.log(`<div class="chessboard">`);

    for (let i = 0; i < n; i++) {
        console.log(` <div>`);
        for (let j = 0; j < n; j++) {
            console.log(`  <span class="${((i + j) % 2 ? 'white' : 'black')}"></span>`);
        }
        console.log(` </div>`);
    }
    console.log(`</div>`)
}

chessBord(3);
chessBord(7);
chessBord(4.5);