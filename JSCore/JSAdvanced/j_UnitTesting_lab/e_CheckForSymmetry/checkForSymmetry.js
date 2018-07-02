function checkForSymmetry(input){
    if(!Array.isArray(input))
        return false; // Non-arrays are non-symmetric
    let reversed = input.slice(0).reverse(); // Clone + reverse;
    return equal =
        (JSON.stringify(input) === JSON.stringify(reversed));
}


module.exports = checkForSymmetry;