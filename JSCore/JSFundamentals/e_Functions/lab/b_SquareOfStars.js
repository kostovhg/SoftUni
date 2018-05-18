function squareOfStars(count = 5) {

    for (let i = 0; i < count; i++) {
        repeatStars(count);
    }

    function repeatStars(n) {
        console.log(`* `.repeat(n).trim());
    }
}

squareOfStars();
squareOfStars(1);
squareOfStars(2);
squareOfStars(3);
squareOfStars(5);
