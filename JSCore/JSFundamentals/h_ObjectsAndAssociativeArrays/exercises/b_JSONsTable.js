function JSONsTable(input) {

    let objs = input.map(r => JSON.parse(r));

    function isNumber(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    function htmlEscape(text) {
        let map = {'"': `&quot;`, '&': `&amp;`, "'": `&#39;`, '<': `&lt;`, '>': `&gt;`};
        return isNumber(text) ?
            Number(text) :
            text.replace(/["&'<>]/g, ch => map[ch]);
    }

    function getRow(o) {
        return Object.values(o).map((v) => `\t\t<td>${htmlEscape(v)}</td>`).join('\n');
    }

    let result = ['<table>'];
    objs.forEach(o => result.push(`\t<tr>\n${getRow(o)}\n\t<tr>`));
    result.push('</table>');

    console.log(result.join(`\n`));
}

JSONsTable([
    '{"name":"Pesho","position":"Promenliva","salary":100000}',
    '{"name":"Teo","position":"Lecturer","salary":1000}',
    '{"name":"Georgi","position":"Lecturer","salary":1000}'
]);