/**
 * Function that accepts array of [width, height], convert array members to
 * 'rectangles' objects with {width, height, area(), compareTo()}
 * Comparison sorts by rectangle area in descending order as a first criteria
 * and by their width in descending order as a second criteria.
 * @param matrix - input consist of array with elements in format [width, height]
 * @return {*} - returns sorted array of rectangles
 */

function orderRectangles(matrix) {

    for (let i = 0; i < matrix.length; i++) {
        matrix[i] = {width: matrix[i][0], height: matrix[i][1],
            area: function () {
                return this.width * this.height
            },
            compareTo: function (o) {
                if(typeof(o) !== typeof(this)){
                    return null;
                }
                return (o.area() - this.area()) || o.width - this.width;
            }
        }
    }

    return matrix.sort((a, b) => a.compareTo(b))
}

console.log(orderRectangles([[10, 5], [5, 12]]));
console.log(orderRectangles([[10, 5], [3, 20], [5, 12]]));
