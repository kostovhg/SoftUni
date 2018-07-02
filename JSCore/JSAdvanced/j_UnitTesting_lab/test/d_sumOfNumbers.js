let expect = require('chai').expect;
let sum = require('../d_SumOfNumbers/sumOfNumbers');

// function sum(arr) {
//     let sum = 0;
//     for (let el of arr) {
//         sum += Number(el)
//     }
//
//     return sum;
// }

describe("Sum function test", function () {
    it("Should return 6 for [2,4]", function () {
        // Arrange
        let array = [2, 4];
        let expected = 6;

        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.equal(expected)
    });

    it("Should return -2 for [-2]", function () {
        // Arrange
        let arr = [-2];
        let expected = -2;
        // Act
        let result = sum(arr);
        // Assert
        expect(result).to.be.equal(expected);
    });

    it("Should return 0 for []", function () {
        // Arrange
        let arr = [];
        let expected = 0;
        // Act
        let result = sum(arr);
        // Assert
        expect(result).to.be.equal(expected);
    })
});