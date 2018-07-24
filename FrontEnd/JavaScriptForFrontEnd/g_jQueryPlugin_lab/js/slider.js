$('.slider').slick({
    accessibility: true,
    arrows:true,
    appendArrows: $('.slider-nav')
});

$('.john').click(function (e) {
    e.preventDefault();

    let a = false;

    let timer = setInterval(function () {
        if(a) {
            $('body').css('background-color', 'red');
            a = false;
        } else {
            $('body').css('background-color', 'yellow');
            a = true;
        }
    }, 200);
});