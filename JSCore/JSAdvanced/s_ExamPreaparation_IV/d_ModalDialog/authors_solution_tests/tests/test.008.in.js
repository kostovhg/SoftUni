// Callback with decoy inputs and buttons
document.body.innerHTML = `
<div id="content">
    <p>Reference content</p>
    <div class="dialog">
        <input name="fieldA" type="text" value="Decoy">
        <button>OK</button>
    </div>
</div>
`;

let Dialog = result;

let called = false;
let callback = (params) => {
    called = params;
};

let dialog = new Dialog('Test Message #', callback);
dialog.addInput('Input1', 'fieldA', 'text');
dialog.addInput('Input2', 'fieldB', 'text');
dialog.addInput('Secret', 'secret', 'hidden');
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
element.find('input[name="fieldA"]').val('Some Value');
element.find('input[name="fieldB"]').val('Other Value');
element.find('input[name="secret"]').val('Hidden Value');
element.find('button').eq(0).trigger('click');
expect(called).to.not.equal(false, "Callback not executed");
expect(called.hasOwnProperty('fieldA')).to.equal(true, "Callback parameter missing fieldA property");
expect(called.hasOwnProperty('fieldB')).to.equal(true, "Callback parameter missing fieldB property");
expect(called.hasOwnProperty('secret')).to.equal(true, "Callback parameter missing pass property");
expect(called.fieldA).to.equal('Some Value', "Callback parameter fieldA not correct");
expect(called.fieldB).to.equal('Other Value', "Callback parameter fieldA not correct");
expect(called.secret).to.equal('Hidden Value', "Callback parameter secret not correct");
expect($('.overlay').length).to.equal(0, "Overlay must be closed on button click");