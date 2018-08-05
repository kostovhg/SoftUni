let accountController = (() => {

    function loadLoginPage(ctx) {
        loadCommon(this, {loginForm: './templates/login/loginForm.hbs'})
            .then(function () {
                ctx.params.loggedIn = auth.isLoggedin();
                this.partial('./templates/login/loginPage.hbs', ctx.params);
            })
    }


    function sendLoginPage(ctx) {
        auth.login(
            ctx.params.username,
            ctx.params.password
        ).then((res) => {
            auth.showInfo('You have login successfully.');
            auth.saveSession(res);
            this.redirect('#/home');
        }).catch((err) => {
            auth.showError(err.responseJSON.description);
            this.redirect('#/login');
        })
    }

    function loadRegisterPage(ctx) {
        loadCommon(this)
            .then(function () {
                ctx.params.loggedIn = auth.isLoggedin();
                this.partial('./templates/register/registerPage.hbs', ctx.params);
            });
    }

    function sendRegisterPage(ctx) {
        let pass = ctx.params.password;
        if (ctx.params.repeatPassword === pass) {
            auth.register(ctx.params.username, pass)
                .then((res) => {
                    auth.saveSession(res);
                    auth.showInfo('You have registered successfully.');
                    this.redirect('#/home')
                })
                .catch((err) => {
                    auth.showError(err.responseJSON.description);
                    this.redirect('#/register')
                });
        } else {
            auth.showError('Passwords does not match!')
        }
    }

    function sendLogout(ctx) {
        let $data = ctx;
        auth.logout().then(function(result) {
            sessionStorage.clear();
            homeController.loadHomePage(ctx)
        }).catch((err) => {
            auth.showError(err.messageJSON.description);
        })
    }

    return {
        loadLoginPage,
        sendLoginPage,
        loadRegisterPage,
        sendRegisterPage,
        sendLogout,
    }
})();
