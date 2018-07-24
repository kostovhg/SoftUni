let [validation, datLenAttr, errMsg] = [
    'data-validation', 'data-validation-length', 'data-validation-error-msg'
];

let [theForm, username, password, email, date, formatText, boldIt, tiltIt, check] = [
    $('form'),
    $('#username'), $('#password'), $('#email'), $('input[data-validation="date"]'),
    $('#format-text input'), $('#bold-btn'), $('#italic-btn'), $(`input[type="checkbox"]`)
];

theForm.attr('id', 'registration-form').on('submit', (e) => {

    $.ajax({
        type: 'post',
        url: 'index.html',
        data: JSON.stringify({
            username: username.val(),
            password: password.val(),
            email: email.val(),
            message: formatText.val(),
            date: date.val()
        })
    }).then((res) => {
        console.log('Everything is fine');
    }).catch((err) => {
        console.warn(err);
    });
    e.preventDefault();

});

username
    .attr(validation, 'alphanumeric length')
    .attr(datLenAttr, '3-12')
    .attr(errMsg, `The username should only contain 3-12 alphanumeric characters.`);

password
    .attr(validation, 'length')
    .attr(datLenAttr, '6-20')
    .attr(errMsg, 'The password should only contain 6-20 characters.');

email
    .attr(validation, 'email');

check
    .attr(validation, 'required')
    .attr(errMsg, `You have to agree to our terms`);

boldIt.on('click', () => {
    formatText.toggleClass('text-bold')
});

tiltIt.on('click', () => {
    formatText.toggleClass('text-italic')
});


$.validate({
    modules: 'date, security'
});






