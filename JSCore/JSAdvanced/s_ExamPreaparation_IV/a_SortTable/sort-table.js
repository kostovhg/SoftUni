/**
 * Sorting function
 *
 * Solution from {@link https://stackoverflow.com/a/49041392/6504352} by Nick Grealy
 * @author Nick Grealy
 * @param {number} colIndex - common index for all cells {@link sort-getCellValue-col}
 * @param {boolean} descending - defines the sorting direction
 */

function sort(colIndex, descending) {


    /**
     * Variable that holds body of the table with rows that should be sorted
     * {DOMElement}
     */
    let tbody = $('#products tbody');

    /**
     * Solution from {@link https://stackoverflow.com/a/49041392/6504352} by Nick Grealy
     * @author Nick Grealy
     */

    /**
     * Function that accepts indexes of row and col returns the value of <th>
     * @param {number} col - a index number of table data (nth-child) in <tr> row
     * @param {number} row - a index number of table row
     * @returns {string} - the content of the cell
     */
    function getCellValue(row, col) { return row.children[col].innerText || row.children[col].textContent};

    /**
     * Function that compare two strings or two numbers
     * @param {number} col - common column index for all values
     * @param {boolean} desc - used to describe the order (descending for true, ascending for false) for values sorting
     * @returns {function} - sorting function
     */
    let compare = (col, desc) =>
        /**
         * Sorting function with two parameters for {@link @a} and {@link @b} as arguments
         * @param {textContent} a - the content of the first cell
         * @param {textContent} b - the content of the second cell
         * @returns {function} - comparison between a and b that returns number;
         */
            (a, b) =>
            /**
         * Function that compare two values
         * @param {textContent} v1 - first argument, that will be the content of the second cell (if {@link desc} is true) or the opposite
         * @param {textContent} v2 - second argument, that will be the content of the first cell (if {@link desc} is true) or the opposite
         * @returns {number} - for empty arguments (0) or numbers returns {@link v1} - {@link v2}, and for strings arguments -1, 0 or 1
         */
            ((v1, v2) => v1 !== '' && v2 !== '' && !isNaN(v1) && !isNaN(v2) ?
                v1 - v2 :
                v1.toString().localeCompare(v2))
            (getCellValue(desc ? b : a, col), getCellValue(desc ? a : b, col));

    /**
     * Invoking function in variable 'compare' for rows converted to array,
     * with parameters {colIndex} and {descending}
     * and appending each of sorted rows to the table body (elements are live and move in the time of their attachment)
     * */
    [...tbody.find('tr')].sort(compare(colIndex, descending)).forEach(x => tbody.append(x))
}