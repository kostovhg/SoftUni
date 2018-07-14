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

result(0, true);

let table = $('table');
let head = table.find('thead').find('tr').find('th').text();

expect(head).to.contains('Name', "Header structure has been broken");
expect(head).to.contains('Price', "Header structure has been broken");

let body = table.find('tbody');
expect(body.length).to.equal(1, "Table body (<tbody>) not found, make sure you're not overriding the structure!");
let rows = body.find('tr');
let expected = [
    ['Tomatoes', '2.30'],
    ['Potatoes', '0.89'],
    ['Bananas', '1.79']
];
expect(rows.length).to.equal(expected.length, "Incorrect number of rows inside table body (<tbody>)");
checkOrder(rows);

function checkOrder(rows) {
    for (let i = 0; i < rows.length; i++) {
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][0], "Incorrect order of elements");
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][1], "Incorrect order of elements");
    }
}