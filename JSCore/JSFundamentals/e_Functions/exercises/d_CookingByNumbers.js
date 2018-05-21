function cookingByNumbers(input) {

    let initial = Number(input.shift());

    let execute = (x, fn) => fn(x);

    let chop = (x) => { x = x /2; return x}; //– divide the number by two
    let dice = (x) => { x =x**(1/2); return x};//– square root of number
    let spice = (x) => { x += 1; return x};//– add 1 to number
    let bake = (x) => { x =x * 3; return x}; //– multiply number by 3
    let fillet = (x) => { x *= 0.8; return x}; //– subtract 20% from number

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