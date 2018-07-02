let expect = require('chai').expect;
const isSymmetric = require('../e_CheckForSymmetry/checkForSymmetry');

describe('Check for symmetry tests', function () {
    describe('Valid inputs', function () {

        it('Should return true for [1, 2, 1]', function () {
            expect(isSymmetric([1, 2, 1])).to.be.equal(true)
        });
        it('Should return false for [1, 2, 3]', function () {
            expect(isSymmetric([1, 2, 3])).to.be.equal(false)
        });
        it('Should return false for [1, 2, 3]', function () {
            expect(isSymmetric([])).to.be.equal(true)
        });
        it('Should return false for ["test"]', function () {
            expect(isSymmetric(['test'])).to.be.equal(true)
        });
        it('Should return false for [-1, 2, 1]', function () {
            expect(isSymmetric([-1, 2, 1])).to.be.equal(false)
        });
        it('Should throw error for [5, "hi", {a:5}, new Date(), {a:5}, "hi", 5]', function () {
            expect(isSymmetric([5, "hi", {a:5}, new Date(), {a:5}, "hi", 5])).to.be.equal(true);
        });
        it('Should throw error for [5, "hi", {a:5}, new Date(), {a:5}, "hi", 5]', function () {
            expect(isSymmetric([5, "hi", {a:5}, new Date(), {X:5}, "hi", 5])).to.be.equal(false);
        })
    });
    describe('Invalid inputs', function () {
        it('Should throw error for "1, 2, 3"', function () {
            expect(isSymmetric("1, 2, 3")).to.be.equal(false)
        });
        it('Should throw error for {}', function () {
            expect(isSymmetric({})).to.be.equal(false);
        })

    })
});