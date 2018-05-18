function printStars(count = 5){

    let rows = (2 * count) - 1;

    for (let r = 1; r <= rows && r > 0; r++) {
        // reversed
        //console.log(`*`.repeat(Math.max(count - r, r - count)));
        // correct
        console.log(`*`.repeat(Math.min(r, 2 * count - r)));
    }

}

printStars();
printStars(2);
printStars(0);
printStars(1);