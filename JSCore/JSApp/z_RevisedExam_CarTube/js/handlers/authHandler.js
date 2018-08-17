/**
 * The Handlers object contains different functions used as callbacks from Sammy get and post methods
 * Current file authHandlers.js contains handlers for user register, login, logout, not registered and registered
 * users home view
 *
 * ### Example for typical get route (where 'this' is sammy application context):
 *      handlers.getHomePage = function (ctx){
 *          if(auth.isAuth()){
 *              this.redirect(userHomePage);
 *              return;
 *          }
 *          this.loadPartials({partialName: 'partialPath'})
 *              .then(function() {
 *                  this.partial('partialPath')
 *              })
 *      }
 *
 *  ### Example for custom get route (with use of loadSection helper method:
 *      function (ctx) {
 *          // load standard partials and as a parameter the current view in layout
 *          ctx.loadSection(LOGIN_SECTION)
 *             .then(function () {
 *             // load the form with it method, action and custom properties for template
 *             let formParams = {};
 *             formParams.formAction = authRoutes.login;
 *             formParams.formMethod = 'post';
 *             formParams.registerLink = authRoutes.register;
 *             this.partial(LAYOUT, formParams)
 *         });
 */

/**
 * Callback function to get the welcome page for not logged in users
 * @param ctx - sammy event context
 */
handlers.getWelcomePage = function (ctx) {
    ctx.loadSection(WELCOME_SECTION)
        .then(function () {
            this.partial(LAYOUT)
        });
};

/**
 * Callback for load login view
 * @param ctx
 */
handlers.getLoginView = function (ctx) {
    // load standard partials
    ctx.loadSection(LOGIN_SECTION)
        .then(function () {
            // load the form with it method, action and custom properties for template
            let formParams = {};
            formParams.formAction = authRoutes.login;
            formParams.formMethod = 'post';
            formParams.registerLink = authRoutes.register;
            this.partial(LAYOUT, formParams)
        });
};

/**
 * Callback for load register view
 * @param ctx
 */
handlers.getRegisterView = function (ctx) {
    ctx.loadSection(REGISTER_SECTION)
        .then(function () {
            // prepare parameters to be passed to login partial
            let formParams = {};
            formParams.formAction = authRoutes.register;
            formParams.formMethod = 'post';
            formParams.loginLink = authRoutes.login;
            // load form with it method, action and custom property for the template
            this.partial(LAYOUT, formParams);
        })
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
                //auth.updateCollection([]);
                // Notify for the success
                notify.showInfo(msgs.register);
                // redirect to users home page
                ctx.redirect(customRoutes.all);
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
            notify.showInfo(msgs.login);
            // redirect to home page
            ctx.redirect(customRoutes.all);
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
        notify.showInfo(msgs.logout);
        // redirect to initial page (index.html) for not logged in users
        ctx.redirect(authRoutes.welcome);
    })
};