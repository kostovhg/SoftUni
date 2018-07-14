// Verify class properties and functions names
document.body.innerHTML = `
<div id="content">
    <p>Reference content</p>
</div>
`;

let Dialog = result;

expect(typeof Dialog).to.equal('function', "You must submit a class");

let dialog;
expect(() => dialog = new Dialog('Message', () => {}), "Instance creation failed, make sure you have submitted a class").to.not.throw();

expect(typeof dialog.addInput).to.equal('function', "Function 'addInput' not found");
expect(dialog.addInput.length).to.equal(3, "Function 'addInput' must take three arguments");
expect(typeof dialog.render).to.equal('function', "Function 'render' not found");
expect(dialog.render.length).to.equal(0, "Function 'render' must not take arguments");

expect($('body').text().trim()).to.equal('Reference content', "Content must only be added when render() is called");