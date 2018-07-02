function sumOfNumbers(arr){
    let sum = 0;
    for (let el of arr) {
        sum += Number(el)
    }
    return sum;
}

// function testSum() {
//     if(sumOfNumbers([1,2]) !== 3) throw new Error('1+2 != 3');
//     if(sumOfNumbers([-2]) !== -2) throw new Error('-2 != -2');
//     if(sumOfNumbers([]) !== 0) throw new Error('[] != 0');
// }
//
// testSum();

module.exports = sumOfNumbers;