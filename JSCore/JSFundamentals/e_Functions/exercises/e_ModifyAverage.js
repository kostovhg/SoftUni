function modifyAverage(input){

    let splitNumbers = (x) => x.toString(10).split('').map(t => parseInt(t));
    let getAverage = (arr) => arr.reduce((a, b) => a + b, 0) / arr.length;


    let arr = splitNumbers(input);

    while(getAverage(arr) <= 5){
        arr.push(9);
    }

    console.log(arr.join(''));
}

modifyAverage(101);
modifyAverage(5835);