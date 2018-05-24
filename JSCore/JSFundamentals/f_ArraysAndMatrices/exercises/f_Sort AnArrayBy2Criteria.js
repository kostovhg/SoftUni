function sortAnArrayBy2Criteria(input) {

/*    function compare(a, b) {
        if (a.length - b.length === 0) {
            return a.localeCompare(b);
        }
        return a.length - b.length;
    }

    input.sort((a, b) => compare(a, b)).forEach(x => console.log(x));
    */
    input.sort((a, b) =>
        (a.length - b.length) ?
            a.length - b.length :
            (a < b ?
                -1 :
                (a > b ?
                    1 :
                    0)
            ))
        .forEach(x => console.log(x));

}

sortAnArrayBy2Criteria(['alpha', 'beta', 'gamma']);
sortAnArrayBy2Criteria(['Isacc', 'Theodor', 'Jack', 'Harrison', 'George']);
sortAnArrayBy2Criteria(['test', 'Deny', 'omen', 'Default']);