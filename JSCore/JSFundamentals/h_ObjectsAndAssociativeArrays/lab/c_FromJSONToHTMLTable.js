function fromJSONToHTMLTable(input) {

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
                "<td>" +
                (!isNaN(parseFloat(v)) && isFinite(v) ? v : htmlEscape(v)) + "</td>").join('') + '</tr>')
    });
    result.push('</table>');
    console.log(result.join('\n'));
}

fromJSONToHTMLTable('[{"Name":"Tomatoes & Chips","Price":2.35},{"Name":"J&B Chocolate","Price":0.96}]');
fromJSONToHTMLTable('[{"Name":"Pesho <div>-a","Age":20,"City":"Sofia"},' +
'{"Name":"Gosho","Age":18,"City":"Plovdiv"},{"Name":"Angel","Age":18,"City":"Veliko Tarnovo"}]');