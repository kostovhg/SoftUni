function rounding(inputArr) {
    let num = inputArr[0];
    let precision = inputArr[1];
    precision = precision > 15 ? 15 : precision;

    console.log(Number(num.toFixed(precision)));

}

rounding([3.1415926535897932384626433832795, 2]);
rounding([10.5, 3]);