// try{
//     throw new RangeError('invalid range.');
//     console.log('This will never execute');
//     console.log('This will never execute');
//     console.log('This will never execute');
//
// } catch(error){
//     console.log(error.stack)
// }
//
// console.log()
// console.log()
// console.log()
//
// function sortNumbs(arr) {
//     arr.sort((a,b) => a-b)
// }
//
// let array = [10, 3, 5, 2, -1];
// sortNumbs(array);
// if(JSON.stringify(array) === '[-1,2,3,5,10]') {
//     console.log('Test passed')
// } else {
//     console.log('Test failed')
// }
//
let expect = require('chai').expect;
let sum = require('./d_SumOfNumbers/sumOfNumbers');

describe("sum(arr)", function(){
    it("should return 3 for [1, 2]", function () {
        expect(sum([1,2]).to.be.equal(3))
    })
});