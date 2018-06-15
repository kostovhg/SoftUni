function tripLength(input) {

    let points = readPoints(input);

    function getDistances(points) {
        let d = [];
        for (let i = 0; i < 3; i++) {
            d.push(getDistance(points[0], points[1]));
            rotatePoints();
        }
        return d;
    }

    let distances = getDistances(points);
    let result = [];
    let fullDistance;

    function readPoints(input) {
        let p = [];
        for (let i = 0; i < input.length; i+=2) {
            p.push({x: input[i], y: input[i + 1], n: i/2 + 1 });
        }
        return p;
    }

    // function getFullDistance(n) {
    //     let dist = 0;
    //     let output = [points[n].n];
    //     let cIndex = n % 3;
    //     let nextIndex = (n + 1) % 3;
    //     let previousIndex = (n - 1) < 0 ? 2 : (n - 1);
    //     //let result = Math.min()
    //     {
    //         dist += getDistance(points[cIndex], points[nextIndex]);
    //         console.log(`distance: ${dist}`);
    //         output.push(points[nextIndex].n);
    //     }
    //     return [dist, output.sort(x => x).join('->')];
    // };

    // for (let i = 0; i < 3; i++) {
    //     let measurement = getFullDistance(i);
    //     let newDistance = measurement[0];
    //     if(!fullDistance || newDistance < fullDistance){
    //         fullDistance = newDistance;
    //         result =[];
    //         result.push(measurement[1]);
    //     }
    //     console.log(`full distance: ${fullDistance}`);
    // }

    for (let i = 0; i < 2; i++) {


        let p1 = points.shift();
        points.push(p1);
        let dist = getDistance(p1, points[0]);

    }

    function rotatePoints() {
        let p = points.shift();
        points.push(p);
    }

    function getDistance(p1, p2){
        return Math.sqrt((p1.x - p2.x)** 2 + (p1.y - p2.y)**2)
    }

    console.log(`${result}: ${fullDistance}`);
    console.log(`------------------`);


}

tripLength([0, 0, 2, 0, 4, 0]);
//1->2->3: 4

tripLength([5, 1, 1, 1, 5, 4]);
//2->1->3: 7

tripLength([-1, -2, 3.5, 0, 0, 2]);
//1->3->2: 8.154234499766936

