// Verify HTML structure without inputs
document.body.innerHTML = `
<div id="content">
    <p>Reference content</p>
</div>
`;

let Dialog = result;

let dialog = new Dialog('Message', () => {});
dialog.render();

let body = $('body');
expect(body.text()).to.contains('Reference content', "Existing page content was altered");
expect(body.children().length).to.equal(2, "Overlay must be appended directly to the document body");
let overlay = body.children().eq(1);
expect(overlay.length).to.equal(1, "Overlay not appended");
expect(overlay[0]).to.equal($('.overlay')[0], "Overlay must be first element in document body");
let element = overlay.find('.dialog');
expect(element.find('p').text()).to.contains('Message', "Dialog message not displayed");
let buttons = element.find('button');
expect(buttons.eq(0).text()).to.equal('OK', "First button must be OK");
expect(buttons.eq(1).text()).to.equal('Cancel', "Second button must be Cancel");