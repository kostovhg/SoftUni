//TEMPLATE FOR DOM TESTS
// Improved to show which tests failed
"use strict";
let fs = require('fs');
let Mocha = require('mocha');
let mocha = new Mocha({reporter: 'json'});

function createTests(pathToUsercode) {
    let numberOfCompeteTests = fs.readdirSync('./tests').filter(f => /^((?!open|000).)*?(\d{3}\.in\.js)$/.test(f)).length;

    let code = fs.readFileSync(pathToUsercode);
    let write = process.stdout.write;

    let promise = new Promise(function (resolve, reject) {
        //Technically run first test func
        let testFile = addRequires();
        testFile += fs.readFileSync('./tests/test.000.001.in.js');
        testFile += code + '\n';

        fs.writeFileSync('compiledTest.js', testFile);
        mocha.addFile('./compiledTest.js');

        let output = '';
        process.stdout.write = function (str) {
            output += str;
        };

        let userTestCount = 0;

        mocha.run(function (failures) {
            output = JSON.parse(output);
            //If its the zero test run just get the number of tests and return
            userTestCount = output.tests.length;
            resolve(userTestCount);
        });
    });

    promise.then(val=>{
        //Delete temp file
        fs.unlinkSync('./compiledTest.js');
        runTest(val);
    });

    function runTest(numberOfUserTests) {
        let testFile = addRequires();
        testFile += "let $currentTest = 0;\n";
        testFile += buildTestPreconditions(numberOfUserTests, numberOfCompeteTests);

        //We add 1 to account for the second zero test
        let allTestRuns = numberOfCompeteTests + 1;
        for (let i = 0; i < numberOfCompeteTests + 1; i++) {
            testFile += '{\n';
            testFile += code + '\n';
            testFile += '}\n';
        }

        fs.writeFileSync('./compiledTest.js', testFile);
        delete require.cache[require.resolve('./compiledTest.js')];
        let mocha2 = new Mocha({reporter: 'json'});
        mocha2.addFile('./compiledTest.js');

        let output = '';
        process.stdout.write = function (str) {
            output += str;
        };
        mocha2.run(function (failures) {
            output = JSON.parse(output);
            process.stdout.write = write;
            let basePassedTests = 0;
            let failed = [];
            for(let i = 0;i<numberOfUserTests;i++){
                if(!output.tests[i].err.message){
                    basePassedTests++;
                } else {
                    failed.push([i, output.tests[i].err.message]);
                }
            }
            console.log(`Found Tests: ${numberOfUserTests}`);
            console.log(`Base passed Tests: ${basePassedTests}`);
            if (failed.length > 0) {
                console.log(`Failed Tests: ${failed.map(m => m[0]).join(', ')}`);
                console.log(failed.map(m => m[1]).join('\n'));
            }

            let passedTests = 0;
            let currentTestNum = numberOfUserTests;
            for(let i = 1;i<=numberOfCompeteTests;i++){
                passedTests = 0;
                let errors = [];
                for(let k = 0;k<numberOfUserTests;k++){
                    if(!output.tests[currentTestNum].err.message){
                        passedTests++;
                    }
                    else{
                        errors.push(output.tests[currentTestNum].err.message);
                    }
                    currentTestNum++;
                }

                if(passedTests < basePassedTests){
                    console.log(`Test ${i} passed: ${passedTests}`);
                }
                else{
                    console.log(`Test ${i} failed: No tests covering this functionality!`);
                }
            }
            
            //Delete temp file
            fs.unlinkSync('./compiledTest.js');
        });
    }


    function addRequires() {
        let requires = "let expect = require('chai').expect;\n";
        requires += "let jsdom = require('jsdom-global')();\n";
        requires += "let $ = require('jquery');\n";
        return requires;
    }

    function buildTestPreconditions(numberOfUserTests, numberOfCompeteTests) {
        //We group all test executions in one, to know what test input to load
        //every time X tests have passed, we'll switch to the next test input (X is the amount of tests the user submitted)
        let testCountLimit = numberOfUserTests;
        let test = fs.readFileSync('./tests/test.000.001.in.js') + '\n';
        test += "beforeEach(function(){\n";
        test += `if($currentTest < ${testCountLimit}){\n`;
        test += fs.readFileSync('./tests/test.000.002.in.js');
        testCountLimit += numberOfUserTests;
        for (let i = 1; i <= numberOfCompeteTests; i++) {
            test += `} else if($currentTest < ${testCountLimit}){\n`;
            test += fs.readFileSync(`./tests/test.0${i < 10 ? '0' + i : i}.in.js`) + '\n';
            testCountLimit += numberOfUserTests;
        }
        test += "}\n";
        test += "});\n";

        //Set $currentTest increase in an afterEach hook
        test += "afterEach(function(){\n$currentTest++;\n});\n";
        return test;
    }
}

// END OT TEMPLATE CODE

/* YOU NEED Mocha, Chai, jsdom, jsdom-global and jquery installed in a way that node can find them to run the template
 Keep the position of "tests" folder in mind too, PATHS ARE RELATIVE
 */

// Supply path to user file, the number of competition tests
createTests('./solution.js');


