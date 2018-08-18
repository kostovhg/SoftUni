handlers.getWelcomePage = function (ctx) {

    ctx.loadPartials({
        loginForm: './templates/forms/loginForm.hbs',
        registerForm: './templates/forms/registerForm.hbs',
    }).then(function () {
        this.partial('./templates/welcomeSectionView.hbs')
    })
};

handlers.registerUser = function (ctx) {
    const username = ctx.params.username;
    const password = ctx.params.password;
    const repeatPassword = ctx.params.passwordCheck;

    if (username.length < 5) {
        notify.showError('Username must be at least 5 symbols long!')
    } else if (password.length === 0) {
        notify.showError('Password must not be empty!')
    } else if (password !== repeatPassword) {
        notify.showError('Password must match!')
    } else {
        auth.register(username, password)
            .then((response) => {
                auth.saveSession(response);
                notify.showInfo('User registration successful.');
                ctx.redirect('#/editor');
            }).catch(notify.handleError);
    }
};