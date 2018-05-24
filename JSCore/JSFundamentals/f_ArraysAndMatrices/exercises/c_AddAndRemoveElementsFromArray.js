function addAndRemoveElementsFromArray(input) {

    let arr = [];
/*    for (let i = 0; i < input.length; i++) {
        if(input[i] === 'add'){
            arr.push(i+1);
        } else {
            arr.pop();
        }
    }*/
    input.forEach((x, i) => x === 'add' ? arr.push(i + 1) : arr.pop());
    console.log((arr.length > 0) ? arr.join('\n'): 'Empty');
}

addAndRemoveElementsFromArray([
    'add',
    'add',
    'add',
    'add'
]);
addAndRemoveElementsFromArray([
    'add',
    'add',
    'remove',
    'add',
    'add'
]);
addAndRemoveElementsFromArray([
    'remove',
    'remove',
    'remove'
]);