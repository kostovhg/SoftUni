// Spawn two instances and cancel
document.body.innerHTML = `
<div id="reference">
    <p>Reference content</p>
</div>
`;

let Dialog = result;
let dialog1 = new Dialog('Test Message #1', () => {});
let dialog2 = new Dialog('Test Message #2', () => {});

dialog1.render();
expect($('.dialog').length).to.equal(1, "Dialog not appended");
expect($('.dialog').text()).to.contains("Test Message #1", "Dialog message not displayed");
dialog2.render();
expect($('.dialog').length).to.equal(2, "Second Dialog not appended");
expect($('.dialog').text()).to.contains("Test Message #2", "Dialog message not displayed");

let d1 = $('.dialog').eq(0);
let d2 = $('.dialog').eq(1);
d1.find('button').each((i, e) => {
    if (e.textContent === 'Cancel') $(e).trigger('click');
});
expect($('.dialog').length).to.equal(1, "First dialog not closed correctly");
d2.find('button').each((i, e) => {
    if (e.textContent === 'Cancel') $(e).trigger('click');
});
expect($('.dialog').length).to.equal(0, "Second dialog not closed correctly");