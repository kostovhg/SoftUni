function sumFirstLast(arr) {
    arr = arr.map(x => Number(x));
    console.log(arr[0] + arr[arr.length - 1]);
}

sumFirstLast(['20', '30', '40']);
sumFirstLast(['5', '10']);
