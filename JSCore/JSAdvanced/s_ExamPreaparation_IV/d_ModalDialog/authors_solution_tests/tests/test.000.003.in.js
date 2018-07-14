// Verify HTML structure with one input
document.body.innerHTML = `
<div id="content">
    <p>Reference content</p>
</div>
`;

let Dialog = result;

let dialog = new Dialog('Dialog with input', () => {});
dialog.addInput('Name', 'name', 'text');
dialog.render();

let element = $('.overlay').find('.dialog');
expect(element.find('p').text()).to.contains('Dialog with input', "Dialog message not displayed");
let input = element.find('input');
expect(input.attr('name')).to.equal('name', "Input name attribute not set correctly");
expect(input.attr('type')).to.equal('text', "Input type attribute not set correctly");