let expect = require('chai').expect;
let PaymentPackage = require('../paymentPackage');

describe('PaymentPackage class tests', function () {
    describe('instantiation of the class and accessors with correct data', function () {
        let aPesho;
        beforeEach('Create object', function () {
            aPesho = new PaymentPackage('Pesho', 450.5);
        });

        it('should return valid class with new("Pesho", 20)', function () {
            expect(new PaymentPackage('Pesho', 450.5)).to.eql(aPesho);
            //expect(new PaymentPackage('pesho', 20)).to.not.eql(aPesho);
        });
        it('getter name() should return Pesho ', function () {
            expect(aPesho.name).to.equal('Pesho');
        });
        it('setter name should return new name Gosho ', function () {
            aPesho.name ='Gosho';
            expect(aPesho.name).to.equal('Gosho');
        });
        it('getter value should return 450.5', function () {
            expect(aPesho.value).to.eq(450.5)
        });
        it('setter value should return new value 132.17', function () {
            aPesho.value = 132.17;
            expect(aPesho.value).to.eq(132.17)
        });
        it('getter VAT should return 20 (default)', function () {
            expect(aPesho.VAT).to.eq(20)
        });
        it('setter VAT should return new VAT 15', function () {
            aPesho.value = 15;
            expect(aPesho.value).to.eq(15)
        });
        it('getter active should return true (default)', function () {
            expect(aPesho.active).to.eq(true)
        });
        it('setter active should return new status false', function () {
            aPesho.active = false;
            expect(aPesho.active).to.eq(false)
        });
        it('toString works correctly', function () {
            expect(aPesho.toString()).to.equal('Package: Pesho\n- Value (excl. VAT): 450.5\n- Value (VAT 20%): 540.6')

        });
    });
    describe('incorrect inputs', function () {

        let create = (name, val) => new PaymentPackage(name, val);
        let correctObj = new PaymentPackage('Pesho', 450.5);

        it('should throw error when name is not a string', function () {
            // through the constructor
            expect(() => create(5, 5)).to.throw('Name must be a non-empty string');
            // through the setter
            expect(() => correctObj.name = 5).to.throw('Name must be a non-empty string');
        });
        it('should throw error when name is an empty string', function () {
            // through the constructor
            expect(() => create('', 5)).to.throw('Name must be a non-empty string');
            // through the setter
            expect(() => correctObj.name = '').to.throw('Name must be a non-empty string');
        });
        it('should throw error when value is not a number', function () {
            // through the constructor
            expect(() => create('Pesho', '5000')).to.throw('Value must be a non-negative number');
            // through the setter
            expect(() => correctObj.value = '5000').to.throw('Value must be a non-negative number');
        });
        it('should throw error when value is negative', function () {
            // through the constructor
            expect(() => create('Pesho', -50.5)).to.throw('Value must be a non-negative number');
            // through the setter
            expect(() => correctObj.value = -50.5).to.throw('Value must be a non-negative number');
        });
        it('should throw error when Vat is not a number', function () {
            expect(() => correctObj.VAT = '50.5').to.throw('VAT must be a non-negative number');
            expect(() => correctObj.VAT = '50.5').to.throw( Error);
        });
        it('should throw error when Vat is not a negative', function () {
            expect(() => correctObj.VAT = -50.5).to.throw('VAT must be a non-negative number');
            expect(() => correctObj.VAT = -50.5).to.throw( Error);
        });
        it('should throw error when active is not a boolean', function () {
            expect(() => correctObj.active = 'valse').to.throw('Active status must be a boolean');
            expect(() => correctObj.active = 'valse').to.throw( Error);
        });
    });
});