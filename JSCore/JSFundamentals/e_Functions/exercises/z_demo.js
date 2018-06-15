function radioCrystals(input) {

    let target = input.shift();
    let chunks = input;
    let nextOp = 0;

    function execute(x, f) {
        let count = 0;
        let func = f[0];
        while (func(x) >= target) {
            x = func(x);
            count++;
        }
        return [x, count];
    }

    // cut the cristal on 4
    let cut = (x) => {
        return x /= 4;
    };

    //removes 20% of the crystal’s thickness
    let lap = (x) => {
        return x *= 0.8;
    };

    // removes 20 microns of thickness
    let grind = (x) => {
        return x -= 20;
    };

    // removes 2 microns of thickness
    let etch = (x) => {
        if(x >= target - 1) {
            return x -= 2;
        }
    };

    // increases the thickness of the crystal by 1 micron;
    // this operation can only be done once!
    let xRay = (x) => {
        if(x >= target - 1 || x < target)
            return Math.floor(++x);
    };

    //let operations = [x => x / 4, x => x * 0.8, x => x - 20, x => x - 2];
    let operations = [[cut, 'Cut'], [lap, 'Lap'], [grind, 'Grind'], [etch, 'Etch'], [xRay, 'X-ray']];

    // removes any imperfections smaller than 1 micron (round down the number);
    // do this after every batch of operations that remove material
    let transAndWash = (x) => {
        if (x >= target - 1) {
            console.log(`Transporting and washing`);
            return Math.floor(x)
        }
        return x;
    };

    function go(c) {
        console.log(`Processing chunk ${c} microns`);
        // let c = cc;
        while (nextOp < operations.length) {
            let operation = operations[nextOp];
            let res = execute(c, operation);
            if (res[1] > 0 ) {
                console.log(`${operation[1]} x${res[1]}`);
                c = (nextOp < operations.length - 1) ? transAndWash(res[0]) : res[0];
            }
            nextOp++;
        }
        console.log(`Finished crystal ${c} microns`)
    }

    for (let i = 0; i < chunks.length; i++) {
        go(chunks[i]);
        nextOp = 0;
    }
}

radioCrystals([1375, 50000]);
radioCrystals([1000, 4000, 8100]);
/*

// Immediately-invoked function expression (IIFE)
let f = (function (n){
    console.log(n+1)
})(5);

f;
f;

// IIFE that returns function (F will contain function for print at the console
f = (function (n) {
    return function () {
        console.log(n+1)
    };
})(5);

// Closure - no access to counter var
// inner function sees all variable from outside function
// but there is no reference to wrapping function from nowhere
f = (function (counter) {
    return function () {
        console.log(++counter);
    };
})(5);

f();
f();
f();

console.log('lambda');
// lambda for closure
f = (counter => () => console.log(++counter))(0);

f();
f();
f();

console.log('new attempt');
let k = f;
k();
k();
*/