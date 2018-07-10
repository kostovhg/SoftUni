function distanceOverTime(inputArray) {
    inputArray[2] /= 3600; // convert seconds to hour

    console.log(Math.abs(inputArray[2] * (inputArray[0] - inputArray[1])) * 1000) // at the end convert km to meters
}

distanceOverTime([0, 60, 3600]);
distanceOverTime([11, 10, 120]);
distanceOverTime([5, -5, 40]);