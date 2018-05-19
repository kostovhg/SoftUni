function insideVolume(input) {
    let rectangularParallelepiped = {
        x1: 10, x2: 50,
        y1: 20, y2: 80,
        z1: 15, z2: 50,
        getCoordinates: function () {
            return [
                [Math.min(this.x1, this.x2), Math.max(this.x1, this.x2)],
                [Math.min(this.y1, this.y2), Math.max(this.y1, this.y2)],
                [Math.min(this.z1, this.z2), Math.max(this.z1, this.z2)]
            ]
        },
        pointInside: function (x, y, z) {
            for (let i = 0; i < arguments.length; i++) {
                if (arguments[i] < this.getCoordinates()[i][0] || arguments[i] > this.getCoordinates()[i][1]) {
                    return false;
                }
            }
            return true;
        }
    };

    for (let i = 0; i < input.length; i += 3) {
        if (rectangularParallelepiped.pointInside(input[i], input[i + 1], input[i + 2])) {
            console.log('inside');
        } else {
            console.log('outside');
        }
    }
}

insideVolume(
    [
        13.1, 50, 31.5,
        50, 80, 50,
        -5, 18, 43
    ]);
insideVolume([8, 20, 22]);