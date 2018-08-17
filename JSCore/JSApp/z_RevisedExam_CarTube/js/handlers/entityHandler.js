handlers.getCreateView = function (ctx) {
    this.loadSection(CREATE_FORM).then(function () {
        this.partial(LAYOUT, {formAction: customRoutes.create});
    })
};

handlers.viewDetails = function (ctx) {
    let entityId = ctx.params.entityId;
    entityService.getEntityDetails(entityId)
        .then(function (res) {
            ctx.loadSection(DETAILS)
                .then(function () {
                    let data = res;
                    data['isSeller'] = res._acl.creator === sessionStorage.getItem('userId');
                    this.partial(LAYOUT, data)
                })
        }).catch(notify.handleError)
};

/**
 * Create a entry (project sub collection record) vary from the project logic
 * @param ctx - EventContext from sammy
 */
handlers.create = function (ctx) {
    // Export ctx params to variables
    let data = {};
    data.title = ctx.params.title;
    data.description = ctx.params.description.trim();
    data.brand = ctx.params.brand.trim();
    data.fuel = ctx.params.fuelType.trim();
    data.model = ctx.params.model.trim();
    data.year = ctx.params.year.trim();
    data.price = ctx.params.price.trim();
    data.imageUrl = ctx.params.imageUrl.trim();

    /* The title length must not exceed 33 characters! */
    if (data.title.length > 33 || data.title === '') {
        notify.showError('The title length must not be empty or exceed 33 characters!');
    } else if (data.description.length > 450 || data.description.length < 30) {
        notify.showError('The description length must not exceed 450 characters and should be at least 30!');
    } else if (data.brand.length > 11 || data.brand.lenght === 0) {
        notify.showError('The brand length must not be empty or exceed 11 characters!')
    } else if (data.fuel.length > 11 || data.fuel === '') {
        notify.showError('The fuel type length must not be empty or exceed 11 characters!')
    } else if (data.model.length > 11 || data.model === '') {
        notify.showError('The model length must not be empty or exceed 11 characters!')
    } else if (data.model.length > 11 || data.model.length < 4) {
        notify.showError('The model length must be at least 4 and not more than 11 characters!')
    } else if (data.year.length !== 4 || isNaN(data.year)) {
        notify.showError('The year must be only 4 chars long!')
    } else if (isNaN(data.price) || data.price === '' || +data.price > 1000000) {
        notify.showError('Price can not be 0 and the maximum price is 1000000$')
    } else if (!data.imageUrl.startsWith('http')) {
        notify.showError('Link url should always start with “http”.')
    } else {
        entityService.postEntity(data)
            .then(() => {
                notify.showInfo(msgs.create);
                ctx.redirect(customRoutes.all)
            })
    }
};

handlers.getEditView = function (ctx) {
    const entityId = ctx.params.entityId;
    entityService.getEntityDetails(entityId)
        .then(function (res) {
            let data =  res;
            data.formAction = customRoutes.edit.replace(':entityId',res._id);
            ctx.loadSection(EDIT_FORM)
                .then(function () {
                    this.partial(LAYOUT, res)
                })
        }).catch(notify.handleError)
};

handlers.edit = function (ctx) {
    let data = {};
    data.title = ctx.params.title.trim();
    data.description = ctx.params.description.trim();
    data.brand = ctx.params.brand.trim();
    data.fuel = ctx.params.fuelType.trim();
    data.model = ctx.params.model.trim();
    data.year = ctx.params.year.trim();
    data.price = ctx.params.price.trim();
    data.imageUrl = ctx.params.imageUrl.trim();

    /* The title length must not exceed 33 characters! */
    if (data.title.length > 33 || data.title === '') {
        notify.showError('The title length must not be empty or exceed 33 characters!');
    } else if (data.description.length > 450 || data.description.length < 30) {
        notify.showError('The description length must not exceed 450 characters and should be at least 30!');
    } else if (data.brand.length > 11 || data.brand.lenght === 0) {
        notify.showError('The brand length must not be empty or exceed 11 characters!')
    } else if (data.fuel.length > 11 || data.fuel === '') {
        notify.showError('The fuel type length must not be empty or exceed 11 characters!')
    } else if (data.model.length > 11 || data.model === '') {
        notify.showError('The model length must not be empty or exceed 11 characters!')
    } else if (data.model.length > 11 || data.model.length < 4) {
        notify.showError('The model length must be at least 4 and not more than 11 characters!')
    } else if (data.year.length !== 4 || isNaN(data.year)) {
        notify.showError('The year must be only 4 chars long!')
    } else if (isNaN(data.price) || data.price === '' || +data.price > 1000000) {
        notify.showError('Price can not be 0 and the maximum price is 1000000$')
    } else if (!data.imageUrl.startsWith('http')) {
        notify.showError('Link url should always start with “http”.')
    } else {
        data['seller'] = sessionStorage.getItem('username');
        data['_id'] = ctx.params.carId;
        entityService.updateEntity(data)
            .then(() => {
                console.log(data.title);
                notify.showInfo(msgs.update_interpolation(data.title));
                ctx.redirect(customRoutes.all)
            })
    }
};

handlers.getMyView = function (ctx) {
    entityService.getMyEntities()
        .then((res) => {
            res.forEach(l => {
                l.isSeller = true;
            });
            ctx.listings = res;
            ctx.loadSection(MY_LIST, {MY_LISTING})
                .then(function () {
                    this.partial(LAYOUT,)
                })
        })
        .catch(notify.handleError)
};

handlers.getAllView = function (ctx) {
    entityService.getAll('?query={}&sort={"_kmd.ect": -1}')
        .then((response) => {
            // console.log(response)
            let listings = response;
            listings.map(l => l.isSeller = ctx.username === l.seller);
            ctx.loadSection(LIST_ALL, {LISTING})
                .then(function () {
                    this.partial(LAYOUT, {listings});
                })
        })
        .catch(notify.showError)
};


/**
 * Delete entry from project sub collection
 * @param ctx - Sammy event context
 */
handlers.delete = function (ctx) {
    entityService.deleteEntity(ctx.params.entityId)
        .then(() => {
            // on success notify user
            notify.showInfo('Entry removed');
            ctx.redirect(customRoutes.all)
        })
};
