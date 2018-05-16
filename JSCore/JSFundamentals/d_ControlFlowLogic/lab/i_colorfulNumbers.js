function colorfulNumbers(n) {
    let result = `<ul>\n`;
    for (let i = 1; i <= n; i++) {
        //result += ` <li><span style='color:` + (i % 2 === 0 ? `blue` : `green` ) + `'>` + i + `</span></li>\n`;
        // language=HTML
        result += ` <li><span style='color:${(i % 2) ? "green" : "blue"}'>${i}</span></li>\n`;
    }

    result += `</ul>`;
    console.log(result);
}

colorfulNumbers(10);