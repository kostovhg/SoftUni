require('jsdom-global')();
let $ = require('jquery');
let expect = require('chai').expect;
let sinon = require('sinon');

function runTests() {
    const fs = require('fs');
    let code = `let result = ${fs.readFileSync('./solution.js', 'utf-8')}`;

    let tests = fs.readdirSync('./tests').filter(f => f.substr(-6) === '.in.js');

    for (let test of tests) {
        let testId = test.substr(-9,3);
        try {
            test = fs.readFileSync(`./tests/${test}`, 'utf-8');
        } catch (err) {
            console.log(`Error reading test ${testId}`);
            continue;
        }
        try {
            eval(code + '\n' + test);
            console.log(`--- Test ${testId}: Pass OK`);
        } catch (err) {
            console.log(`Test ${testId} failed: ${err}`);
        }
    }
}

runTests();