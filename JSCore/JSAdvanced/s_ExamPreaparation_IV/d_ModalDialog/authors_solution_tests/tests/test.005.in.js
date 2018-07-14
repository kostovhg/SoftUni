// Cancel with decoy inputs and buttons
document.body.innerHTML = `
<div id="reference">
    <p>Reference content</p>
    <div class="dialog">
        <button>Cancel</button>
    </div>
</div>
`;

let Dialog = result;
let callback = () => {
    throw new Error("Callback must not be called when cancelling");
};
let dialog = new Dialog('Test Message #', callback);

// Check before rendering
let body = $('body');
expect(body.children().length).to.equal(1, "Content must only be added when render() is called");

dialog.render();
expect(body.text()).to.contains('Reference content', "Existing page content was altered");
let element = body.children().eq(1).find('.dialog');
expect(element.length).to.equal(1, "Dialog not appended");
// Decoy click
body.find('#reference').find('.dialog').find('button').trigger('click');
expect($('.overlay').length).to.equal(1, "Overlay was closed on wrong button");
// Real click
element.find('button').each((i, e) => {
    if (e.textContent === 'Cancel') $(e).trigger('click')
});
expect($('.overlay').length).to.equal(0, "Overlay must be closed on Cancel");