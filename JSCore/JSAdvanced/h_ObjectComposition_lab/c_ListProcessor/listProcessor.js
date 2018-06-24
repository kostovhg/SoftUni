function listProcessor(input) {
    let commandExecutor = (function () {
        let result = [];
        function add(what) { result.push(what);}
        function remove(what) { result = result.filter(el => el !== what);}
        function print() { console.log(result.join(','));}
        return {add, remove, print}
    })();

    for (let command of input) {
        let [com, arg] = command.split(' ');
        commandExecutor[com](arg);
    }
}


listProcessor(['add hello', 'add again', 'remove hello', 'add again', 'print']);
listProcessor(['add pesho', 'add gosho', 'add pesho', 'remove pesho', 'print']);
