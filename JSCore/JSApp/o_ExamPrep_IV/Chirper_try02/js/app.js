/**
 * A object which properties (functions) will be filled according the project needs
 * @type {{}} - properties as functions
 */
let handlers = {};

$(() => {
    let app = Sammy('#main', function () {

        // We are going to use sammy.handlebars.js plugin
        this.use('Handlebars', 'hbs');

        // A fixed variable that points to the context here.
        let appCtx = this;

        /* Routes redirection */
        // User authentications authRoutes
        appCtx.get(authRoutes.index, handlers.getLoginForm); // get main page without authentication
        appCtx.get(authRoutes.logout, handlers.logoutUser); // get login form
        appCtx.post(authRoutes.login, handlers.loginUser); // post login form
        appCtx.get(authRoutes.register, handlers.getRegisterView); // get register form
        appCtx.post(authRoutes.register, handlers.registerUser); // post register form
        appCtx.get(authRoutes.home, handlers.goHome); // get home page

        // Entities authRoutes
        // TODO: rearrange custom paths and handler functions

    });

    // Config is done, run this miracle
    app.run();
});