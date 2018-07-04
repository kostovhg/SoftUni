let expect = require('chai').expect;
let lookupChar = require('../c_CharLookup/charLookup');


describe('Test for lookupChar', function () {
    describe('Tests for incorrect inputs', function () {
        it('with a non-string first parameter, should return undefined', function () {
            expect(lookupChar(13, 0)).to.equal(undefined, "the function did not return the correct result!")
        });
        it('with a non-number second parameter, should return undefined', function () {
            expect(lookupChar("pesho", "gosho")).to.equal(undefined, "the function did not return the correct result!")
        });
        it('with a non-integer second parameter, should return undefined', function () {
            expect(lookupChar("pesho", 3.12)).to.equal(undefined, "the function did not return the correct result!")
        });
    });
    describe('Tests for incorrect index value', function () {
        it('with an incorrect index value, should return "Incorrect index"', function () {
            expect(lookupChar("pesho", 13)).to.equal("Incorrect index", "the function did not return the correct result!")
        });
        it('with a negative index parameter, should return "Incorrect index', function () {
            expect(lookupChar("pesho", -1)).to.equal("Incorrect index", "the function did not return the correct result!")
        });
        it('with an index value equal to string length, should return "Incorrect index', function () {
            expect(lookupChar("pesho", 5)).to.equal("Incorrect index", "the function did not return the correct result!")
        });
    });
    describe('Tests for correct input values', function () {
        it('with an incorrect index value, should return "Incorrect index"', function () {
            expect(lookupChar("pesho", 0)).to.equal("p", "the function did not return the correct result!")
        });
        it('with a negative index parameter, should return "Incorrect index', function () {
            expect(lookupChar("pesho", 3)).to.equal("h", "the function did not return the correct result!")
        });
    });
});