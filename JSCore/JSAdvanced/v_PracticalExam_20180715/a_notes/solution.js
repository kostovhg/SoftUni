/* Stiker format

<li class="note-content">
    <a class="button>x</a>
    <h2>TODO</h2>
    <hr>
    <p>some text</p>
</li>
*/

function addSticker() {
    let titleInput = $('.title');
    let contentField = $('.content');
    console.log($('.stickerBoard'));

    if (titleInput.val() !== '' || contentField.val() !== '') {
        $('#sticker-list')
            .append($('<li class="note-content">')
                .append($('<a class="button">')
                    .text('x')
                   // .on('click', (e) => $(e.target).parent().fadeOut())
                    //.on('click', (e) => $(e.target).parent().css('display', 'none'))
                    .on('click', (e) => $(e.target).parent().remove())
                    .append($('<h2>').text(titleInput.val()))
                    .append($('<hr>'))
                    .append($('<p>').text(contentField.val()))
                )
            );

        titleInput.val('');
        contentField.val('');
    }
}