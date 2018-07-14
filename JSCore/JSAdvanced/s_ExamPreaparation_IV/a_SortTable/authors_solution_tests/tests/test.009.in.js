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
        <td>p-A</td>
        <td>1</td>
    </tr>
        <tr>
        <td>p-C</td>
        <td>2</td>
    </tr>
    <tr>
        <td>p-F</td>
        <td>3</td>
    </tr>
    <tr>
        <td>p-B</td>
        <td>4</td>
    </tr>
    <tr>
        <td>p-R</td>
        <td>5</td>
    </tr>
    <tr>
        <td>p-L</td>
        <td>6</td>
    </tr>
    <tr>
        <td>p-D</td>
        <td>7</td>
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
    ['p-A', '1'],
    ['p-B', '4'],
    ['p-C', '2'],
    ['p-D', '7'],
    ['p-F', '3'],
    ['p-L', '6'],
    ['p-R', '5'],
];
expect(rows.length).to.equal(expected.length, "Incorrect number of rows inside table body (<tbody>)");
checkOrder(rows);

function checkOrder(rows) {
    for (let i = 0; i < rows.length; i++) {
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][0], "Incorrect order of elements");
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][1], "Incorrect order of elements");
    }
}