/**
 * Object that keeps sammy routing callback functions
 * @type {{}} - configured in several files (./js/handlers/{authHandler,entityHandler}.js)
 */
let handlers = {};

/**
 * Sammy configuration
 */
$(() => {
    let app = Sammy('#container', function () {

        // We are going to use sammy.handlebars.js plugin
        this.use('Handlebars', 'hbs');

        // A fixed variable that points to the context here.
        let appCtx = this;

        // Executing before each route proceeding
        this.before({except: {path: '#/login'}}, function () {
            let selected_nav_id = nav_sections[this.path.split('/').pop()]; // get the end of the path and return nav_section value for it
            this[selected_nav_id] = true; // and use nav_section object to return nav a id to mark as selected
            // attach to Sammy.EventContext.params frequently used variables, that will be evaluated in many handlebars
            // templates
            this.isLoggedin = sessionStorage.getItem('authtoken') !== null;
            if (!this.isLoggedin) {
                this.redirect(authRoutes.welcome);
            } else {
                this.username = sessionStorage.getItem('username');
                this.authtoken = sessionStorage.getItem('authtoken');
            }
        });
        // Custom function to load common sections
        appCtx.helpers({
            /**
             * Helper function that returns Sammy.RenderContext
             * @param section_view - partial to be loaded in layout
             * @param additional_views - if there is sub partials or other views, they should be given as object
             * @returns {*} - render context with loaded common partials (header, footer, navigation, etc.)
             */
            loadSection: function (section_view, additional_views) {
                let partials = {NAVIGATION_SECTION, FOOTER_SECTION, CONTENT_SECTION: section_view};
                if (additional_views) {
                    for (let key of Object.keys(additional_views)) {
                        partials[key] = additional_views[key];
                    }
                }
                return this.loadPartials(partials)
            }
        });


        /* Routes redirection */
        appCtx.get(authRoutes.index, handlers.getWelcomePage);
        // get main page for all users
        appCtx.get(authRoutes.welcome, handlers.getWelcomePage);
        // get view after logout
        appCtx.get(authRoutes.logout, handlers.logout);
        // get login view
        appCtx.get(authRoutes.login, handlers.getLoginView);
        // send login info from form to server
        appCtx.post(authRoutes.login, handlers.loginUser);
        // get register view
        appCtx.get(authRoutes.register, handlers.getRegisterView);
        // send register data from register form to server
        appCtx.post(authRoutes.register, handlers.registerUser);

        // Entities authRoutes
        appCtx.get(customRoutes.all, handlers.getAllView);
        appCtx.get(customRoutes.my, handlers.getMyView);
        appCtx.get(customRoutes.create, handlers.getCreateView);
        appCtx.post(customRoutes.create, handlers.create);
        appCtx.get(customRoutes.details, handlers.viewDetails);
        appCtx.get(customRoutes.edit, handlers.getEditView);
        appCtx.post(customRoutes.edit, handlers.edit);
        appCtx.get(customRoutes.delete, handlers.delete);

    });

    // Config is done, run this miracle
    app.run();
});