let expect = require('chai').expect;
let sharedObject = require('../e_SharedObject/sharedObject');

let jsdom = require('jsdom-global')();
let $ = require('jquery');
global.$ = $;

document.body.innerHTML = `<div id="wrapper">
<input type="text" id="name">
<input type="text" id="income">
</div>`;

describe('sharedObject function tests', function () {
    describe('name object property', function () {
        it('should start with null value', function () {
            expect(sharedObject.name).to.be.equal(null, 'Name is different from null on initialization')
        });
    });
    describe('income object property', function () {
        it('should start with null value', function () {
            expect(sharedObject.income).to.be.equal(null, 'Income is different from null on initialization')
        });
    });
    describe('changeName function', function () {
        it('should not change object name property with invalid parameter', function () {
            sharedObject.changeName('');
            expect(sharedObject.name).to.be.equal(null, 'Name is different from null on initialization')
        });
        it('should not change object name property with invalid parameter and preexisting value', function () {
            sharedObject.name = 'oldName';
            sharedObject.changeName('');
            expect(sharedObject.name).to.be.equal('oldName')
        });
        it('should not change #name field value with invalid parameter', function () {
            let textBox = $('#name');
            textBox.val('rand');
            sharedObject.changeName('');
            expect(textBox.val()).to.be.equal('rand')
        });
        it('should change object name property with valid parameter', function () {
            sharedObject.changeName('Boris');
            expect(sharedObject.name).to.be.equal('Boris', 'Name is different from Boris')
        });
        it('should change #name field value with valid parameter', function () {
            let textBox = $('#name');
            textBox.val('Gosho');
            sharedObject.changeName('Gosho');
            expect(textBox.val()).to.be.equal('Gosho')
        });
    });
    describe('changeIncome function', function () {
        it('should not change income property with invalid parameters 4.2', function () {
            sharedObject.changeIncome(4.2);
            expect(sharedObject.income).to.equal(null, 'Income changes incorrectly')
        });
        it('should not change income property with invalid parameters 0', function () {
            sharedObject.changeIncome(0);
            expect(sharedObject.income).to.equal(null, 'Income changes incorrectly')
        });

        it('should not change income property with invalid parameters -5', function () {
            sharedObject.changeIncome(-5);
            expect(sharedObject.income).to.equal(null, 'Income changes incorrectly')
        });
        it('should not change preexisting value of income property with invalid parameters 4.2', function () {
            sharedObject.income = 55;
            sharedObject.changeIncome(4.2);
            expect(sharedObject.income).to.equal(55, 'Income changes incorrectly')
        });
        it('should not change preexisting value of income property with invalid parameters 0', function () {
            sharedObject.income = 55;
            sharedObject.changeIncome(0);
            expect(sharedObject.income).to.equal(55, 'Income changes incorrectly')
        });
        it('should not change preexisting value of income property with invalid parameters -5', function () {
            sharedObject.income = 55;
            sharedObject.changeIncome(-5);
            expect(sharedObject.income).to.equal(55, 'Income changes incorrectly')
        });
        it('should not change income field value with invalid parameters 4.2', function () {
            let incomeField = $("#income");
            sharedObject.changeIncome(4.2);
            expect(incomeField.val()).to.equal('', 'Income changes incorrectly')
        });
        it('should not change income field value with invalid parameters 0', function () {
            let incomeField = $("#income");
            sharedObject.changeIncome(0);
            expect(incomeField.val()).to.equal('', 'Income changes incorrectly')
        });

        it('should not change income field value with invalid parameters -5', function () {
            let incomeField = $("#income");
            sharedObject.changeIncome(-5);
            expect(incomeField.val()).to.equal('', 'Income changes incorrectly')
        });
        it('should not change preexisting value of income property with invalid parameters 4.2', function () {
            let incomeField = $("#income");
            incomeField.val(55);
            sharedObject.changeIncome(4.2);
            expect(incomeField.val()).to.equal('55', 'Income changes incorrectly')
        });
        it('should not change preexisting value of income property with invalid parameters 0', function () {
            let incomeField = $("#income");
            incomeField.val(55);
            sharedObject.changeIncome(0);
            expect(incomeField.val()).to.equal('55', 'Income changes incorrectly')
        });
        it('should not change preexisting value of income property with invalid parameters -5', function () {
            let incomeField = $("#income");
            incomeField.val(55);
            sharedObject.changeIncome(-5);
            expect(incomeField.val()).to.equal('55', 'Income changes incorrectly')
        });
        it('should change income object property with valid parameter', function () {
            sharedObject.changeIncome(100);
            expect(sharedObject.income).to.equal(100, 'Income has not changed correctly');
        });
        it('should change income field with valid parameter', function () {
            let incomeField = $("#income");
            sharedObject.changeIncome(50);
            expect(incomeField.val()).to.equal('50', 'Income field has not changed correctly');
        });
    });
    describe('updateName function', function () {
        it('should not change name property with invalid input value', function () {
            sharedObject.name = "OldName";
            let newName = $('#name').val('');
            sharedObject.updateName();
            expect(sharedObject.name).to.equal('OldName', `Object property has been changed when it should't`)
        });
        it('should change name property with valid input value ', function () {
            sharedObject.name = "OldName";
            let newName = $('#name').val('Name');
            sharedObject.updateName();
            expect(sharedObject.name).to.equal('Name', `Object property has not been changed when it should`)
        })
    });
    describe('updateIncome function', function () {
        it('should not change income property with invalid input value (empty)', function () {
            sharedObject.income = 100;
            let newIncome = $('#income').val('');
            sharedObject.updateIncome();
            expect(sharedObject.income).to.equal(100, 'income has been changed with incorrect textbox value (empty)')
        });
        it('should not change income property with invalid input value (NaN)', function () {
            sharedObject.income = 100;
            let newIncome = $('#income').val('five-hundred');
            sharedObject.updateIncome();
            expect(sharedObject.income).to.equal(100, 'income has been changed with incorrect textbox value (NaN)')
        });
        it('should not change income property with invalid input value (non-Integer)', function () {
            sharedObject.income = 100;
            let newIncome = $('#income').val(3.5);
            sharedObject.updateIncome();
            expect(sharedObject.income).to.equal(100, 'income has been changed with incorrect textbox value (3.5)')
        });
        it('should not change income property with invalid input value (0)', function () {
            sharedObject.income = 100;
            let newIncome = $('#income').val(0);
            sharedObject.updateIncome();
            expect(sharedObject.income).to.equal(100, 'income has been changed with incorrect textbox value (0)')
        });
        it('should not change income property with invalid input value (-10)', function () {
            sharedObject.income = 100;
            let newIncome = $('#income').val(-10);
            sharedObject.updateIncome();
            expect(sharedObject.income).to.equal(100, 'income has been changed with incorrect textbox value (0)')
        });
        it('should change income property with valid input value (10)', function () {
            sharedObject.income = 100;
            let newIncome = $('#income').val(10);
            sharedObject.updateIncome();
            expect(sharedObject.income).to.equal(10, 'income has been changed with incorrect textbox value (0)')
        });
    })

});