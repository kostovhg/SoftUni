function euclidsAlgorithm(a, b){
    return (!b) ?  a : euclidsAlgorithm(b, a % b);
}

console.log(euclidsAlgorithm(252, 105));