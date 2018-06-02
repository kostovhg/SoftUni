function formFiller(username, email, phoneNumber, sentences) {

    let unamePh = /<![a-zA-Z]+!>/g;
    let emailPh = /<@[a-zA-Z]+@>/g;
    let pNumPh = /<\+[a-zA-Z]+\+>/g;

    for (let sentence of sentences) {
        console.log(
            sentence
                .replace(unamePh, username)
                .replace(emailPh, email)
                .replace(pNumPh, phoneNumber))
    }
}

formFiller('Pesho', 'pesho@softuni.bg', '90-60-90', [
    'Hello, <!username!>!',
    'Welcome to your Personal profile.',
    'Here you can modify your profile freely.',
    'Your current username is: <!fdsfs!>. Would you like to change that? (Y/N)',
    'Your current email is: <@DasEmail@>. Would you like to change that? (Y/N)',
    'Your current phone number is: <+number+>. Would you like to change that? (Y/N)'
]);