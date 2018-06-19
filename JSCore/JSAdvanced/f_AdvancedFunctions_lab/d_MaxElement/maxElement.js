function maxElements(arr) {
    return Math.max(...arr);
    // return Math.max.apply('no matter', arr);
}

console.log(maxElements([2, 3, -5, 18333, 4]));