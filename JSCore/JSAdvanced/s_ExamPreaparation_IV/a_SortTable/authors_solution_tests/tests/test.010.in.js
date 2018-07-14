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
        <td>11111</td>
    </tr>
        <tr>
        <td>p-C</td>
        <td>21</td>
    </tr>
    <tr>
        <td>p-F</td>
        <td>311</td>
    </tr>
    <tr>
        <td>p-B</td>
        <td>4</td>
    </tr>
    <tr>
        <td>p-R</td>
        <td>5111</td>
    </tr>
    <tr>
        <td>p-L</td>
        <td>61</td>
    </tr>
    <tr>
        <td>p-D</td>
        <td>7111111</td>
    </tr>
    </tbody>
</table>`;

result(1, true);

let table = $('table');
let head = table.find('thead').find('tr').find('th').text();

expect(head).to.contains('Name', "Header structure has been broken");
expect(head).to.contains('Price', "Header structure has been broken");

let body = table.find('tbody');
expect(body.length).to.equal(1, "Table body (<tbody>) not found, make sure you're not overriding the structure!");
let rows = body.find('tr');
let expected = [
    ['p-D', '7111111'],
    ['p-A', '11111'],
    ['p-R', '5111'],
    ['p-F', '311'],
    ['p-L', '61'],
    ['p-C', '21'],
    ['p-B', '4']
];
expect(rows.length).to.equal(expected.length, "Incorrect number of rows inside table body (<tbody>)");
checkOrder(rows);

function checkOrder(rows) {
    for (let i = 0; i < rows.length; i++) {
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][0], "Incorrect order of elements");
        expect(rows.eq(i).find('td').text()).to.contains(expected[i][1], "Incorrect order of elements");
    }
}