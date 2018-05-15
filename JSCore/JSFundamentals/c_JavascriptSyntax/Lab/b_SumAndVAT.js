function sumAndVAT(input) {
    let sum = 0;
    input.forEach(e => sum += e);
    console.log(`sum = ${sum}`);
    console.log(`vat = ${sum * 0.20}`);
    console.log(`sum = ${sum * 1.20}`);
}


sumAndVAT([1.20, 2.60, 3.50]);
sumAndVAT([3.12, 5, 18, 19.24, 1953.2262, 0.001564, 1.1445]);
