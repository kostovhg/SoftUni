let expect = require('chai').expect;
let mathEnforcer = require('../d_MathEnforcer/mathEnforcer');

describe('mathEnforcer', function () {
    describe('addFive', function () {
        it('with a non-number parameter, should return correct result', function () {
            expect(mathEnforcer.addFive("five")).to.be.equal(undefined)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.addFive(5)).to.be.equal(10)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.addFive(50000000000)).to.be.equal(50000000005)
        });
        it('with a negative integer parameter, should return correct result', function () {
            expect(mathEnforcer.addFive(-50000000000)).to.be.equal(-49999999995)
        });
        it('with a floating point, should return correct result', function () {
            expect(mathEnforcer.addFive(0.0399)).to.be.closeTo(5.03, 0.01)
        });

    });
    describe('subtractTen', function () {
        it('with a non-number parameter, should return correct result', function () {
            expect(mathEnforcer.subtractTen("five")).to.be.equal(undefined)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.subtractTen(5)).to.be.equal(-5)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.subtractTen(50000000000)).to.be.equal(49999999990)
        });
        it('with a negative integer parameter, should return correct result', function () {
            expect(mathEnforcer.subtractTen(-50000000000)).to.be.equal(-50000000010)
        });
        it('with a floating point, should return correct result', function () {
            expect(mathEnforcer.subtractTen(0.0399)).to.be.closeTo(-9.9601, 0.01)
        });
    });
    describe('sum', function () {
        it('with a non-number parameter, should return correct result', function () {
            expect(mathEnforcer.sum("five", 1)).to.be.equal(undefined)
        });
        it('with a non-number parameter, should return correct result', function () {
            expect(mathEnforcer.sum(1, "five")).to.be.equal(undefined)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.sum(0, 1)).to.be.equal(1)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.sum(1e10, 1e10)).to.be.equal(2e10)
        });
        it('with a integer parameter, should return correct result', function () {
            expect(mathEnforcer.sum(0, -1)).to.be.equal(-1)
        });
        it('with a negative integer parameter, should return correct result', function () {
            expect(mathEnforcer.sum(-1e10, 1e10)).to.be.equal(0)
        });
        it('with a floating point first parameter, should return correct result', function () {
            expect(mathEnforcer.sum(0.09999999, 1)).to.be.closeTo(1.099999, 0.01)
        });
        it('with a floating point second parameter, should return correct result', function () {
            expect(mathEnforcer.sum(1, 0.09999999)).to.be.closeTo(1.099999, 0.01)
        });
    });
});