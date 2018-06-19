function commandProcessor(arr) {

    let commandProcessor = (function () {
        let result = '';
        return {
            append: (item) => result += item,
            removeStart: (num) => result = result.slice(num),
            removeEnd: (num) => result = result.slice(0, result.length - num),
            print: () => console.log(result)
        }
    })();

    for (let x of arr) {
        let [command, item] = x.split(' ');
        commandProcessor[command](item);
    }

}

commandProcessor([
    'append hello',
    'append again',
    'removeStart 3',
    'removeEnd 4',
    'print'
]);

commandProcessor(['append 123', 'append 45',
    'removeStart 2', 'removeEnd 1', 'print']);