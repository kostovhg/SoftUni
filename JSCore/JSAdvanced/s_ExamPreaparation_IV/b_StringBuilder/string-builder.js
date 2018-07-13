// class StringBuilder {
//     constructor(string) {
//         if (string !== undefined) {
//             StringBuilder._vrfyParam(string);
//             this._stringArray = Array.from(string);
//         } else {
//             this._stringArray = [];
//         }
//     }
//
//     append(string) {
//         StringBuilder._vrfyParam(string);
//         for(let i = 0; i < string.length; i++) {
//             this._stringArray.push(string[i]);
//         }
//     }
//
//     prepend(string) {
//         StringBuilder._vrfyParam(string);
//         for(let i = string.length - 1; i >= 0; i--) {
//             this._stringArray.unshift(string[i]);
//         }
//     }
//
//     insertAt(string, startIndex) {
//         StringBuilder._vrfyParam(string);
//         this._stringArray.splice(startIndex, 0, ...string);
//     }
//
//     remove(startIndex, length) {
//         this._stringArray.splice(startIndex, length);
//     }
//
//     static _vrfyParam(param) {
//         if (typeof param !== 'string') throw new TypeError('Argument must be string');
//     }
//
//     toString() {
//         return this._stringArray.join('');
//     }
// }
StringBuilder = class StringBuilder {
    constructor(string) {
        this._vrfyParam = function(param) {
            if (typeof param !== 'string') throw new TypeError('Argument must be string');
        };

        if (string !== undefined) {
            this._vrfyParam(string);
            this._stringArray = Array.from(string);
        } else {
            this._stringArray = [];
        }

        this.append = function(string) {
            this._vrfyParam(string);
            for(let i = 0; i < string.length; i++) {
                this._stringArray.push(string[i]);
            }
        };

        this.prepend = function(string) {
            this._vrfyParam(string);
            for(let i = string.length - 1; i >= 0; i--) {
                this._stringArray.unshift(string[i]);
            }
        };

        this.insertAt = function(string, startIndex) {
            this._vrfyParam(string);
            this._stringArray.splice(startIndex, 0, ...string);
        };

        this.remove = function(startIndex, length) {
            this._stringArray.splice(startIndex, length);
        };

        this.toString = function() {
            return this._stringArray.join('');
        };
    }
};

module.exports = StringBuilder;
//
// let str = new StringBuilder('hello');
// str.append(', there');
// str.prepend('User, ');
// str.insertAt('woop',5 );
// console.log(str.toString());
// str.remove(6, 3);
// console.log(str.toString());