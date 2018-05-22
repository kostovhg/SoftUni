function cookingByNumbers(input) {

    let initial = Number(input.shift());
    /*
    let [number, op1, op2, op3, op4, op5] = [Number(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]];
    */

    let execute = (x, fn) => fn(x);

    let chop = (x) => x /= 2; //– divide the number by two
    let dice = (x) => x = x**(1/2); //– square root of number
    let spice = (x) => x += 1; //– add 1 to number
    let bake = (x) => x *= 3; //– multiply number by 3
    let fillet = (x) => x *= 0.8; //– subtract 20% from number

    let x = initial;
    for (const op of input) {
        switch (op){
            case 'chop': x = execute(x, chop); break;
            case 'dice': x = execute(x, dice); break;
            case 'spice': x = execute(x, spice); break;
            case 'bake': x = execute(x, bake); break;
            case 'fillet': x = execute(x, fillet); break;
        }
        console.log(x);
    }

}

//cookingByNumbers([32, 'chop', 'chop', 'chop', 'chop', 'chop']);
cookingByNumbers([9, 'dice', 'spice', 'chop', 'bake', 'fillet']);