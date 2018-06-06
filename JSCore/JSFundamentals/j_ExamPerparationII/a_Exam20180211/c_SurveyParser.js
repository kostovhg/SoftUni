function surveyParser(input) {

    let [svgRegex, catsRegex, digits] = [
        /<svg>(.*?)<\/svg>/,
        /<cat>.*?<text>.*\[(.*?)\].*?<\/text>.*?<\/cat>\s*<cat>.*?((?:<g>.*?<\/g>)+).*?<\/cat>/g,
        /(?:<g><val>)(10|[0-9])<\/val>(\d+)(?=<\/g>)/gm
    ];

    let [count, sum] = [0, 0];
    let svg = input.match(svgRegex);
    if(!svg){
        console.log('No survey found'); // only zero test 3 check this!!!
        return;
    }
    let surveyData = catsRegex.exec(svg[1]);
    if (!surveyData){
        console.log('Invalid format'); // zero test 4 and test 8 check for this!!!
        return;
    }

    let ratings;
    while(ratings = digits.exec(surveyData[2])){
        count += Number(ratings[2]);
        sum += +ratings[2] * +ratings[1];
    }

    if(count > 0){
        console.log(`${surveyData[1]}: ${parseFloat((sum / count).toFixed(2)).toString()}`);
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