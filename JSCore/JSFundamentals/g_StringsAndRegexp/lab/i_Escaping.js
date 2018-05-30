function escaping(input) {

   /* input = input.map(x => `  <li>` + htmlEscape(x) + `</li>`);

    function htmlEscape(str) {
        return str.replace(/&/g, `&amp;`)
            .replace(/</g, `&lt;`)
            .replace(/>/g, `&gt;`)
            .replace(/"/g, `&quot;`)
            .replace(/'/g, `&apos;`)
    };

    console.log((`<ul>\n` + input.join('\n') + `\n</ul>`));*/

   function htmlList(items) {
       return `<ul>\n` +
           items.map(htmlEscape).map(
               item => `  <li>${item}</li>`).join(`\n`) +
           `\n</ul>\n`;
   }

   function htmlEscape(text) {
       let map = {'"': `&quot;`, '&': `&amp;`, "'": `&#39;`, '<': `&lt;`, '>': `&gt;`};
       return  text.replace(/["&'<>]/g, ch => map[ch]);
   }

    console.log(htmlList(input));
}

escaping(['<b>unescaped text</b>', 'normal text']);
escaping(['<div style=\"color: red;\">Hello, Red!</div>', '<table><tr><td>Cell 1</td><td>Cell 2</td><tr>']);