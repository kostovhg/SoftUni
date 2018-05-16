function distanceIn3D(pointsArray) {
    let p1 = {x: pointsArray[0], y: pointsArray[1], z: pointsArray[2]};
    let p2 = {x: pointsArray[3], y: pointsArray[4], z: pointsArray[5]};

    let dist = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    console.log(Math.sqrt(Math.pow(p1.z - p2.z, 2) + dist ** 2));
}

distanceIn3D([1, 1, 0, 5, 4, 0]);
distanceIn3D([3.5, 0, 1, 0, 2, -1]);