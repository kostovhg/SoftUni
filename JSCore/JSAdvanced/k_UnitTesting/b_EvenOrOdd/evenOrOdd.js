function isOddOrEven(inputString){
    if(typeof(inputString) !== 'string'){
       return undefined;
    }
    if(inputString.length % 2 === 0){
        return "even";
    }

    return 'odd'
}

module.exports=isOddOrEven;