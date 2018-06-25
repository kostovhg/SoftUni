(function stringExtension(input) {

    String.prototype.ensureStart = function (str) {
        if (!this.startsWith(str)) {
            return '' + str + this;
        } else {
            return '' + this
        }
    };

    String.prototype.ensureEnd = function (str) {
        if(!this.endsWith(str)){
            return this + str;
        } else {
            return '' + this
        }
    };

    String.prototype.isEmpty = function () {
        return (this.length < 1) ;
    };

    String.prototype.truncate = function (n) {
        if(n < this.length && n > -1){
            let str = this.substr(0, n);
            let lastIndex = str.trimRight().lastIndexOf(' ');
            str = str.substr(0, lastIndex > 0 ? lastIndex : n - 3);
            return str + '...';
        } else {
            return '' + this
        }
    };

    String.format = function (format, ...placeholders) {
        let newString = format;
        for (let i in placeholders) {
            newString = newString.replace(`{${i}}`, placeholders[i])
        }
        return newString;
    };


}());
/*
ensureStart(str) – append str to the beginning of a string, only if it’s not already present
ensureEnd(str) – append str to the end of a string, only if it’s not already present
isEmpty() – return true if the string is empty, false otherwise
truncate(n) – truncates the string to n characters by removing words and appends an ellipsis (three periods) to the end. 
If a string is less than n characters long, return the same string.
If it is longer, split the string where a space occurs and append an ellipsis to it so that the total length is less than or equal to n.
If no space occurs anywhere in the string, return n – 3 characters and an ellipsis. 
If n is less than 4, return n amount of periods.
format(string, …params) – static method to replace placeholders with parameters. 
A placeholder is a number surrounded by curly braces. If parameter index cannot be found for a certain placeholder, 
do not modify it. Note static methods are attached to the String object instead of it’s prototype. 
 */
let str = 'my string';
console.log(str);
str = str.ensureStart('my')
console.log(str);
str = str.ensureStart('hello ')
console.log(str);
str = str.truncate(16);
console.log(str);
str = str.truncate(14)
console.log(str);
str = str.truncate(8)
console.log(str);
str = str.truncate(4)
console.log(str);
str = str.truncate(2)
console.log(str);
str = String.format('The {0} {1} fox',
    'quick', 'brown');
console.log(str);
str = String.format('jumps {0} {1}',
    'dog');
console.log(str);

//       1234567890
let s = 'the quick brown fox jumps over the lazy dog';
console.log(s.isEmpty());
console.log(s.truncate(6));
console.log(s.truncate(12));
console.log(`--------------`);
console.log(s.truncate(10));
console.log(s.truncate(25));
console.log(s.truncate(43));
console.log(s.truncate(45));
