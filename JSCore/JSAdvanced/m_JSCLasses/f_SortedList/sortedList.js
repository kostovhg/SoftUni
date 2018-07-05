
let theListClass = (function() {

    return class SortedList{

        constructor(){ [this.list, this.size] = [[], 0]}

        add(el) {
            (this.size < 1 || el > this.list[this.size - 1]) ?
                this.list.push(el) :
                this.list.splice(this.findIndex(el), 0, el);
            this.size++;
        }
        remove(index) {
            if (index >= 0 && index < this.list.length) {
                this.list.splice(index, 1);
                this.size--;
            }
        }
        get(index) {
            if (index >= 0 && index < this.list.length) {
                return this.list[index];
            }
        }
        findIndex(el) {
            let counter = 0;
            while (el > this.list[counter]) {
                counter++;
            }
            return counter;
        }

    }
})();


let theList = new theListClass;

console.log('start size :' + theList.size);
theList.add(3);
theList.add(5);
theList.add(2);
theList.add(4);
theList.add(1);
console.log(theList.list.toString());
theList.remove(2);
console.log('sorted list: ' + theList.list.toString());
console.log('Get index: ' + theList.get(2));
console.log('End size: ' + theList.size);

