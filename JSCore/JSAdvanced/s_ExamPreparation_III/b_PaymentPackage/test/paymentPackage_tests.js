let expect = require('chai').expect;
let PaymentPackage = require('../paymentPackage');

// after many checks:
// test    8        : toString should return string
// test 5, 8, 9     : toString should return correct object representation
// test 5, 8, 9, 10 : toString should return (inactive)
// test    8, 9     : toString returns properly calculated values
// test    8, 9, 10 : toString with change on VAT and inactive should return correct result
// test    8,       : instantiated with two correct arguments not to thrown error
// test    8,       : instantiated class should be constructor.name
// test 1,          : new class should have property name and it should be a string
// test 3, 5, 8     : new class should have property Vat, and it should be correct number


describe('PaymentPackage Tests', function () {
    let inputTypes = {
        emptyStr: '', date: new Date(), nothing: null, obj: {x: 1},
        zero: 0, negative: -5, floating: 5.5, positive: 50, bool: false,
        someString: 'Pesho'
    };
    let create = (name, value) => new PaymentPackage(name, value);
    let obj;

    describe('Throw error for :', function () {
        // gets test 1
        it('name not non-empty string', () => {
            let wrongNames = [inputTypes.emptyStr, inputTypes.date, inputTypes.nothing, inputTypes.obj, inputTypes.positive, inputTypes.bool];
            wrongNames.forEach(x => expect(() => create(x, inputTypes.positive)).to.throw(Error));
        });
        // gets test 2
        it('value not non-negative number', () => {
            let wrongValues = [inputTypes.date, inputTypes.nothing, inputTypes.obj, inputTypes.bool, inputTypes.someString, inputTypes.negative];
            wrongValues.forEach(x => expect(() => create(inputTypes.someString, x)).to.throw(Error));

        });
    });
    describe('toString to return', function () {
        beforeEach('create obj', function () {
            // ! Very important is to reinitialize the object before each test
            obj = create(inputTypes.someString, inputTypes.positive);
        });
        // needed for tests 8 and 9
        it('correct representation on inactive obj', function () {
            obj.active = false;
            expect(obj.toString()).to.equal('Package: Pesho (inactive)\n' +
                '- Value (excl. VAT): 50\n' +
                '- Value (VAT 20%): 60')
        });
        /* Not needed
        it('correct representation on active obj', function () {
                    expect(obj.toString()).to.equal('Package: Pesho\n' +
                        '- Value (excl. VAT): 50\n' +
                        '- Value (VAT 20%): 60');
                });
        it('changing the value should change value and VAT', function () {
             obj.value = 100.40;
             expect(obj.toString()).to.equal("Package: Pesho\n" +
                 "- Value (excl. VAT): 100.4\n" +
                 "- Value (VAT 20%): 120.48")
         });
        */
    });
    describe('Correct functioning', function () {
        beforeEach('create obj', function () {
            // ! Very important is to reinitialize the object before each test
            obj = create(inputTypes.someString, inputTypes.positive);
        });
        /*  Not needed
        it('instantiate creates object ', function () {
            expect(obj).to.be.an('object');
            expect(obj).to.be.instanceOf(PaymentPackage);
        });
        it('instance to have name property ', function () {
            expect(obj).to.have.property('name', par.someString,).and.to.be.a('string');
        });
        it('instance to have value property ', function () {
            expect(obj).to.have.property('value', par.positive).and.to.be.a('number');
        });
        it('instance to have constructor', function () {
            expect(obj).to.have.property('constructor').to.be.a('function');
        });
        it('instance to be of type PaymentPackage', function () {
            expect(obj).to.be.instanceOf(PaymentPackage);
        });
        it('constructor should accept 2 arguments', function () {
            expect(PaymentPackage.length).to.be.equal(2)
        });
        */
        it('instance to have VAT property ', function () {
            expect(obj).to.have.property('VAT', 20).and.to.be.a('number'); // test 3
            expect(PaymentPackage.prototype.hasOwnProperty('VAT')).to.be.true; // test 5

        });
        it('instance to have active property ', function () {
            // tests 4 and 6
            expect(obj).to.have.property('active', true).to.be.a('boolean');
            expect(PaymentPackage.prototype.hasOwnProperty('active')).to.be.true;

        });
        it("should change the value to 0", () => {
            // solves test 7 !?
            obj.value = 0;
            expect(obj.value).to.be.equal(0);
        });
        it('instance to have toString function ', function () {
            // solves test 8
            expect(obj).to.have.property('toString').to.be.a('function');
        });
    });
});
