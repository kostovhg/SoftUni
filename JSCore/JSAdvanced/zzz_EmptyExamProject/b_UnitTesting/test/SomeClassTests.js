let expect = require('chai').expect;
let SomeClass = require('../index');

describe('Sumator class testing', () => {
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
    let create = () => new Sumator();
    let obj;

    describe('New instance has class properties', () => {

        beforeEach('Initialize obj', () => {
            obj = create();
        });

        it('should have all properties', function () {
            expect((create()).hasOwnProperty('data')).to.equal(true);
        });
        it('should have data = []', function () {
            expect(create().data).to.be.an('array').that.is.empty;
        });
        /*        it('has functions attached to prototype', function () {
                    // this should work for all
                    let theClass = Object.getPrototypeOf(obj);
                    let classMethods = Object.getOwnPropertyNames(theClass);
                    classMethods.forEach(p => console.log(`Class method : ${p}`))
                    let objectProperties = Object.getOwnPropertyNames(obj);
                    objectProperties
                        .forEach((m, i) =>
                            console.log(`Obj prop: ${m}, index: ${i}, arguments ${obj[m].length}`))
                    objectProperties
                        .forEach((p, i) => {
                            console.log(`Class prop: ${p}, index: ${i}, arguments ${obj[p].length}`);
                            expect(theClass).to.have.property(p, obj[p], `Missing ${p} function`)
                        });
                });*/
        it('has functions attached to prototype', function () {
            expect(Object.getPrototypeOf(obj).hasOwnProperty('add')).to.equal(true, "Missing add function");
            expect(Object.getPrototypeOf(obj).hasOwnProperty('sumNums')).to.equal(true, "Missing sumNums function");
            expect(Object.getPrototypeOf(obj).hasOwnProperty('removeByFilter')).to.equal(true, "Missing removeByFilter function");
            expect(Object.getPrototypeOf(obj).hasOwnProperty('toString')).to.equal(true, "Missing toString function");
        });

    });

    /* does not thrown errors
     describe('Errors thrown for:', () => {
          beforeEach('create obj', () => {
              obj = create();
          });
          it('constructor(non-string)', () => {
              types.nonStrings.forEach(i => {
                  expect(() => create(i)).to.throw('Argument must be string')
              })
          });
          it('append(non-string)', () => {
              types.nonStrings.forEach(i => expect(() => obj.append(i)).to.throw('Argument must be string'))
          });
          it('prepend(non-string)', () => {
              types.nonStrings.forEach(i => expect(() => obj.prepend(i)).to.throw('Argument must be string'))
          });
          it('insertAt(non-string, startIndex)', () => {
              types.nonStrings.forEach(i => expect(() => obj.insertAt(i, 1)).to.throw('Argument must be string'))
          });
      });
  */
    describe('Correct functioning', function () {

        beforeEach('create obj"', () => {
            obj = create();
        });

        it('test to String on new obj', () => {
            expect(obj.toString()).to.be.equal('(empty)');
        });
        it('.add(item)', () => {
            obj.add(", there");
            expect(obj.data.length).to.be.equal(1);
            expect(obj.toString()).to.be.equal(', there');
        });
        it('.sumNums with numbers', () => {
            obj.add(3);
            obj.add(4);
            expect(obj.sumNums()).to.equal(7)
            expect(obj.toString()).to.be.equal('3, 4');
        });
        it('.sumNums with non-nums', () => {
            obj.add(types.obj);
            obj.add(4);
            expect(obj.sumNums()).to.equal(4)
            expect(obj.toString()).to.be.equal('[object Object], 4');
        });
        it('.sumNums without nums', () => {
            obj.add(types.obj);
            obj.add(types.email);
            expect(obj.sumNums()).to.equal(0)
            expect(obj.toString()).to.be.equal('[object Object], pesho@gmail.com');
        });
        it('.removeByFilter() filter x % 2 === 0', () => {
            let filter = (x) => x % 2 === 0;
            [...Object.getOwnPropertyNames(types)]
                .filter(x => !(typeof types[x] === 'object' || typeof types[x] === 'function'))
                .forEach(t => obj.add(types[t]));
            obj.removeByFilter(filter);
            expect(obj.data.length).to.be.equal(9)
            expect(obj.toString()).to.be.equal(', -5, -5.5, 5.5, 5, 101, Pesho, pesho@gmail.com, Gosho');
        });
        it('.removeByFilter() filter non-strings', () => {
            let filter = (x) => typeof x !== 'string';
            [...Object.getOwnPropertyNames(types)]
            //.filter(x => !(typeof types[x] === 'object' || typeof types[x] === 'function'))
                .forEach(t => obj.add(types[t]));
            obj.removeByFilter(filter);
            expect(obj.data.length).to.be.equal(4)
            expect(obj.toString()).to.be.equal('Pesho, pesho@gmail.com, Gosho, ');
        });
        it('.toString()', () => {
            [...Object.getOwnPropertyNames(types)]
                .filter(x => !(typeof types[x] !== 'number'))
                .forEach(t => obj.add(types[t]));
            expect(obj.toString()).to.be.equal('-5, -5.5, 0, 5.5, 50, 5, 6, 80, 100, 101');
        });
        it('.toString() after filter', () => {
            [...Object.getOwnPropertyNames(types)]
                .filter(x => !(typeof types[x] !== 'number'))
                .forEach(t => obj.add(types[t]));
            obj.removeByFilter(x => typeof(x) === 'number');
            expect(obj.toString()).to.be.equal('(empty)');
        });
    });
});
