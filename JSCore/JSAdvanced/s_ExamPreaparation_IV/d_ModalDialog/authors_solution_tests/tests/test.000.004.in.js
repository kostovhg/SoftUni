// Verify callback parameters
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

let dialog = new Dialog('Callback will be tested', callback);
dialog.addInput('Name', 'name', 'text');
dialog.render();

let element = $('.overlay').find('.dialog');
expect(element.find('p').text()).to.contains('Callback will be tested', "Dialog message not displayed");
element.find('input').val('Pesho');
element.find('button').eq(0).trigger('click');
expect(called).to.not.equal(false, "Callback not executed");
expect(called.hasOwnProperty('name')).to.equal(true, "Callback parameter must be an object");
expect(called.name).to.equal('Pesho', "Callback parameter not correct");
expect($('.overlay').length).to.equal(0, "Overlay must be closed on button click");