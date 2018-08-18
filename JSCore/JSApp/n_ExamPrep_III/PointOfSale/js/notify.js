/**
 * Module that controls the display of information boxes
 * @type {{showError, showInfo, handleError, showLoading}}
 */
let notify = (() => {

    /**
     * Passes error response json representation property "description" to showError function
     * @param reason - usually received from .catch block after request
     */
    function handleError(reason) {
        showError(reason.responseJSON.description);
    }

    /**
     * Display infoBox for 3 seconds with message
     * @param message - message to be displayed
     */
    function showInfo(message) {
        let infoBox = $('#infoBox');
        infoBox.html(`<span>${message}</span>`);
        infoBox.show();
        setTimeout(() => infoBox.fadeOut(), 3000);
    }

    function showLoading(){
        // let loadingBox = $('#loadingBox');
        // loadingBox.show();
        // handled in helpers
    }

    /**
     * Display errorBox for 3 seconds with message
     * @param message - message to be displayed
     */
    function showError(message) {
        let errorBox = $('#errorBox');
        errorBox.html(`<span>${message}</span>`);
        errorBox.show();
        setTimeout(() => errorBox.fadeOut(), 3000);
    }

    return {
        showError,
        showInfo,
        handleError,
        showLoading,
    }

})();