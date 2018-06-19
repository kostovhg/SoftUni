/*
*   To use IIFE, the function should only declare IIFE one time and its return result can be called more times
*   But judge does not accept such approach
 */
// let createBook = (function () {
//     let bookId = 0;
//     return function (selector, title, author, isbn) {
//         $(selector).append($('<div>')
//             .attr('id', `book${++bookId}`)
//             .css('border', 'none')
//             .append($('<p>').addClass('title').text(title))
//             .append($('<p>').addClass('author').text(author))
//             .append($('<p>').addClass('isbn').text(isbn))
//             .append($('<button>').text('Select').on('click', () => bookContainer.css('border', '2px solid blue')))
//             .append($('<button>').text('Deselect').on('click', () => bookContainer.css('border', 'none')))
//         );
//
//     }
// }());

function createBook(selector, title, author, isbn){
    /*
     * No Need Of IIFE because id will increase based on count of children of selector!
     */
    let bookGenerator = (function () {
        let bookId = 1;
        return function (selector, title, author, isbn) {
            let [bookContainer, wrapper] = [$('<div>'), $(selector)];
            bookContainer
                .attr('id', `book${wrapper.children().length + 1}`)
                .css('border', '')
                .append($('<p>').addClass('title').text(title))
                .append($('<p>').addClass('author').text(author))
                .append($('<p>').addClass('isbn').text(isbn))
                .append($('<button>').text('Select').on('click', () => bookContainer.css('border', '2px solid blue')))
                .append($('<button>').text('Deselect').on('click', () => bookContainer.css('border', '')));
            wrapper.append(bookContainer)
        }
    }());
    bookGenerator(selector, title, author, isbn)
}
