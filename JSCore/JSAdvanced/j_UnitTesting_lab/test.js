let expect = require('chai').expect;
let sum = require('d_SumOfNumbers/sumOfNumbers');


describe('group', function () {
    it('Test name', function () {
        expect(sum([1,2])).to.be.equal(3);
    })
});