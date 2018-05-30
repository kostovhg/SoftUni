function extractText(text) {

    let result = [];
    let start = text.indexOf('(');
    let end = text.indexOf(')', start);

    while (start > -1 && end > -1) {
        result.push(text.substr(++start, end - start));
        start = text.indexOf('(', end);
        end = text.indexOf(')', start);
    }
    console.log(result.join(', '));
}

extractText('Rakiya (Bulgarian brandy) is self-made liquor (alcoholic drink)');
extractText('No )matches) in (this text, (despite abundance ( of');