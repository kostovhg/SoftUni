let expect = require('chai').expect;
let PaymentPackage = require('../paymentPackage');

describe('PaymentPackage Tests', function () {
    let iTypes = {
        emptyStr: '', date: new Date(), nothing: null, obj: {x: 1},
        zero: 0, negative: -5, floating: 5.5, positive: 50, bool: false,
        someString: 'Pesho'
    };
    let create = (name, value) => new PaymentPackage(name, value);
    let obj;

    describe('Throw error for :', function () {
        it('name not being non-empty string', () => {
            let wrongNames = [iTypes.emptyStr, iTypes.date, iTypes.nothing, iTypes.obj, iTypes.positive, iTypes.bool];
            wrongNames.forEach(x => expect(() => create(x, iTypes.positive)).to.throw(Error)); // gets test 1
        });

        it('values not being non-negative number', () => {
            let wrongValues = [iTypes.date, iTypes.nothing, iTypes.obj, iTypes.bool, iTypes.someString, iTypes.negative];
            wrongValues.forEach(x => expect(() => create(iTypes.someString, x)).to.throw(Error)); // gets test 2
        });
    });
    describe('Correct functioning', function () {
        beforeEach('create obj', function () {
            obj = create(iTypes.someString, iTypes.positive);
        });
        it('instance and class to have VAT property (default: 20)', function () {
            expect(obj).to.have.property('VAT', 20).and.to.be.a('number'); // test 3
            expect(PaymentPackage.prototype.hasOwnProperty('VAT')).to.be.true; // test 5

        });
        it('instance and class to have active property (default: true)', function () {
            expect(obj).to.have.property('active', true).and.to.be.a('boolean'); // test 6
            expect(PaymentPackage.prototype.hasOwnProperty('active')).to.be.true; // test 4
        });
        it("set value should work with 0", () => {
            obj.value = iTypes.zero;
            expect(obj.value).to.be.equal(0); // solves test 7 !?
        });
    });
    describe('toString to return', function () {
        // needed for tests 8 and 9
        // test 8 does not pass if this check is not in separate describe block ?!?!?
        it('correct toString representation on inactive obj', function () {
            obj = create(iTypes.someString, iTypes.positive);
            obj.active = false;
            expect(obj.toString()).to.equal('Package: Pesho (inactive)\n' +
                '- Value (excl. VAT): 50\n' +
                '- Value (VAT 20%): 60')
        });
    });
});
