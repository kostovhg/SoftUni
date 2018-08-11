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
        appCtx.get(authRoutes.index, handlers.getLoginView); // get main page without authentication
        appCtx.get(authRoutes.logout, handlers.logout); // get login form
        appCtx.post(authRoutes.login, handlers.loginUser); // post login form
        appCtx.get(authRoutes.register, handlers.getRegisterView); // get register form
        appCtx.post(authRoutes.register, handlers.registerUser); // post register form
        appCtx.get(authRoutes.home, handlers.goHome); // get home page

        // Entities authRoutes
        // TODO: rearrange custom paths and handler functions
        appCtx.get(customRoutes.createEntity, handlers.getActiveEntity);
        appCtx.post(customRoutes.createEntity, handlers.checkoutEntry);
        appCtx.get(customRoutes.deleteEntity, handlers.deleteEntry);
        appCtx.post(customRoutes.entryDetails, handlers.createEntry);

        // overviewEntities
        appCtx.get(customRoutes.overviewEntities, handlers.getMyEntities);
        appCtx.get(customRoutes.entityDetails, handlers.getEntityDetails);

    });

    // Config is done, run this miracle
    app.run();
});