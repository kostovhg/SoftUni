let section = $('body section:first');
section.addClass('slider');
//console.log(section);

$(document).ready(function () {
    $('.multiple-items').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 3,
        dots: true, 
        arrows: true
    });
});
