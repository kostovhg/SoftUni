
// import { expect } from 'chai';
// import { sharedObject } from '../e_SharedObject/sharedObject';

let path = require('path');
let fs = require('fs');
var bodyContent = fs.readFileSync(path.join(__dirname, '../', 'e_SharedObject/shared-object.html'));

const {JSDOM} = require('jsdom');
const jsdom = new JSDOM(bodyContent);

const {window} = jsdom;
const {document} = window;

const $ = global.jQuery = require('jquery')(window);

global.window = window;
global.document = document;

let expect = require('chai').expect;
let sharedObject = require('../e_SharedObject/sharedObject.js');

describe('Testing sharedObject function', function () {
    describe('Initialization', function () {
        it('should show sign of live', function () {
            const result = sharedObject.toString();
            let expected = {'name': null, 'income': null}.toString();
            expect(result).to.be.equal(expected)
        });
    });

    describe('Keep initial state correct', function () {
        it('should return null for name and income', function () {
            expect(sharedObject.name).to.be.equal(null, 'Name was not equal to null');
            expect(sharedObject.income).to.be.equal(null, 'Income was not equal to null');
        })
    });
    describe('changeName function', function () {
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
            expect(actual1).to.be.equal(expected1, 'Name has been changed with incorrect input parameter ""')
            expect(actual2).to.be.equal(expected2, 'Name has been changed with incorrect input parameter ""')
        });
    });
});