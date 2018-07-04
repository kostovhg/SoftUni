
let expect = require('chai').expect;
let isOddOrEven = require('../b_EvenOrOdd/evenOrOdd');

describe('Tests for isOddOrEven.js', function () {
    describe('Wrong input', function () {
        it('Should return undefined for not string arguments', function () {
            // Arrange
            let isUndefined = false;
            let expected = true;
            let inputs = [
                {aProperty: 'aValue'}, 5, 3.3, new Date(), true
            ];
            // Act
            inputs.forEach(i => isUndefined = isUndefined || !isOddOrEven(i));
            // Assert
            expect(isUndefined).to.be.equal(expected)
        })
    });
    describe('Correct inputs', function () {
        it('Should return even for "abcd"', function () {
            // Arrange
            let expected = 'even';
            let input = 'abcd';
            // Act
            let result = isOddOrEven(input);
            // Assert
            expect(expected).to.be.equal(result)
        }); it('Should return even for "abc"', function () {
            // Arrange
            let expected = 'odd';
            let input = 'abc';
            // Act
            let result = isOddOrEven(input);
            // Assert
            expect(expected).to.be.equal(result)
        });
        it('Should return even for ""', function () {
            // Arrange
            let expected = 'even';
            let input = '';
            // Act
            let result = isOddOrEven(input);
            // Assert
            expect(expected).to.be.equal(result)
        })

    });
});