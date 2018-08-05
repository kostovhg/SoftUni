const handlers = {};

$(() => {
    const app = Sammy('#main', function () {

        let appCtx = this;

        Handlebars.registerHelper("debug", function(optionalValue) {
            console.log("Current Context");
            console.log("====================");
            console.log(this);

            if (optionalValue) {
                console.log("Value");
                console.log("====================");
                console.log(optionalValue);
            }
        });

        appCtx.use('Handlebars', 'hbs');

        appCtx.get('#/home', homeController.loadHomePage);

        appCtx.get('#/about', homeController.loadAboutPage);

        appCtx.get('#/login', accountController.loadLoginPage);

        appCtx.post('#/login', accountController.sendLoginPage);

        appCtx.get('#/register', accountController.loadRegisterPage);

        appCtx.post('#/register', accountController.sendRegisterPage);

        appCtx.get('#/logout', accountController.sendLogout);

        appCtx.get('#/create', catalogController.loadCreate);

        appCtx.post('#/create', catalogController.sendCreate);

        appCtx.get('#/catalog', catalogController.loadCatalog);

        appCtx.get('#/catalog/:_id', catalogController.loadDetails);

    });

    app.run('#/home');
});