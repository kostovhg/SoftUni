/* Asynchronous programming */

/***********************
 * parallel tasks
 ************************/

// setTimeout(t1, 1000);
// setTimeout(t2, 2000);
// setTimeout(t3, 500);
//
// function t1() {
//     console.log('Task1');
// }
// function t2() {
//     console.log('Task2');
// }
// function t3() {
//     console.log('Task3');
// }

/*************************
 * Promises - Syntax
 *************************/

// let p = new Promise(function (resolve, reject) {
//     // Do an async task and then resolve or reject
//
//     if(/* operation successful*/) {
//         resolve('Success!');
//     } else {
//         reject('Failure!');
//     }
// });
//
// p.then(function(result) {
//      /* process the result(when the promise is resolved) */ })
// .catch(function(error) {
//      /* handle the error(when the promise is rejected) */ });

/* Promise.then() example */
// console.log('Before promise');
//
// new Promise(function (resolve, reject) {
//     console.log('Inside promise');
//     setTimeout(function () {
//         resolve('done');
//     }, 1500);
//
// })
//     .then(function (result) {
//         console.log('Then returned: ' + result);
//         //throw new Error('Opsa!');
//     })
//     .catch(function (err) {
//         console.log('Ended with error: ' + err)
//     });
//
// console.log('After promise');
//
// /* Promise.catch() example */
// console.log('Before promise');
//
// new Promise(function (resolve, reject) {
//     console.log('Inside promise');
//     setTimeout(function () {
//         reject('fail');
//     }, 1500);
//
// })
//     .then(function (result) {
//         console.log('Then returned: ' + result);
//     })
//     .catch(function (err) {
//         console.log('Ended with error: ' + err)
//     });
//
// console.log('After promise');

/*************************
 * JS Promises - Example - First Task
 *************************/
//
// let p1 = new Promise(function (resolve, reject) {
//     console.log('Task1 started.');
//     // reject('cannot execute task1');
//     setTimeout(function () {
//         resolve('Task1 result');
//         console.log('Task1 finished.')
//     }, 2000);
// });
//
// let p2 = new Promise(function (resolve, reject) {
//     console.log('Task2 started.');
//     // reject('cannot execute task2');
//     setTimeout(function () {
//         resolve('Task2 result');
//         console.log('Task2 finished.')
//     }, 1000);
// });
//
// let p3 = new Promise(function (resolve, reject) {
//     console.log('Task3 started.');
//     // reject('cannot execute task3');
//     setTimeout(function () {
//         resolve('Task3 result');
//         console.log('Task3 finished.')
//     }, 450);
// });
//
// console.log('All tasks started.');
//
// Promise.all([p1, p2, p3])
//     .then(function (result) {
//         console.log('All tasks finished.');
//         console.log("Result: " + result.join(", "));
//     })
//     .catch(function (err) {
//         console.log('Some of the tasks failed.');
//         console.log('Error: ' + err)
//     });

/*************************
 * Async / Await Simplify Promises
 *************************/

async function loadData() {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            resolve('data');
        }, 3000)
    })
}

async function loadMoreData(input) {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            resolve(input + ' + more data');
        }, 2000)
    })
}

async function loadAllData() {
    console.log('Before promise');
    let data = await loadData();
    console.log('between awaits');
    let moreData = await loadMoreData(data);
    console.log('Result: ' + moreData);
    console.log('After promise')
}

loadAllData();
console.log('Finished')