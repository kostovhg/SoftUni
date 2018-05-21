function validityChecker(input) {

    let points = readPoints();

    function readPoints() {
        let p = [{x: 0, y: 0, n: 0}];
        for (let i = 0; i < input.length; i+=2) {
            p.push({x: input[i], y: input[i + 1], n: i/2 + 1 });
        }
        return p;
    }

    for (let i = 1; i < points.length; i++) {
        checkDistance(points[i], points[0]);
    }

    function checkDistance(p1, p2) {
        let dist = Math.sqrt((p1.x - p2.x)** 2 + (p1.y - p2.y)**2);
        if (Number.isInteger(dist)){
            console.log(`{${p1.x}, ${p1.y}} to {${p2.x}, ${p2.y}} is valid`);
        } else {
            console.log(`{${p1.x}, ${p1.y}} to {${p2.x}, ${p2.y}} is invalid`);
        }
    }

    checkDistance(points[1], points[2]);
//    console.log(points[0])
}

validityChecker([3, 0, 0, 4]);
validityChecker([2, 1, 1, 1]);