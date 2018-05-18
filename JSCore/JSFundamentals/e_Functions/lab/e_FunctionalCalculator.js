function functionalCalculator(a, b, op = '+') {
    function calculate(a, b, op) {

        let calc = function (a, b, op) {
            return op(a, b);
        };
        let add = function (a, b) {
            return a + b;
        };
        let subtract = function (a, b) {
            return a - b;
        };
        let multiply = function (a, b) {
            return a * b;
        };
        let divide = function (a, b) {
            return a / b;
        };
        let division = function (a, b) {
            return a % b;
        };
        let pow = function (a, b) {
            return a ** b;
        };
        let root = function (a, b) {
            return a ** (1 / b);
        };

        switch (op) {
            case '+':
                return calc(a, b, add);
            case '-':
                return calc(a, b, subtract);
            case '*':
                return calc(a, b, multiply);
            case '/':
            case '\\': // not needed for judge
                return calc(a, b, divide);
            case '%':
                return calc(a, b, division);
            case '^':
            case '**':
                return calc(a, b, pow);
            case 'v':
                return calc(a, b, root);
        }
    }

    console.log(calculate(a, b, op));
}

functionalCalculator(2, 4, '+');
functionalCalculator(3, 3, '/');
functionalCalculator(18, -1, '*');
functionalCalculator(4, 2, 'v');
functionalCalculator(8, 3, 'v');
functionalCalculator(5, 2, '%');
functionalCalculator(6, 2, '\\');

