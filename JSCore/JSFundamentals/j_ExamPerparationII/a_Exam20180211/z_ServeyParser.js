function surveyParser(input) {

    let [svgRegex, catsRegex, dataRegex, digitsRegex, digits] = [
        /<svg>(.*?)<\/svg>/,
        /<cat>.*?<text>.*\[(.*?)\].*?<\/text>.*?<\/cat>\s+<cat>.*?(<g>.*<\/g>).*?<\/cat>/g,
        /<g>.*?<\/g>+/g,
        /(10|[1-9]).*?<\/val>(\d+)/,
        /(?:<g><val>)(10|[0-9])<\/val>(\d+)(?=<\/g>)/gm
    ];

    let [count, sum, result] = [0, 0, []];

    function getBetween(tag, text) {
        let [start, end, res] = [
                text.indexOf(`<${tag}>`) + tag.length + 2,
                text.indexOf(`</${tag}>`),
                []
            ]
        ;
        while (start > -1 && end > start) {
            res.push(text.substr(start, (end) - start));
            text = text.substr(end + tag.length + 3);
            start = text.indexOf(`<${tag}>`) + tag.length + 2;
            end = text.indexOf(`</${tag}>`);

        }
        return res;
    }

    let svg = getBetween(`svg`, input);
    if (svg.length > 0) {
        //console.log(`svg: ` + svg);
        let cat = getBetween(`cat`, svg[0]);
        // console.log(`cat's : \n` + cat.join('\n'));
        let cat1 = cat[0];
        let cat2 = cat[1];
        let label = cat1.substr(cat1.indexOf(`[`) + 1, cat1.indexOf(']') - cat1.indexOf('[') - 1);
        if (cat1 && cat2 && label) {
            let data = getBetween(`g`, cat2);
            for (const values of data) {
                let m = lastRegex.exec(values);
                let [votes, value] = [Number(m[2]), Number(m[1])];
                //if(votes < 0 || votes > )
                sum += votes * value;
                count += votes;
                //console.log(`${votes} votes with value ${value} -> sum ${sum} -> counts ${count}`)
            }

            if(count > 0) {
                console.log(`${label}: ${parseFloat((sum / count).toFixed(2)).toString()}`);
            }
            else {
                console.log('Invalid format')
            }
        } else {
            console.log('Invalid format')
        }
    } else {
        console.log(`No survey found`);
    }

}


surveyParser(`<p>Some random text</p><svg><cat><text>How do you rate our food? ` +
    `[Food - General]</text></cat><cat><g><val>1</val>0</g><g><val>2</val>1</g><g>` +
    `<val>3</val>3</g><g><val>4</val>10</g><g><val>5</val>7</g></cat></svg><p>Some more random text</p>`);
surveyParser(`<svg><cat><text>How do you rate the special menu? ` +
    `[Food - Special]</text></cat> <cat><g><val>1</val>5</g><g>` +
    `<val>5</val>13</g><g><val>10</val>22</g></cat></svg>`);
surveyParser(`<p>How do you suggest we improve our service?` +
    `</p><p>More tacos.</p><p>It's great, don't mess with it!</p>` +
    `<p>I'd like to have the option for delivery</p>`);
surveyParser(`<svg><cat><text>Which is your favourite meal from our selection?` +
    `</text></cat><cat><g><val>Fish</val>15</g><g><val>Prawns</val>31</g><g>` +
    `<val>Crab Langoon</val>12</g><g><val>Calamari</val>17</g></cat></svg>`);
