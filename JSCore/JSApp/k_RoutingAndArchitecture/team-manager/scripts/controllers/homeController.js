let homeController = (() => {

    function loadHomePage(ctx) {
        loadCommon(ctx)
            .then(function () {
                ctx.params.loggedIn = auth.isLoggedin();
                this.partial('./templates/home/home.hbs', ctx.params);
            });
    }

    function loadAboutPage(ctx) {
        loadCommon(ctx)
            .then(function () {
                ctx.params.loggedInd = auth.isLoggedin();
                this.partial('./templates/about/about.hbs', ctx.params);
            });
    }

    return {
        loadHomePage,
        loadAboutPage,
    }
})();
