/**
 * Callback function to get the welcome page for not logged in users
 * @param ctx - sammy event context
 */
handlers.getWelcomePage = function (ctx) {
    // check for any case if the user is logged in
    if (auth.isAuth()) {
        // home page is for logged in users
        ctx.redirect(authRoutes.home);
    } else {
        // if not logged in, load necessary partials
        ctx.loadPartials({headerSection, footerSection})
            // then swap selector content with proper view
            .then(function () {
                this.partial(loginForm)
            });
    }
};

/**
 * Callback for load login view
 * @param ctx
 */
handlers.getLoginView = function (ctx) {
    if (auth.isAuth()) {
        ctx.redirect(authRoutes.home);
    } else {
        // load standard partials
        ctx.loadPartials({footerSection, headerSection,})
            .then(function () {
                // load the form with it method, action and custom properties for template
                this.partial(loginForm, {
                    formAction: authRoutes.login,
                    formMethod: 'post',
                    registerLink: authRoutes.register,
                })
            })
    }
};

/**
 * Callback for load register view
 * @param ctx
 */
handlers.getRegisterView = function (ctx) {
    if (auth.isAuth()) {
        ctx.redirect(authRoutes.home);
    } else {
        ctx.loadPartials({footerSection, headerSection,})
            .then(function () {
                // load form with it method, action and custom property for the template
                this.partial(registerForm, {
                    formAction: authRoutes.register,
                    formMethod: 'post',
                    registerLink: authRoutes.login,
                })
            })
    }
};

/**
 * Callback for registering the user
 * @param ctx
 */
handlers.registerUser = function (ctx) {
    // Get ctx.params (form data)
    const username = ctx.params.username;
    const password = ctx.params.password;
    const repeatPassword = ctx.params.repeatPass;

    // validate form data
    if (username.length < 5) {
        notify.showError('Username must be at least 5 symbols long!')
    } else if (password.length === 0) {
        notify.showError('Password must not be empty!')
    } else if (password !== repeatPassword) {
        notify.showError('Password must match!')
    } else {
        // if all fields are valid, call auth (authService.js) function register
        auth.register(username, password)
            .then((response) => {
                // save session
                auth.saveSession(response);
                // save additional parameters if any
                auth.updateCollection([]);
                // Notify for the success
                notify.showInfo('User registration successful.');
                // redirect to users home page
                ctx.redirect(authRoutes.home);
            }).catch(notify.handleError);
    }
};

/**
 * Callback for logging in a user
 * @param ctx
 */
handlers.loginUser = function (ctx) {
    // get ctx.params (forms fields)
    const username = ctx.params.username;
    const password = ctx.params.password;
    // validate them
    if (username.length === 0) {
        notify.showError('Username is required!')
    } else if (username.length === 0) {
        notify.showError('Password is required!')
    }
    // call auth (authService.js)
    auth.login(username, password)
        .then((response) => {
            // if server returns successful response
            // save session
            auth.saveSession(response); // save additional params in auth.saveSession
            // Notify for the success
            notify.showInfo('Login successful.');
            // redirect to home page
            ctx.redirect(authRoutes.home);
        }).catch(notify.handleError);
};

/**
 * Callback for log out user
 * @param ctx
 */
handlers.logout = function (ctx) {
    // Call auth (authService.js) method logout
    auth.logout().then(() => {
        // If successful clear session storage
        sessionStorage.clear();
        // Notify for the success
        notify.showInfo('Logout successful.');
        // redirect to initial page (index.html) for not logged in users
        ctx.redirect(authRoutes.index);
    })
};

/**
 * Callback for load home template for logged in users
 * @param ctx
 */
handlers.goHome = function (ctx) {
    if (!auth.isAuth()) {
        ctx.redirect(welcomeSection);
    } else {
        // load all needed partials
        ctx.loadPartials({headerSection, navigation, footerSection, submitChirpForm})
            .then(function () {
                // load and swap selector content with home template
                this.partial(homeSection)
            });
    }
};