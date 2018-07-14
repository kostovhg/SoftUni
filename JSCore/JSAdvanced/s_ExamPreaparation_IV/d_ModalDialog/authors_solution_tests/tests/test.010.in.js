// Spawn two instances and callback
document.body.innerHTML = `
<div id="reference">
    <p>Reference content</p>
</div>
`;

let Dialog = result;
let p1 = false;
let p2 = false;
let cb1 = (params) => {
    p1 = params;
};
let cb2 = (params) => {
    p2 = params;
};
let dialog1 = new Dialog('Test Message #1', cb1);
dialog1.addInput('Input1', 'i1', 'text');
let dialog2 = new Dialog('Test Message #2', cb2);
dialog2.addInput('Input2', 'i2', 'text');

dialog1.render();
expect($('.dialog').length).to.equal(1, "Dialog not appended");
expect($('.dialog').text()).to.contains("Test Message #1", "Dialog message not displayed");
dialog2.render();
expect($('.dialog').length).to.equal(2, "Second Dialog not appended");
expect($('.dialog').text()).to.contains("Test Message #2", "Dialog message not displayed");

// Assign elements
let d1, d2;
$('.dialog').each((i, e) => {
    if ($(e).find('p').text().includes('1')) d1 = $(e);
    else if ($(e).find('p').text().includes('2')) d2 = $(e);
});
// Check first callback
d1.find('input').val('pesho');
d1.find('button').each((i, e) => {
    if (e.textContent === 'OK') $(e).trigger('click');
});
expect($('.dialog').length).to.equal(1, "First dialog not closed correctly");
expect(p1).to.not.equal(false, "Callback 1 not executed");
expect(p1.i1).to.equal('pesho', "Callback 1 parameter not correct");
// Check second callback
d2.find('input').val('gosho');
d2.find('button').each((i, e) => {
    if (e.textContent === 'OK') $(e).trigger('click');
});
expect($('.dialog').length).to.equal(0, "Second dialog not closed correctly");
expect(p2).to.not.equal(false, "Callback 2 not executed");
expect(p2.i2).to.equal('gosho', "Callback 2 parameter not correct");