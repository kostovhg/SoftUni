function rotateArray(input) {

    let pos = input.pop() % input.length;
    // for (let i = 0; i < pos ; i++) {
    //     input.unshift(input.pop());
    // }

    console.log(input.slice(-pos).concat(input.slice(0, -pos)).join(' '));
}

rotateArray([1, 2, 3, 4, 2]);
rotateArray(['Banana','Orange','Coconut','Apple',15]);