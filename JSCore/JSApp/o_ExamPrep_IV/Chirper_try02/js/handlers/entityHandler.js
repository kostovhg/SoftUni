// TODO: All this logic should be changed according the project
/**
 * Get a single active entity (project specific)
 * @param ctx
 * @returns {Promise<void>}
 */
handlers.getActiveEntity = async function (ctx) {
    // Not for use, only for example
    try {
        const userId = sessionStorage.getItem('userId');
        let [receipt] = await entriesService.getActiveReceipt(userId);
        if (!receipt) {
            receipt = await entriesService.createReceipt();
        }

        let entries = await entityService.getAllByReceiptId(receipt._id);

        if (entries.length > 0) {
            entries.forEach(e => e.subtotal = (e.qty * e.price).toFixed(2));
            ctx.productsCount = entries.length;
            ctx.total = entries
                .map(e => +e.subtotal)
                .reduce((a, b) => a + b)
                .toFixed(2);
        } else {
            ctx.total = 0;
            ctx.productsCount = 0;
        }

        ctx.entries = entries;
        ctx.receiptId = receipt._id;
        // ctx.username = sessionStorage.getItem('username');

        ctx.loadPartials({
            headerSection,
            createReceiptEntryRow,
            createEntryForm,
            createReceiptForm,
        }).then(function () {
            // attach events
            this.partial(createReceiptView, handlers.callAllAttachEvents)
        })
    } catch (e) {
        notify.showError(e.message);
    }
};

handlers.checkoutEntry = function (ctx) {
    const receiptId = ctx.params.entityId;
    const productsCount = +ctx.params.productsCount;
    const total = +ctx.params.total;

    if (productsCount === 0) {
        notify.showError('Cannot checkout without any products');
    } else {
        entriesService.checkout(receiptId, productsCount, total)
            .then(() => {
                notify.showInfo('Receipt checked out');
                ctx.redirect(authRoutes.createEntity);
            })
            .catch(notify.handleError);
    }
};

handlers.getEntityDetails = function (ctx) {
    const receiptId = ctx.params.entityId;
    entityService.getAllByReceiptId(receiptId)
        .then(function (res) {
            res.forEach(e => e.subtotal = (e.price * e.qty).toFixed(2));
            ctx.entries = res;
            ctx.loadPartials({
                headerSection, receiptDetailsEntryRow
            }).then(function () {
                this.partial(receiptDetailsView)
            })
        }).catch(notify.handleError)
};

handlers.getMyEntities = function (ctx) {
    entityService.getMyEntities()
        .then((res) => {
            res.forEach(e => {
                e.date = new Date(e._kmd.ect).toString();
            });
            // ctx.username = sessionStorage.getItem('username')
            ctx.receipts = res;
            if (res.length > 0) {
                ctx.total = res
                    .map(e => +e.total)
                    .reduce((a, b) => a + b).toFixed(2);
            } else {
                ctx.total = 0;
            }

            ctx.loadPartials({
                headerSection, allReceiptDetailsRow,
            }).then(function () {
                this.partial(allReceiptView)
            })
        })
        .catch(notify.handleError)
};

