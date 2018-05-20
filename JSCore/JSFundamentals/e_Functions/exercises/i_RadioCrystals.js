function radioCrystals(input) {

    let target = input.shift();
    let chunks = input;
    let nextOp = 0;

    // cut the cristal on 4
    let cut = (x) => {
        let count = 0;
        while (x / 4 >= target) {
            x /= 4;
            count++;
        }
        return [x, count];
    };

    //removes 20% of the crystal’s thickness
    let lap = (x) => {
        let count = 0;
        while (x * 0.8 > target) {
            x *= 0.8;
            count++;
        }
        return [x, count];
    };

    // removes 20 microns of thickness
    let grind = (x) => {
        let count = 0;
        while (x - 20 > target) {
            x -= 20;
            count++;
        }
        return [x, count];
    };

    // removes 2 microns of thickness
    let etch = (x) => {
        let count = 0;
        while (x > target) {
            x -= 2;
            count++;
        }
        return [x, count];
    };

    // increases the thickness of the crystal by 1 micron;
    // this operation can only be done once!
    let xRay = (x) => {
        let count = 0;
        if (x >= target - 1 && x < target) {
            Math.floor(x++);
            count++;
        }
        return [x, count];
    };

    //let operations = [x => x / 4, x => x * 0.8, x => x - 20, x => x - 2];
    let operations = [[cut, 'Cut'], [lap, 'Lap'], [grind, 'Grind'], [etch, 'Etch'], [xRay, 'X-ray']];

    // removes any imperfections smaller than 1 micron (round down the number);
    // do this after every batch of operations that remove material
    let transAndWash = (x) => {
        if(x >= target - 1) {
            console.log(`Transporting and washing`);
            return Math.floor(x)
        }
        return x;
    };

    function go(c) {
        console.log(`Processing chunk ${c} microns`);
        // let c = cc;
        while (nextOp < operations.length) {
            let cOp = operations[nextOp];
            let res = cOp[0](c);
            if (res[1] > 0) {
                console.log(`${cOp[1]} x${res[1]}`);
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
