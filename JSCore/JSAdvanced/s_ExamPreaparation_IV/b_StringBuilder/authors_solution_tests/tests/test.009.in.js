//<testInfo>append, prepend, insertAt do not verify parameter</testInfo>
StringBuilder = class StringBuilder {
    constructor(string) {
        if (string !== undefined) {
            if (typeof string !== 'string') throw new TypeError('Argument must be string');
            this._stringArray = Array.from(string);
        } else {
            this._stringArray = [];
        }
    }

    append(string) {
        for(let i = 0; i < string.length; i++) {
            this._stringArray.push(string[i]);
        }
    }

    prepend(string) {
        for(let i = string.length - 1; i >= 0; i--) {
            this._stringArray.unshift(string[i]);
        }
    }

    insertAt(string, startIndex) {
        this._stringArray.splice(startIndex, 0, ...string);
    }

    remove(startIndex, length) {
        this._stringArray.splice(startIndex, length);
    }

    toString() {
        return this._stringArray.join('');
    }
};