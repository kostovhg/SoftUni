function aggregateTable(input) {

    let towns = [];
    let sum = 0;

    input.forEach(x =>
        x.split('|')
            .filter(e => e !== '')
            .map(e => e.trim())
            .forEach((e, i) =>
                i % 2 ?
                    sum += Number(e) :
                    towns.push(e)));
    console.log(towns.join(', '));
    console.log(sum);
}

aggregateTable([
    '| Sofia           | 300',
    '| Veliko Tarnovo  | 500',
    '| Yambol          | 275']);