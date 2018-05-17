function quadraticEquation(a, b, c) {

    let d = b ** 2 - 4 * a * c;
    if (d < 0){
        console.log("No")
    } else if (d === 0) {
        console.log(-b / (2 * a));
    } else {
        console.log((-b - Math.sqrt(d)) / (2 * a));
        console.log((-b + Math.sqrt(d)) / (2 * a));
    }
}

quadraticEquation(6, 11, -35);
quadraticEquation(1, -12, 36);
quadraticEquation(5, 2, 1);
