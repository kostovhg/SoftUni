let expect = require('chai').expect;

function createCalculator() {
    let value = 0;
    return {
        add: function (num) {
            value += Number(num);
        },
        subtract: function (num) {
            value -= Number(num)
        },
        get: function () {
            return value;
        }
    }
}


describe('Create calculator tests', function () {
    let calc;
    beforeEach(function(){
        calc = createCalculator();
    });

    describe('Add', function () {
        it('Should return 0 after initialization', function () {
            // Arrange
            // Act
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(0)
        });
        it('Should return 5 after add 5', function () {
            // Arrange
            // Act
            calc.add(5);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(5)
        })
        it('Should return 11 after add(5.5), add(5.5)', function () {
            // Arrange
            // Act
            calc.add(5.5);
            calc.add(5.5);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(11)
        })
        it('Should return 11 after add("5.5"), add("5.5")', function () {
            // Arrange
            // Act
            calc.add('5.5');
            calc.add('5.5');
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(11)
        })
    })
    describe('Subtract', function () {
        it('Should return 5 after subtract 5', function () {
            // Arrange
            // Act
            calc.subtract(5);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(-5)
        })
        it('Should return 11 after subtract(5.5), subtract(5.5)', function () {
            // Arrange
            // Act
            calc.subtract(5.5);
            calc.subtract(5.5);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(-11)
        })
        it('Should return 11 after subtract("5.5"), subtract("5.5")', function () {
            // Arrange
            // Act
            calc.subtract('5.5');
            calc.subtract('5.5');
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(-11)
        })
        describe('Add in combination with Substract', function () {
            it('Should return 10 after adding 2x10 and subtract 10 ', function () {
                // Act
                calc.add(10);
                calc.add(10);
                calc.subtract(10);
                let result = calc.get();
                // Asert
                expect(result).to.be.equal(10);
            })
        })
    })
});
