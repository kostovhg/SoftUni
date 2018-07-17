let expect = require('chai').expect;
let Calculator = require('../Calculator');


describe('Sumator class testing', function () {
    let types = {
        date: new Date(), nothing: null, obj: {x: 1}, arr: [1, 2], undef: undefined,
        negative: -5, negativeFloating: -5.5, zero: 0, floating: 5.5, positive: 50, bool: false,
        beforeUpperBond: 5, lowerBond: 6, between: 80, upperBond: 100, afterUpperBond: 101,
        pesho: 'Pesho', email: 'pesho@gmail.com', gosho: 'Gosho', emptyStr: '',
        get nonStrings() {
            return [
                this.date, this.obj,
                this.zero, this.negative, this.floating, this.positive,
                this.beforeUpperBond, this.lowerBond, this.between, this.upperBond, this.afterUpperBond,
                this.bool]
        },
        get nonNumbers() {
            return [
                this.emptyStr, this.pesho,
                this.date, this.obj]
        }
    };
    let obj;
   /* console.log('Test');*/
    describe('New instance  properties', function () {
        let obj;
        beforeEach('Initialize func', function (){
            obj = new Calculator();
        });

        it('should have expenses property', function () {
            expect(obj.hasOwnProperty('expenses')).to.be.true;
        });
        it('should have expenses property equal to []', function () {
            expect(obj.expenses).to.be.an('array').that.is.empty;
        });
    });

    describe('Correct functioning', function () {
        let inputs = 0;
        beforeEach('create obj"', () => {
            obj = new Calculator();
        });

        it('add should add any type of object', function () {
            [...Object.getOwnPropertyNames(types)]
            //.filter(x => !(typeof types[x] === 'object' || typeof types[x] === 'function'))
                .forEach(t => {
                    obj.add(types[t]);
                    inputs++
                });
            expect(obj.expenses.length).to.be.equal(inputs);
        });
        it('divideNums should divide all numbers', function () {
            obj.add(2);
            obj.add(5);
            obj.add(2);
            expect(obj.divideNums()).to.be.closeTo((2 / 5) / 2, 0.0000001);
        });
        it('divideNums should throw error on non numbers', function () {
            obj.add({});
            obj.add('Pesho');
            obj.add(new Date());
            // expect(() => obj.divideNums()).to.throw();
            expect(() => obj.divideNums()).to.throw('There are no numbers in the array!');
        });
        it('divideNums should throw empty', function () {
            // expect(() => obj.divideNums()).to.throw();
            expect(() => obj.divideNums()).to.throw('There are no numbers in the array!');
        });
        it('divideNums should return dev on 0', function () {
            [...Object.getOwnPropertyNames(types)]
            //.filter(x => !(typeof types[x] === 'object' || typeof types[x] === 'function'))
                .forEach(t => {
                    obj.add(types[t]);});
            expect(obj.divideNums()).to.be.equal('Cannot divide by zero');
        });

        it('toString should print for empty', function () {
            expect(obj.toString()).to.equal('empty array')
        });
        it('toString should print for empty', function () {
            obj.add({});
            obj.add('Pesho');
            expect(obj.toString()).to.equal('[object Object] -> Pesho')
        });

        it('orderBy should return empty', function () {
            expect(obj.orderBy()).to.be.equal('empty')
        });
        it('orderBy should return ordered', function () {
            [...Object.getOwnPropertyNames(types)]
            .filter(x => !(typeof types[x] === 'object' || typeof types[x] === 'function'))
                .forEach(t => {
                    obj.add(types[t]);
                    inputs++
                });
            expect(obj.orderBy()).to.be.equal(', -5, -5.5, 0, 100, 101, 5, 5.5, 50, 6, 80, Gosho, Pesho, false, pesho@gmail.com, ');
        });

    });
});