/**
 * On Current script call attach different helpers to javascript prototypes, Handlebar.js, and other
 */
(() => {

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
     * Custom method for return passed time in form (? {days,hours,months} ago)
     * @param dateIsoFormat
     * @returns {string}
     */
    Date.prototype.timePassed = function (dateIsoFormat) {
        let diff = new Date - (new Date(dateIsoFormat));
        diff = Math.floor(diff / 60000);
        if (diff < 1) return 'less than a minute';
        if (diff < 60) return diff + ' minute' + pluralize(diff);
        diff = Math.floor(diff / 60);
        if (diff < 24) return diff + ' hour' + pluralize(diff);
        diff = Math.floor(diff / 24);
        if (diff < 30) return diff + ' day' + pluralize(diff);
        diff = Math.floor(diff / 30);
        if (diff < 12) return diff + ' month' + pluralize(diff);
        diff = Math.floor(diff / 12);
        return diff + ' year' + pluralize(diff);
        function pluralize(value) {
            if (value !== 1) return 's';
            else return '';
        }
    };

    // attach reduce to jQuery
    // $.fn.reduce = [].reduce;

    // attach plugin to jQuery for only numeric values on input key press
    $.fn.forceNumeric = function () {
        return this.each(function () {
            $(this).keydown(function (e) {
                var key = e.which || e.keyCode;

                if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
                    // numbers
                    key >= 48 && key <= 57 ||
                    // Numeric keypad
                    key >= 96 && key <= 105 ||
                    // comma, period and minus, . on keypad
                    key == 190 || key == 188  || key == 109 || key == 110 ||
                    // Backspace and Tab and Enter
                    key == 8 || key == 9 || key == 13 ||
                    // Home and End
                    key == 35 || key == 36 ||
                    // left and right arrows
                    key == 37 || key == 39 ||
                    // Del and Ins
                    key == 46 || key == 45) {
                    return true;
                }
                {
                    notify.showError('You can enter only positive numbers!')
                    return false;
                }
            });
        });
    };

    // attach serealizeObject plugin for transfer forms to obj
    //https://www.helicaltech.com/get-form-data-as-an-object-using-jquery/
    $.fn.serializeObject = function () {
        var results = {},
            arr = this.serializeArray();
        for (var i = 0, len = arr.length; i < len; i++) {
            obj = arr[i];
            //Check if results have a property with given name
            if (results.hasOwnProperty(obj.name)) {
                //Check if given object is an array
                if (!results[obj.name].push) {
                    results[obj.name] = [results[obj.name]];
                }
                results[obj.name].push(obj.value || '');
            } else {
                results[obj.name] = obj.value || '';
            }
        }
        return results;
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
        'isPublic': function (isPublic) {
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
        // Accepts date (should be valid js date string) and returns yyyy-MM-dd string to be used in edit form template
        'getDate': function (dateString) {
            let aDate = new Date(dateString);
            return `${aDate.getFullYear()}-${(aDate.getMonth() + 1).pad()}-${aDate.getDate().pad()}`;
        },
        // Accepts date (should be valid js date string) and returns HH:mm to be used in edit form template
        'getTime': function (departure) {
            let time = new Date(departure);
            return `${time.getHours().pad()}:${time.getMinutes().pad()}`
        },
        // Accepts date (should be valid js date string) and returns yyyy-MM-dd HH:mm string to be used in edit form template
        'getDateTime': function (aDate) {
            aDate = new Date(aDate);
            return `${aDate.getFullYear()}-${(aDate.getMonth() + 1).pad()}-${aDate.getDate().pad()} ${aDate.getHours().pad()}:${aDate.getMinutes().pad()}`
        },
        // Accepts date and optional parameter fullName and returns string in format "dd MMM" or "dd MMMM"
        'getDateString': function (stringDate, fullName) {
            let theDate = new Date(stringDate);
            let shortMonthName = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            let fullMonthName = [
                'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'
            ];
            return `${theDate.getDate()} ${fullName ? fullMonthName[theDate.getMonth()] : shortMonthName[theDate.getMonth()]}`
        },
        // get sum of all entries properties. propToSum should be string
        'getTotal': function (entries, propToSum) {
            return entries.length === 0 ? 0 : entries.reduce((a, b) => a + (b[propToSum]));
        },
        // Return username in sessionStorage
        'username': function () {
            return sessionStorage.getItem('username');
        },
        // return userId from sessionStorage
        'userId': function () {
            return sessionStorage.getItem('userId');
        }
    });

})();