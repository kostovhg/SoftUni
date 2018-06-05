function ancientVSMemory(input) {

    let arr = input.join(' ')
        .replace(/ 0+/g, '')
        .split(/32656 19759 32763 /g)
        .map(x => x.trim())
        .filter(x => x !== '' && x !== '0')
        .map(x => x.split(/ /).slice(1).map(Number))
        .map(e => e.map(c => String.fromCharCode(c)).join(''))
        .forEach(n => console.log(n));

}

ancientVSMemory([
    '32656 19759 32763 0 5 0 80 101 115 104 111 0 0 0 0 0 0 0 0 0 0 0',
    '0 32656 19759 32763 0 7 0 83 111 102 116 117 110 105 0 0 0 0 0 0 0 0'
]);

ancientVSMemory([
    '0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 32656 19759 32763 0',
    '5 0 71 111 115 104 111 0 0 0 0 0 0 0 0 0 32656 19759 32763 0 4 0',
    '75 105 114 111 0 0 0 0 0 0 0 0 0 0 32656 19759 32763 0 8 0 86 101',
    '114 111 110 105 107 97 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0']);

