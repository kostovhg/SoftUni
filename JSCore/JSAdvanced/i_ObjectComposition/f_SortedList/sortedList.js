function sortedList(input) {
    return {
        list: [],
        size: 0,
        add: function (el) {
            //this.list.push(el);
            this.size++;
            let counter = 0;
            while(el > this.list[counter]){
                counter++;
            }
            this.list.splice(counter, 0, el);
            //this.list.sort((a, b) => a - b)
        },
        remove: function (index) {
            if (index >= 0 && index < this.list.length) {
                this.list.splice(index, 1);
                this.size--;
            }
        },
        get: function (index) {
            if (index >= 0 && index < this.list.length) {
                return this.list[index];
            }
        },

    }
}


let theList = sortedList();

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
