let [validation, datLenAttr, errMsg] = [
    'data-validation', 'data-validation-length', 'data-validation-error-msg'
];

let [theForm, username, password, email, formatText, boldIt, tildIt, check] = [
    $('form'),
    $('#username'), $('#password'), $('#email'),
    $('#format-text input'), $('#bold-btn'), $('#italic-btn'),$(`input[type="checkbox"]`)
];

theForm.attr('action', '').attr('id', 'registration-form');

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

tildIt.on('click', () => {
    formatText.toggleClass('text-italic')
});

$.validate({
    modules: 'date, security'
});






