function lastMonth(dateArray) {
    let date = new Date(dateArray[2], dateArray[1] - 1, 0);
    console.log((date.getDate()));
}

lastMonth([17, 3, 2002]);
lastMonth([13, 12, 2004]);