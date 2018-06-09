function jansNotation(input) {

    let arrayOfOperands = [];

    for (let el of input) {
        if (typeof (el) === "number") {
            arrayOfOperands.push(el)
        } else {
            if (arrayOfOperands.length > 1) {
                execEl(el, arrayOfOperands.pop(), arrayOfOperands.pop())
            } else {
                console.log(`Error: not enough operands!`);
                return;
            }
        }
    }

    function execEl(e, n1, n2) {

        switch (e) {
            case "+":
                arrayOfOperands.push(n2 + n1);
                break;
            case "-":
                arrayOfOperands.push(n2 - n1);
                break;
            case "/":
                arrayOfOperands.push(n2 / n1);
                break;
            case "*":
                arrayOfOperands.push(n2 * n1);
                break;
        }
    }

    console.log((arrayOfOperands.length > 1 ? 'Error: too many operands!' : arrayOfOperands[0]))
}


jansNotation([3, 4, '+']);
jansNotation([5, 3, 4, '*', '-']);

jansNotation([7, 33, 8, '-']);
jansNotation([15, '/']);

jansNotation([31, 2, '+', 11, '/']);

jansNotation([-1, 1, '+', 101, '*', 18, '+', 3, '/']);