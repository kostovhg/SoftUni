function attachEvents(){
    $('a').on('click', (e) => {
        $('a').removeClass('selected');
        $(e.target).addClass('selected');
    })
}