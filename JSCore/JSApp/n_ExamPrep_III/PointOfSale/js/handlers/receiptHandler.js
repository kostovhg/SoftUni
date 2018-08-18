handlers.getEditor = async function (ctx) {

    try {
        const userId = sessionStorage.getItem('userId');
        let [ receipt ] = await receiptService.getActiveReceipt(userId);
        if(!receipt){
            receipt = await receiptService.createReceipt();
        }

        let entries = await entriesService.getAllByReceiptId(receipt._id);
        ctx.entries = entries;
        ctx.receiptId = receipt._id;

        ctx.loadPartials({
            navigation: headerSection,
            // footer: './templates/common/footer.hbs',
            checkout,
            entryForm,
            entry: createReceiptEntryRow,
        }).then(function () {
            // attach events

            this.partial('./templates/receipts/createReceiptView.hbs', entriesService.callAttachEvents)
        })

    } catch (e) {
        notify.handleError(e);
    }
};

handlers.createEntry = function (ctx) {
    let type = ctx.params.type;
    let qty = ctx.params.qty;
    let price = ctx.params.price;

    if(type.length === 0){
        notify.showError('Product name must be non-empty string!');
    } else if (isNaN(qty)){
        notify.showError('Quantity must be a number!')
    } else if(isNaN(price)){
        notify.showError('Price must be a number!')
    } else {
        entriesService.createEntry(ctx.params)
            .then(()=> {
                notify.showInfo('Entry added.')
            })
    }
};