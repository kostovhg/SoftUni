let loadCommon = function(that, addPartials, addParams) {
    let partials = {
        header: './templates/common/header.hbs',
        footer: './templates/common/footer.hbs',
    };
    if(addPartials) {
        for (let key of Object.keys(addPartials)) {
            partials[key] = addPartials[key];
        }
    }
    if(that) {
        that.params.loggedIn = sessionStorage.getItem('authtoken') !== null;
        that.username = sessionStorage.getItem('username');
        if (addParams) {
            for (let param of Object.keys(addParams)) {
                that.params[param] = addParams[param];
            }
        }
    } else {
        // I don't know
    }
    console.log(that);
    return that.loadPartials(partials);
};