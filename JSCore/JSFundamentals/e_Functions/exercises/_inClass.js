function cookingByNumbers(input) {

    let initial = Number(input.shift());
    /* destructing the input
    let [number, op1, op2, op3, op4, op5] = [Number(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]];
    */

    let chop = (x) => x /= 2; //– divide the number by two
    let dice = (x) => x = x**(1/2); //– square root of number
    let spice = (x) => x += 1; //– add 1 to number
    let bake = (x) => x *= 3; //– multiply number by 3
    let fillet = (x) => x *= 0.8; //– subtract 20% from number

    let operator = {
        chop : (x) => x /= 2, //– divide the number by two
        dice : (x) => x = x**(1/2), //– square root of number
        spice : (x) => x += 1, //– add 1 to number
        bake : (x) => x *= 3, //– multiply number by 3
        fillet : (x) => x *= 0.8, //– subtract 20% from number
    };

    let x = initial;
    for (const op of input) {
        // switch (op){
        //     case 'chop': x = chop(x); break;
        //     case 'dice': x = dice(x); break;
        //     case 'spice': x = spice(x); break;
        //     case 'bake': x = bake(x); break;
        //     case 'fillet': x = fillet(x); break;
        // }

        x = operator[op](x);
        console.log(x);
    }

}

//cookingByNumbers([32, 'chop', 'chop', 'chop', 'chop', 'chop']);
cookingByNumbers([9, 'dice', 'spice', 'chop', 'bake', 'fillet']);

function modifyAverage(number){

    let numberAsString = number.toString();

    function getAverage(numberAsString) {
        let sum = 0;
        for (let digit of numberAsString) {
            sum += Number(digit);
        }
        return sum / numberAsString.length;
    }

    let addNine = (num) => num + '9';

    while(getAverage(numberAsString) <= 5){
        numberAsString = addNine(numberAsString);
    }

    console.log(numberAsString);
}

modifyAverage(101);


function helix(number){

    let rows = Number(number);

    let bases = 'ATCGTTAGGG';

    let currentIndex = 0;

    for (let i = 0; i < rows; i++) {
        let currentRow = i % 4;
        if(currentIndex === bases.length) currentIndex = 0;
        if(currentRow === 0) {
            console.log(
                `**${bases[currentIndex++]}${bases[currentIndex++]}**`);
        } else if(currentRow === 1 || currentRow === 3){
            console.log(
                `*${bases[currentIndex++]}--${bases[currentIndex++]}*`);
        } else {
            console.log(
                `${bases[currentIndex++]}----${bases[currentIndex++]}`);
        }
    }

}

helix(10);