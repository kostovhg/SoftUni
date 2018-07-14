// Callback with one input
document.body.innerHTML = `
<div id="content">
    <p>Reference content</p>
</div>
`;

let Dialog = result;

let called = false;
let callback = (params) => {
    called = params;
};

let dialog = new Dialog('Test Message #', callback);
dialog.addInput('Input', 'field', 'text');
// Check before rendering
let body = $('body');
expect(body.text().trim()).to.equal('Reference content', "Content must only be added when render() is called");

dialog.render();
expect(body.text()).to.contains('Reference content', "Existing page content was altered");
let element = body.children().eq(1).find('.dialog');
expect(element.find('p').text()).to.contains('Test Message #', "Dialog message not displayed");
element.find('input').val('Vasil');
element.find('button').eq(0).trigger('click');
expect(called).to.not.equal(false, "Callback not executed");
expect(called.hasOwnProperty('field')).to.equal(true, "Callback parameter must be an object");
expect(called.field).to.equal('Vasil', "Callback parameter not correct");
expect($('.overlay').length).to.equal(0, "Overlay must be closed on button click");