function scoreToHTML(input) {

    let objects = JSON.parse(input);

    function htmlEscape(text) {
        let map = {'"': `&quot;`, '&': `&amp;`, "'": `&#39;`, '<': `&lt;`, '>': `&gt;`};
        return text.replace(/["&'<>]/g, ch => map[ch]);
    }

    let headers = Object.keys(objects[0]);

    let result = [];
    result.push(`<table>`);
    result.push(`  <tr>${(headers.map(h => "<th>" + htmlEscape(h) + "</th>").join(''))}</tr>`);
    objects.forEach(o => {
        result.push('  <tr>' +
            Object.values(o).map(v =>
                `<td>${(!isNaN(parseFloat(v)) && isFinite(v)) ?
                    v :
                    htmlEscape(v)
                    }</td>`)
                .join('') +
            '</tr>'
        );
    });
    result.push('</table>');

    console.log(result.join('\n'))
}

scoreToHTML('[{"name":"Pesho","score":479},{"name":"Gosho","score":205}]');

scoreToHTML('[{"name":"Pesho & Kiro","score":479},{"name":"Gosho, Maria & Viki","score":205}]');
