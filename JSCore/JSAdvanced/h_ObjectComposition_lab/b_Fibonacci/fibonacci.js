
// working in judge, because it expects a function to be returned, not an array
function fibonacci(){
    let [first, second] = [0, 1];
    return function() {
        let next = first + second;
        first = second;
        second = next;
        return first
    }
}

let fib = fibonacci();
console.log(fib(5));
console.log(fib(15));
console.log(fib(15));
console.log(fib(15));

// According the description with array as a result
// function fibonacci(n) {
//     let f = (() => {
//         let [first, second] = [0, 1];
//         return () => {
//             let next = first + second;
//             first = second; second = next;
//             return first;
//         }
//     })();
//
//     let result = [];
//     for (let i = 0; i < n; i++) {
//         result.push(f())
//     }
//
//     //console.log(result);
//     return result;
// }
//
// fibonacci(5);
// fibonacci(15);

