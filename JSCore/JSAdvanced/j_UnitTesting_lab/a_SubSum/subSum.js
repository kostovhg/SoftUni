function subSum(arr, start, end) {
    if(!Array.isArray(arr)){
        return new TypeError('First argument should be array of numbers');
    }
    start = start < 0 ? 0 : start;
    end = end >= arr.length ? arr.length - 1 : end;
    let sum = 0;
    for (let i = start; i <= end; i++) {
        sum += +arr[i];
    }
    return sum;
}

console.log(subSum([10, 20, 30, 40, 50, 60], 3, 300));
console.log(subSum([1.1, 2.2, 3.3, 4,4, 5,5], -3, 1));
console.log(subSum("text", -3, 1));
