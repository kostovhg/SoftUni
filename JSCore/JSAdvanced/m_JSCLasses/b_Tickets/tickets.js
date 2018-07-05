function tickets(input, sortCriteria) {

    let Ticket = (function f() {
       return class Ticket {

            constructor(destination, price, status) {
                [this.destination, this.price, this.status] = [destination, Number(price), status]
            }

            compareTo(otherThicket, criteria = 'price') {
                return (isNaN(this[criteria])) ?
                    this[criteria].localeCompare(otherThicket[criteria]) :
                    this[criteria] - otherThicket[criteria]
            }

            toString(){
                return `Ticket { destination: '${this.destination}', price: ${this.price.toFixed(2)}, status: '${this.status}' },`
            }
        }
    })();

    let ticketsArray = [];
    input.forEach(line => ticketsArray.push(new Ticket(...line.split('|'))));

    return ticketsArray.sort((a, b) => a.compareTo(b, sortCriteria));
}


console.log(tickets(['Philadelphia|94.20|available',
        'New York City|95.99|available',
        'New York City|95.99|sold',
        'Boston|126.20|departed'],
    'destination'));
console.log(tickets(['Philadelphia|94.20|available',
        'New York City|95.99|available',
        'New York City|95.99|sold',
        'Boston|126.20|departed'],
    'status'));
console.log(tickets(['Philadelphia|94.20|available',
        'New York City|95.99|available',
        'New York City|95.99|sold',
        'Boston|126.20|departed'],
    'price'));
