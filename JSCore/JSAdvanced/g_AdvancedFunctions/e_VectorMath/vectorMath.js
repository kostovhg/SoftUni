/**
 * A IIFE that returns object with functions for parameters
 * the functions are a operations with vectors as follows:
 * @add(vec1, vec2) – Addition of two vectors – f(v1, v2) => [v1.x + v2.x, v1.y + v2.y ]
 * @multiply(vec1, scalar) – Scalar multiplication – f(v1, s) => [v1.x * s, v1.y * s]
 * @length(vec1) – Vector length – f(v) => sqrt(v.x ** 2 + v.y ** 2 )
 * @dot(vec1, vec2) – Dot product of two vectors – f(v1, v2) => [v1.x * v2.x, v1.y * v2.y ]
 * @cross(vec1, vec2) – Cross product of two vectors – f(v1, v2) => [v1.x * v2.y, v1.y * v2.x ]
 * @returns Object with functions for parameters
 */

// judge accepts only the IIFE without the variable to which the IIFE was assigned
let solution = (function vectorMath(){
    return {
        add: (a, b) => [a[0] + b[0], a[1]+b[1]],
        multiply: (a, b) => [a[0] * b, a[1] * b],
        length: (a) => (a[0]**2 + a[1]**2) ** (0.5),
        dot: (a, b) => a[0] * b[0] + a[1] * b[1],
        cross: (a, b) => a[0] * b[1] - a[1] * b[0],
    }
}());

/**
 * Test Cases
 */
console.log(solution.add([1, 1], [1, 0]));
console.log(solution.multiply([3.5, -2], 2));
console.log(solution.length([3, -4]));
console.log(solution.dot([1, 0], [0, -1]));
console.log(solution.cross([3, 7], [1, 0]));
