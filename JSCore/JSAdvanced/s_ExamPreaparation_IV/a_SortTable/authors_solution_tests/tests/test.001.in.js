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
        <td>B</td>
        <td>2</td>
    </tr>
        <tr>
        <td>A</td>
        <td>3</td>
    </tr>
    <tr>
        <td>C</td>
        <td>1</td>
    </tr>
    </tbody>
</table>`;

result(0, false);

let table = $('table');
let head = table.find('thead').find('tr').find('th').text();

expect(head).to.contains('Name', "Header structure has been broken");
expect(head).to.contains('Price', "Header structure has been broken");

let body = table.find('tbody');
expect(body.length).to.equal(1, "Table body (<tbody>) not found, make sure you're not overriding the structure!");
let rows = body.find('tr');
let expected = [
    ['A', '3'],
    ['B', '2'],
    ['C', '1']
];
expect(rows.length).to.equal(expected.length, "Incorrect number of rows inside table body (<tbody>)");
checkOrder(rows);

function checkOrder(rows) {
    for (let i = 0; i < rows.length; i++) {
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][0], "Incorrect order of elements");
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][1], "Incorrect order of elements");
    }
}