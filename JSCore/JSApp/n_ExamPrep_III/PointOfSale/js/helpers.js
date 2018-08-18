/**
 * On Document ready attach different helpers to javascript prototypes, Handlebar.js, and other
 */
(() => {

    /**
     * Custom method for padding numbers with leading zeroes
     * @param size - count of symbols. Default is 2 (14 -> "14", 3 -> "03"). If set to 4 (3 -> "0003") and etc.
     * @returns {string} - string representation of number with leading zeroes and length equal to the given size
     */
    Number.prototype.pad = function (size) {
        var s = String(this);
        while (s.length < (size || 2)) {
            s = "0" + s;
        }
        return s;
    };

    /**
     * Attach different helpers to Handlebars to be used in templates
     */
    Handlebars.registerHelper({
        // Assign to current context property isAuthor with boolean value if current context
        // creator has the same ID as that one stored in sessionStorage (from method authService.saveSession())
        'isOwner': function () {
            this.isAuthor = sessionStorage.getItem('userId') === this._acl.creator;
        },
        // Helper accepting parameter isPublic from the current context(entity) and returns
        // 'checked' or empty string. It is used for checkbox in edit and create forms
        'publicFlight': function (isPublic) {
            return isPublic === 'true' ? 'checked' : '';
        },
        // A debug method for the phase of development which reveals current context send to
        // handlebars template which use {{debug}}
        "debug": function (optionalValue) {
            console.log("Current Context");
            console.log("====================");
            console.log(this);

            if (optionalValue) {
                console.log("Value");
                console.log("====================");
                console.log(optionalValue);
            }
        },
        // Helper to be used in html DOM elements text property that converts the passed in
        // str parameter to span with style for convert str to uppercase
        'capitalize': function (str) {
            return new Handlebars.SafeString(`<span style="text-transform: uppercase;">${str}</span>`);
        },
        // Accepts date and returns yyyy-MM-dd string to be used in edit form template
        'getDate': function (departure) {
            let aDate = new Date(departure);
            return `${aDate.getFullYear()}-${(aDate.getMonth() + 1).pad()}-${aDate.getDate().pad()}`;
        },
        // Accepts date and returns HH:mm to be used in edit form template
        'getTime': function (departure) {
            let time = new Date(departure);
            return `${time.getHours().pad()}:${time.getMinutes().pad()}`
        },
        // Accepts date and optional parameter fullName and returns string in format "dd MMM" or "dd MMMM"
        'getDateString': function (departure, fullName) {
            let theDate = new Date(departure);
            let shortMonthName = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            let fullMonthName = [
                'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'
            ];
            return `${theDate.getDate()} ${fullName ? fullMonthName[theDate.getMonth()] : shortMonthName[theDate.getMonth()]}`
        }
    });

    /**
     * Bind ajaxStart and ajaxStop to loading box visibility
     * On first ajax start loadingBox will show up.
     * On last ajax end loadingBox will hide again.
     */
    $(document).ajaxStart(function () {
        $('#loadingBox').show();
    }).ajaxStop(function () {
        $('#loadingBox').hide();
    });
})();