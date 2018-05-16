function colorfulNumbers(n) {
    let result = `<ul>\n`;
    for (let i = 1; i <= n; i++) {
        // language=HTML
        //result += ` <li><span style='color:` + (i % 2 === 0 ? `blue` : `green` ) + `'>` + i + `</span></li>\n`;
        result += ` <li><span style='color:${i % 2 === 0 ? "blue" : "green"}'>` + i + `</span>\n`;
    }

    result += `</ul>`;
    console.log(result);
}

colorfulNumbers(10);