// judge 100 -> comparing strings
function uniqueSequences_(input) {

    let aMap = new Map();

    input
        .map(row => JSON.parse(row).map(Number).sort((a, b) => b - a))
        .map(arr => [`[${arr.join(`, `)}]`, arr.length])
        .sort((a1, a2) => a1[1] - a2[1])
        .forEach(e => !aMap.has(e[0]) ? aMap.set(e[0], e[1]) : null);

    [...aMap.keys()].forEach(e => console.log(e))
}

// judge 100 -> comparing strings
function uniqueSequences(input) {

    input
        .map(row => (JSON.parse(row).map(Number)).sort((a, b) => b - a))
        .sort((arr1, arr2) => arr1.length - arr2.length)
        .map(r => `[${r.join(', ')}]`)
        .filter((e, i, a) => a.indexOf(e) === i)
        .forEach(r => console.log(r));
}

// judge 83 - probably not all are inserted as unique!? (comparison is not between strings)
function uniqueSequencesComp(input) {

    let arrays = [];

    //.forEach(e => console.log(`[${(e.sort((n1, n2) => +n2 - +n1)).join(', ')}]`));
    input
        .map(row => (JSON.parse(row).map(e => Number(e))) // convert each element to array
            .sort((a, b) => b - a)) // backward sorting each array
        .sort((arr1, arr2) => arr1.length - arr2.length) // sort the arrays by length
        .filter((e, i, ar) => !same(e, ar[i-1] || [])) // clear arrays that are the same (e - current array,
        .filter((a, i, arr) => !dif(a, arr[i - 1] || [])) // clear arrays that are the same (a - current array,
        //.forEach(a => arrays.push(a));
        .forEach(r => console.log(`[${r.join(', ')}]`));

    function same(arr1, arr2) {
        if(arr1.length !== arr2.length) return false;
        for (let i = 0; i < arr1.length; i++) {
            if(arr1[i] !== arr2[i]){
                return false;
            }
        }
        return true;
    }

    function dif(a1, a2) {
        return a1.map((e, i) => e === a2[i])
            .reduce((a, b) =>  a && b)
    }

    //console.log(arrays);
}

uniqueSequences([
    '[-3, -2, -1, 0, 1, 2, 3, 4]',
    '[10, 1, -17, 0, 2, 13]',
    '[4, -3, 3, -2, 2, -1, 1, 0]'
]);


uniqueSequences([
    '[7.14, 7.180, 7.339, 80.099]',
    '[7.339, 80.0990, 7.140000, 7.18]',
    '[7.339, 7.180, 7.14, 80.099]',
]);


uniqueSequences([
    '[7.14, 7.180, 7.18, 7.339, 80.099, 0]',
    '[7.339, 80.0990, 7.14, 7.180, 7.18]',
    '[7.339, 7.180, 7.14, 80.099]',
]);
