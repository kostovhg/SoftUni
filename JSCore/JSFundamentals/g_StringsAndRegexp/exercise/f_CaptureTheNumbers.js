function captureTheNumbers(input) {

    let numbers = [];
    let reg = /\d+/g, match;
    for (let el of input) {
        while (match = reg.exec(el)){
            numbers.push(match[0]);
        }
    }

    console.log(numbers.join(' '));
}

captureTheNumbers(
    ['The300',
    'What is that?',
    'I think it’s the 3rd movie.',
    'Lets watch it at 22:45']);

captureTheNumbers(
    ['123a456',
    '789b987',
    '654c321',
    '0']);

captureTheNumbers(
    ['Let’s go11!!!11!',
    'Okey!1!']);
