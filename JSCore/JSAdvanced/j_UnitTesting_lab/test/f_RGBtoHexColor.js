let expect = require('chai').expect;
let rgbToHexColor = require('../f_RGBtoHex/rgbToHexColor');

describe('Check rgbToHexColor converter tests', function () {
    describe('Valid inputs', function () {
        it('Should return #000000 for (0, 0, 0)', function () {
            expect(rgbToHexColor(0, 0, 0)).to.be.equal('#000000');
        });
        it('Should return #ffffff for (255, 255, 255)', function () {
            expect(rgbToHexColor(255, 255, 255)).to.be.equal('#FFFFFF');
        });
    });
    describe('Invalid inputs', function () {
        describe('Invalid arguments type', function () {
            it('Should be undefined for not integer arguments', function () {
                // Arrange
                let correct = 1;
                let wrongArgs = [
                    [], [125], [125, 125], // wrong count of arguments
                    ["1", 125, 125], [125, "1", 125], [125, 125, "1"], // wrong type of argument
                    [{red: 125}, 125, 125], [125, {blue: 125}, 125], [125, 125, {green: 125}], // wrong type of argument
                    [125.5, 125, 125], [125, 125.5, 125], [125, 125, 125.5], // wrong type of argument
                ];
                // Act
                for (let [r, b, g] of wrongArgs) {
                    correct &= rgbToHexColor(r, b, g) /*!== undefined*/;
                }
                // Assert
                expect(correct).to.be.equal(0);
            });
        });
        describe('Invalid red input', function () {
            it('Should return undefined for (-1, 125, 125)', function () {
                expect(rgbToHexColor(-1, 125, 125)).to.be.equal(undefined);
            });
            it('Should return undefined for ("red", 125, 125)', function () {
                expect(rgbToHexColor("red", 125, 125)).to.be.equal(undefined);
            });
            it('Should return undefined for (256, 125, 125)', function () {
                expect(rgbToHexColor(256, 125, 125)).to.be.equal(undefined);
            });
        });
        describe('Invalid blue input', function () {
            it('Should return undefined for (125, -1, 125)', function () {
                expect(rgbToHexColor(125, -1, 125)).to.be.equal(undefined);
            });
            it('Should return undefined for (125, "blue", 125)', function () {
                expect(rgbToHexColor(125, "blue", 125)).to.be.equal(undefined);
            });
            it('Should return undefined for (125, 256, 125)', function () {
                expect(rgbToHexColor(125, 256, 125)).to.be.equal(undefined);
            });
        });
        describe('Invalid green input', function () {
            it('Should return undefined for (125, 125, 125)', function () {
                expect(rgbToHexColor(-1, 125, 125)).to.be.equal(undefined);
            });
            it('Should return undefined for (125, 125)', function () {
                expect(rgbToHexColor("red", 125, 125)).to.be.equal(undefined);
            });
            it('Should return undefined for (125, 125)', function () {
                expect(rgbToHexColor(256, 125, 125)).to.be.equal(undefined);
            });
        });
    })
});

/*
// Red value is invalid
    if (!Number.isInteger(green) || (green < 0) || (green > 255))
        return undefined; // Green value is invalid
    if (!Number.isInteger(blue) || (blue < 0) || (blue > 255))
        return undefined; // Blue value is invalid
 */