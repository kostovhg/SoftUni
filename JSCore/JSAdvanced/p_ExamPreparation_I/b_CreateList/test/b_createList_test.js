let expect = require('chai').expect;
let createList = require('../createList');

describe('tests for createList', function () {

    let list;

    beforeEach(() => {
        list = createList();
        list.add(1);
        list.add('two');
        list.add({num: 3});
    });

    describe('has data array', function () {
        it('should has empty array', function () {
            list = createList();
            expect(list.toString()).to.be.equal('');
            list.shiftLeft();
            expect(list.toString()).to.be.equal('')
        });
    });
    describe('add function', function () {
        it('add should push to the end one member', function () {
            list.add(5);
            expect(list.toString()).to.be.equal('1, two, [object Object], 5');
        });
        it('add should push to the end with two empty strings', function () {
            list = createList();
            list.add('');
            list.add('');
            expect(list.toString()).to.be.equal(', ')
        });
    });
    describe('shiftLeft and shiftRight', function () {
        it('shiftLeft should sift all to left', function () {
            list.shiftLeft();
            expect(list.toString()).to.be.equal('two, [object Object], 1')
        });
        it('shiftRight should shift all to right ', function () {
            list.shiftRight();
            expect(list.toString()).to.be.equal('[object Object], 1, two')
        });
    });
    describe('swap function', function () {
        it('should return false if index1 is not a number', function () {
            expect(list.swap({}, 2)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index1 is not a integer', function () {
            expect(list.swap(1.2, 2)).to.equal(false)
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index1 is < 0', function () {
            expect(list.swap(-1, 1)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index1 is > length', function () {
            expect(list.swap(3, 1)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index2 is not a number', function () {
            expect(list.swap(2, {})).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index2 is not a integer', function () {
            expect(list.swap(2, 1.2)).to.equal(false)
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index2 is < 0', function () {
            expect(list.swap(1, -1)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index2 is > length', function () {
            expect(list.swap(1, 3)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should return false if index1 === index2', function () {
            expect(list.swap(1, 1)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should not swap with invalid indexes', function () {
            expect(list.swap(10, 2)).to.equal(false);
            expect(list.toString()).to.equal('1, two, [object Object]');
        });
        it('should proceed correctly 1 and 2', function () {
            expect(list.swap(1, 2)).to.equal(true)
            expect(list.toString()).to.equal('1, [object Object], two');
        });
        // The purpose of the following test is not very clear and
        // test number 11 does not pass without this
        it('should swap them with index1 = 1 and index 2 = 0', function () {
            expect(list.swap(1, 0)).to.equal(true);
            expect(list.toString()).to.equal('two, 1, [object Object]');
        });
    });

    describe('toString works', function () {
        it('should print elements', function () {
            list.add();
            expect(list.toString()).to.be.equal('1, two, [object Object], ')
        });
    });

});
