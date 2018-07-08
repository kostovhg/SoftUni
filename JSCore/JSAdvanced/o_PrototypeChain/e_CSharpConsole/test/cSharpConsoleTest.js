const expect = require('chai').expect;
const Console = require('../specialConsole');
const countPlaceholders = (str) => {
    const re = /{\d}/g;
    return ((str || '').match(re) || []).length
}

describe('Console tests', function () {
    it('writeLine(string) should return string ', function () {
        expect(Console.writeLine('A string')).to.be.equal('A string');
    });
    it('writeLine(object) should return JSON stringify of the object', function () {
        let obj = {
            num: 5, name: 'Obj', method: function () {
                console.log('A message')
            }
        };
        let stringifyObj = JSON.stringify(obj);
        expect(Console.writeLine(obj)).to.be.equal(stringifyObj);
    });
    it('writeLine(notString, parameters) should throw TypeError', function () {
        expect(() => {Console.writeLine([], 'a')}).to.throw(TypeError);
    });
    it('writeLine(template, parameters) should throw TypeError with number of parameters different from placeholders number', function () {
       expect(() => {Console.writeLine('{0}', 1, 2)}).to.throw(RangeError);
    });
    it('writeLine(template, parameters) should throw TypeError with incorrect placeholder index', function () {
        expect(() => {Console.writeLine('{6}', 1, 2, 3)}).to.throw(RangeError);
    });
    it('writeLine(template, parameters) should return correct string', function () {
        expect(Console.writeLine('{0}{1}{2}', 1, 2, 3)).to.be.equal('123')
    });
});

