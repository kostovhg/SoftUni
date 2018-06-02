function countWordsInAText(input) {

    let map = new Map();
    input
        .join(' ')
        .toLowerCase()
        .split(/[\W]+/)
        .filter(a => a !== '')
        .forEach(s => {
            !map.has(s) ?
                map.set(s, 1) :
                map.set(s, Number(map.get(s)) + 1);
        });

    for (let key of Array.from(map.keys()).sort()) {
        console.log(`'${key}' -> ${map.get(key)} times`)
    }
}

countWordsInAText([`Far too slow, you're far too slow.`]);
countWordsInAText([`JS devs use Node.js for server-side JS.-- JS for devs`]);