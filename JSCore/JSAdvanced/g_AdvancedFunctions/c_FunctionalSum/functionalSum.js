// let add = (function () {
//         let sum = 0;
//         return function functionalSum(num){
//             sum += num;
//             functionalSum.toString = () => sum;
//             return functionalSum;
//         }
//     }
// )();
//
//
// console.log('' + add(1)(6)(-3));


function add(n){

    let f = (x) => {
        return add(n + x)
    };

    f.toString = () => {
        return n
    };

    return f;
}