/**
 * Create a entry (project sub collection record) vary from the project logic, so TODO: modify it all!
 * @param ctx - EventContext from sammy
 */
handlers.createEntry = function (ctx) {
    // Export ctx params to variables
    let type = ctx.params.type;
    let qty = ctx.params.qty;
    let price = ctx.params.price;
    // Check their values
    if (type.length === 0) {
        notify.showError('Product name must be non-empty string!');
    } else if (isNaN(qty)) {
        notify.showError('Quantity must be a number!')
    } else if (isNaN(price)) {
        notify.showError('Price must be a number!')
    } else {
        // Invoke entries service (entriesService.js) create entry method
        entriesService.createEntry(ctx.params)
            .then(() => {
                notify.showInfo('Entry added.');
                // TODO: change/revise redirect
                ctx.redirect(authRoutes.home)
            })
    }
};
/**
 * Delete entry from project sub collection
 * @param ctx - Sammy event context
 */
handlers.deleteEntry = function (ctx) {
    entriesService.deleteEntry(ctx.params.entryId)
        .then(() => {
            // on success notify user
            notify.showInfo('Entry removed');
            // TODO: revise redirect route
            ctx.redirect(authRoutes.home)
        })
};

// custom function for check input fields values
handlers.checkVal = function(val, inputName){
    if (val ==='' || isNaN(val)) {
        notify.showError(`The ${inputName} field must be a number!`);
        return false;
    } else {
        return true;
    }
};

// for dynamically changing values on entries overview page
// All events on entries review will be only front end till sending the receipt
handlers.callAllAttachEvents = function () {

    function isPositiveNumber(n) {
        return !isNaN(n) && n > 0;
    }

    let [
        entryType, entryQty, entryPrice, subTotal,
        previousSubtotals, submitEntry,
        submitReceipt, receiptId, receiptTotal,

    ] = [
        $('input[name=type]'), $('#qtyDynamic'), $('#priceDynamic'), $('#subTotalDynamic'),
        $('.row>.col:nth-child(4)'), $('#addItemBtn'),
        $('#checkoutBtn'), $('#entityId'), $('#totalDynamic'),
        $('input[name=entityId]'), $('input[name=productCount]'), $('input[name=total]')
    ];

    // on load form set total
    let tempTotal = 0;
    previousSubtotals.each((i, v) => {
        tempTotal += Number(v.innerText);
    });
    receiptTotal.text(tempTotal.toFixed(2));
    // entryQty.forceNumeric();
    // entryPrice.forceNumeric();
    entryQty.on('input', calc);
    entryPrice.on('input', calc);
    // submitEntry.on('submit', function (e) {
    //     //e.preventDefault();
    //     let newRow = $('<div class="row">')
    //         .append($('<div class="col wide">').text(entryType.val()))
    //         .append($('<div class="col wide">').text(entryQty.val()))
    //         .append($('<div class="col wide">').text(entryPrice.val()))
    //         .append($('<div class="col">').text((+entryQty.val() * +entryPrice.val()).toFixed(2)))
    //         .append($('<div class="col right">').append('<a href="#/entry/delete/">&#10006;</a>')
    //             .on('click', (ev) => {
    //                 //ev.preventDefault();
    //                 $(ev.currentTarget).parents('.row').remove();
    //             }));
    //     $('#active-entries').append(newRow);
    //     $('#create-createReceiptEntryRow-form')[0].reset();
    //     subTotal.text('Sub-total');
    // });

    function calc(e) {
        let currentInput = $(e.currentTarget);
        let form = currentInput.parents('form');
        //let data = $(form).serializeObject();
        let val = currentInput.val();
        if (handlers.checkVal(val, currentInput.attr('name'))) {
            subTotal.text((Number(entryQty.val()) * Number(entryPrice.val())).toFixed(2));
            receiptTotal.text((tempTotal + Number(subTotal.text())).toFixed(2));
        } else {
            currentInput.val(val.slice(0, -1));
        }
    }
};