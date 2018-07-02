let validate = (function () {
    let template = {
        Method: /^(GET|POST|DELETE|CONNECT)$/g,
        URI: /^(\*|[.\w]+)$/g,
        Version: /^HTTP\/(0\.9|1\.[0-1]|2\.0)$/g,
        Message: /^[^\<\>\\\&\'\"]*$/g
    };

    return (inputObj) => {
        [...Object.getOwnPropertyNames(template)].forEach(k => {
            let p = k.toLowerCase();
            if (!inputObj.hasOwnProperty(p) || !inputObj[p].match(template[k])) {
                throw new Error(`Invalid request header: Invalid ${k}`)
            }
        });
        return inputObj;
    }
})();


let objects = [
    {method: 'GET', uri: 'kkk jjjj', version: 'HTTP/0.8', message: ''},
    {method: 'GET', uri: 'svn.public.catalog', version: 'HTTP/1.1', message: ""},
    {method: 'POST', uri: '.catalog', version: 'HTTP/1.1', message: ""},
    {method: 'delete', uri: '...aaa666', version: 'HTTP/1.1', message: ""},
    {method: 'CONNECT', uri: '...aaa666', version: 'HTTP/1.1', message: ""},
    {method: 'GET', uri: 'svn.public.catalog', version: 'HTTP/0.0', message: ""},
    {method: 'GET', uri: 'svn.public.catalog', version: 'HTTP/1.1', message: "^&"},
    {method: 'GET', uri: 'svn.public.catalog', version: 'HTTP/1.1'},
    {uri: 'svn.public.catalog', version: 'HTTP/1.1', message: ""},
];

for (let obj of objects) {
    try {
        console.log(validate(obj));
    } catch (e) {
        console.log(e.message);
    }
}
