let expect = require('chai').expect;
let jsdom = require('jsdom-global')();
let $ = require('jquery');

// let nuke = require('../f_ArmageDOM/armageDOM');
let nuke =  function (selector1, selector2){
    if(selector1 === selector2){
        return;
    }
    $(selector1).filter(selector2).remove();
};

describe('nuke function tests', function () {
    beforeEach('include HTML for testing', function () {
        document.body.innerHTML =
            `<div id="target">
                <div class="nested target">
                    <p>This is some text</p>
                </div>
                <div class="target">
                    <p>Empty div</p>
                 </div>
                <div class="inside">
                    <span class="nested">Some more text</span>
                    <span class="target">Some more text</span>
                </div>
            </div>`;
    });
    before(() => global.$=$);

    describe('invalid input', function () {
        it(`should do nothing with invalid 1st parameter`, function () {
            // console.log($('#target').html());
            let selector1 = 'invalidSelector';
            let selector2 = '#target';
            let initialState = $('body').html();
            nuke(selector1, selector2);
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing with invalid 2nd parameter`, function () {
            let selector1 = '#targeet';
            let selector2 = 'invalidSelector';
            let initialState = $('body').html();
            nuke(selector1, selector2);
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing with empty 1st parameter`, function () {
            let selector2 = '#target';
            let initialState = $('body').html();
            nuke('', selector2);
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing with empty 2nd parameter`, function () {
            let selector1 = '#target';
            let initialState = $('body').html();
            nuke(selector1, '');
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing with only one parameter`, function () {
            let selector1 = '#target';
            let initialState = $('body').html();
            nuke(selector1);
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing without parameters`, function () {
            let initialState = $('body').html();
            nuke();
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing with equal parameters`, function () {
            let initialState = $('body').html();
            nuke("#target", '#target');
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it(`should do nothing with not matching parameters`, function () {
            let initialState = $('body').html();
            nuke(".taget", '.nosted');
            let endState = $('body').html();
            expect(initialState).to.equal(endState);
        });
        it('should do nothing on nuke(2, #target)', function () {
            let selector = $('#target');
            let prev = selector.html();
            // console.log(prev);
            nuke('.nested', '.inside');
            expect(selector.html()).to.equal(prev);
        });
    });
    describe('Valid inputs', function () {
        it('should delete all nodes that match both selectors (.target .nested)', function () {
            let selector1 = '.target';
            let selector2 = '.nested';
            nuke(selector1, selector2);
            let match = $(selector1).filter(selector2);
            expect(match.length).to.equal(0);
        });
        it('should delete all nodes that match both selectors (.nested span)', function () {
            let selector1 = '.nested';
            let selector2 = 'span';
            nuke(selector1, selector2);
            // let match = $(selector1).filter(selector2);
            // expect(match.length).to.equal(0);
            expect(($(selector1 + selector2)).length).to.equal(0);
        });
        it(`should remove one span for ('.target', 'span')`, function () {
            let targetSelector = '.target';
            let spanSelector = 'span';
            let initialTargetLength = $(targetSelector).length;
            let initialSpanLength = $(spanSelector).length;
            let initialSpanTargetLength = $(targetSelector).filter(spanSelector).length;
            nuke(targetSelector, spanSelector);
            expect($(targetSelector).filter(spanSelector).length).to.be.equal(0);
            expect($(spanSelector).length).to.be.equal(initialSpanLength - initialSpanTargetLength);
            expect($(targetSelector).length).to.equal(initialTargetLength - initialSpanTargetLength);
        });
    });
});