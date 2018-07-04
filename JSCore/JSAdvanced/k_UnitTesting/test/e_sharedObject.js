let expect = require('chai').expect;
let sharedObject = require('../e_SharedObject/sharedObject');

let jsdom = require('jsdom-global')();
let $ = require('jquery');
document.body.innerHTML = `<div id="wrapper">
<input type="text" id="name">
<input type="text" id="income">
</div>`;

describe('Testing sharedObject function', function () {

    describe('Initialization', function () {
        it('should show sign of live', function () {
            const result = sharedObject.toString();
            let expected = {'name': null, 'income': null}.toString();
            expect(result).to.be.equal(expected)
        });
    });

    describe('Keep initial state correct', function () {
        it('should return null for name', function () {
            expect(sharedObject.name).to.be.equal(null, 'Name was not equal to null');
        });
        it('should return null for income', function () {
            expect(sharedObject.income).to.be.equal(null, 'Income was not equal to null');
        });
    });
    describe('.changeName function', function () {
        it('should do nothing on empty string', function () {
            // Arrange
            let expected1 = null;
            let expected2 = 'Pesho';
            // Act
            sharedObject.changeName('');
            let actual1 = sharedObject.name;
            sharedObject.changeName('Pesho');
            sharedObject.changeName('');
            let actual2 = sharedObject.name;
            // Assert
            expect(actual1).to.be.equal(expected1, 'Name has been changed with incorrect input parameter ""');
            expect(actual2).to.be.equal(expected2, 'Name has been changed with incorrect input parameter ""')
        });
        it('should change the name of the object with input Gosho', function () {
            // Arrange
            let expected = 'Gosho';
            // Act
            sharedObject.changeName('Gosho');
            let actual = sharedObject.name;
            // Assert
            expect(actual).to.be.equal(expected, 'Name have not been changed with "Gosho" input parameter "Gosho"');
        });
    });
    describe('.changeName textBox tests', function () {
        it('should do nothing on empty string', function () {
            // Arrange
            let nameTxt = $('#name');
            let expected = 'Gosho';
            // Act
            sharedObject.changeName('');
            let actual = nameTxt.val();
            // Assert
            expect(actual).to.be.equal(expected, 'Name has been changed with incorrect input parameter ""');
        });
        it('should change the value of the box with correct input', function () {
            // Arrange
            let nameTxt = $('#name');
            let expected = 'Misho';
            // Act
            sharedObject.changeName('Misho');
            let actual = nameTxt.val();
            // Assert
            expect(actual).to.be.equal(expected, 'Name has been changed with incorrect input parameter ""');
        });
    });
    describe('.changeIncome function', function () {
        it('should do nothing on string input', function () {
            // Arrange
            let expected = null;
            // Act
            sharedObject.changeIncome('Gosho');
            let actual = sharedObject.income;
            // Assert
            expect(actual).to.be.equal(expected, 'Income have been changed with input parameter "Gosho"');
        });
        it('should do nothing if input is not integer', function () {
            // Arrange
            let expected = null;
            // Act
            sharedObject.changeIncome(expected);
            let actual = sharedObject.income;
            // Assert
            expect(actual).to.be.equal(expected, 'Income have been changed with input parameter 0');
        });
        it('should do nothing on 0 or negative value', function () {
            // Arrange
            let expected = 10;
            // Act
            sharedObject.changeIncome(expected);
            sharedObject.changeIncome(0);
            let actual = sharedObject.income;
            // Assert
            expect(actual).to.be.equal(expected, 'Income have been changed with input parameter 0');
        });
    });

});