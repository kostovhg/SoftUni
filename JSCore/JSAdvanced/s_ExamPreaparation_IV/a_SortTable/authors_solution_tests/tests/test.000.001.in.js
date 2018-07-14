document.body.innerHTML = `
<table id="products">
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Potatoes</td>
        <td>0.89</td>
    </tr>
    <tr>
        <td>Tomatoes</td>
        <td>2.30</td>
    </tr>
    <tr>
        <td>Bananas</td>
        <td>1.79</td>
    </tr>
    </tbody>
</table>`;

expect(typeof result).to.equal('function', "You must submit a function");
expect(result.length).to.equal(2, 'Your function must take two arguments');

let attached = false;
HTMLElement.prototype.addEventListener = function () {
    attached = true;
};

try {
    result();
} catch (e) {
    throw new Error("Your function must do nothing when called without arguments");
}

expect(attached).to.equal(false, "You must not attach event listeners, your function will be called automatically");