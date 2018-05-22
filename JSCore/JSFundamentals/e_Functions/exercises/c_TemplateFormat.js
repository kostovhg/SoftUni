function templateFormat(input) {

    let result = [`<?xml version="1.0" encoding="UTF-8"?>`, `<quiz>`];

    for (let i = 0; i < input.length; ) {
        result.push(`  <question>`);
        result.push(`    ${input[i++]}`);
        result.push(`  </question>`);
        result.push(`  <answer>`);
        result.push(`    ${input[i++]}`);
        result.push(`  </answer>`);
    }

    result.push(`</quiz>`);

    console.log(result.join('\n'));
}

templateFormat([
    "Who was the forty-second president of the U.S.A.?",
    "William Jefferson Clinton"
]);

templateFormat([
    "Dry ice is a frozen from of which gas?",
    "Carbon Dioxide",
    "What is the brightest star in the night sky?",
    "Sirius"
]);