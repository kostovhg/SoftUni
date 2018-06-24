/**
 * Function that prints the eccentric anomaly (E) from Kepler's equation
 * M = E - e * sin(E)
 * @param M - mean anomaly
 * @param e - orbital eccentricity
 */

function keplersProblem(M, e) {
    // return the result of approximate function
    console.log(Number(newtonsMethod(M, e, 0).toFixed(9)));

    /**
     * Calculate current difference between the y = 0 (real root) and y = M - f(x) (current value of x where y = f(x))
     * @param x
     * @return {number}
     */
    function y(x) {
        return Math.abs(M - (x - e * Math.sign(x)));
    };

    /**
     * Calculate x1 = x0 - (f(x0) / f'(x0)) as step from Newton's method
     * @param x0 - previous x value
     * @return {number} - x1, next x value (value of the point where the tangent crosses x axis)
     */
    function nextIteration(x0) {
        return x0 - (x0 - e * Math.sin(x0) - M) / (1 - e * Math.cos(x0));
    }

    /**
     * Approximate E recursively for no more than 200 recursions
     * Check if current approximation of E returns root of y lower than epsilon (1e-9) or recursion count limit is reached
     * If either condition is met return current E
     * @param E0 - current eccentric anomaly (E)
     * @param series - count of recursions
     * @return {*} - itself or current E
     */
    function newtonsMethod(E0, series) {
        return (y(E0) < 1e-9 || series > 200) ?
            E0 : // it will be returned from last recursive call to the first one
            newtonsMethod(nextIteration(E0), ++series); // if conditions are not met call this function with new value for E
    }
}


keplersProblem(1, 0);
//1

keplersProblem(3.1415926535, 0.75);
// 3.141592654

keplersProblem(0.25, 0.99);
// 1.156077258

keplersProblem(4.8, 0.2);
// 4.601234265
