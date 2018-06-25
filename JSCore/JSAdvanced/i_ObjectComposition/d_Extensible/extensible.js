function extensible(template) {

    let newObj = {
        __proto__: {},
        extend: copy
    };

    function copy(template) {
        for (let prop in template) {
            ({}.toString.call(template[prop]) === '[object Function]') ?
                Object.getPrototypeOf(newObj)[prop] = template[prop] :
                newObj[prop] = template[prop];
        }
    }

    newObj.extend(template);
    return newObj
}

let template = {
    extensionMethod: function () {
        console.log(`some function`)
    },
    extensionProperty: 'someString'
};

let resultObj = extensible(template);

console.log(resultObj);
resultObj.extend(template);

console.log(Object.getPrototypeOf(resultObj));


