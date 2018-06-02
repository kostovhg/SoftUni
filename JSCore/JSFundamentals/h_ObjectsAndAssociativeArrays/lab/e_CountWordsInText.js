function countWordsInAText(input) {

    let result = {};
    input
        .join(' ')
        .split(/[\W]+/)
        .filter(a => a !== '')
        .forEach(s => {
            result.hasOwnProperty(s) ?
                result[s]++ :
                result[s] = 1;
        });
    console.log(JSON.stringify(result));
}

countWordsInAText([`Far too slow, you're far too slow.`]);
countWordsInAText([`JS devs use Node.js for server-side JS.-- JS for devs`]);
