function matchMultiplication(input) {

    console.log(
        input
            .replace(
                /(\-?[0-9]+)\s*\*\s*(\-?(?:\d+\.)?\d+)/g,
                (m, p1, p2) => +p1 * +p2));
}

matchMultiplication('My bill: 2*2.50 (beer); 2* 1.20 (kepab); -2  * 0.5 (deposit).');
matchMultiplication('My bill is: 4 * 2.50 (beer); 12* 1.50 (kepab); 1  *4.50 (salad); 2  * -0.5 (deposit).');