let expect = require('chai').expect;
let Sumator = require('../sumator');

describe('Sumator class testing', () => {
    let types = {
        date: new Date(), nothing: null, obj: {x: 1}, undef: undefined,
        zero: 0, negative: -5, floating: 5.5, positive: 50, bool: false,
        someString: 'Pesho', emptyStr: '',
        get nonStrings() {
            return [
                this.date, this.obj,
                this.zero, this.negative, this.floating,
                this.positive, this.bool]
        },
        get nonNumbers() {
            return [
                this.emptyStr, this.someString,
                this.date, this.obj]
        }
    };
    let create = (param) => new Sumator();
    let obj;

    describe('Can be instantiated with a passed in:', () => {
        // no points
        it('string', () => {
            obj = create(types.someString);
            expect(() => create(types.someString)).to.not.throw();
            expect(obj).to.have.own.property('_stringArray');
            expect(obj.toString()).to.be.equal(types.someString);
        });
        it('without anything', () => {
            obj = create();
            expect(() => create()).to.not.throw();
            expect(obj).to.have.own.property('_stringArray');
            expect(obj.toString()).to.be.equal('')
        });

        it('should have all properties', function () {
            expect((create()).hasOwnProperty('_stringArray')).to.equal(true);
        });
        it('has functions attached to prototype', function () {
            // this should work for all
            obj = create();
            let classProperties = Object.getOwnPropertyNames(obj);
            classProperties.filter((p, i) => (!p.startsWith('_') || i > 1)).forEach((p, i) =>
                console.log(`prop: ${p}, index: ${i}, arguments ${obj[p].length}`));
            classProperties
                .filter((p, i) => (!p.startsWith('_') || i > 1))
                .forEach(p =>
                    expect(Object.getPrototypeOf(obj)).to.have.property(p, obj[p], `Missing ${p} function`));
        });
    });

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

    describe('Correct functioning', function () {

        beforeEach('create with "hello"', () => {
            obj = create('hello');
        });

        it('new StringBuilder("hello")', () => {
            expect(obj.toString()).to.be.equal('hello');
        });
        it('.append(", there")', () => {
            obj.append(", there");
            expect(obj.toString()).to.be.equal('hello, there');
        });
        it('.prepend("User, ")', () => {
            obj.prepend("User, ");
            expect(obj.toString()).to.be.equal('User, hello');
        });
        it('.insertAt("woop,5")', () => {
            obj.prepend("User, ");
            obj.insertAt('woop', 5);
            expect(obj.toString()).to.be.equal('User,woop hello');
        });
        it('.insertAt("woop,5")', () => {
            obj.prepend("User, ");
            obj.insertAt('woop', 5);
            obj.remove(6, 3);
            expect(obj.toString()).to.be.equal('User,w hello');
        });
    });
});
