function simpleEmailValidation(input) {

    let pattern = /^[a-zA-Z0-9]+@[a-z]+\.[a-z]+$/;
    console.log(pattern.test(input) ? 'Valid' : 'Invalid')
}

simpleEmailValidation('valid@email.bg');
simpleEmailValidation('invalid@emai1.bg');
