function usernames(input) {

    let result = input.map(email => {
        let [name, domain] = email.split('@');
        return name + '.' + domain.split('.').map(c => c[0]).join('');
    });

    console.log(result.join(', '));
}

usernames(['peshoo@gmail.com', 'todor_43@mail.dir.bg', 'foo@bar.com']);