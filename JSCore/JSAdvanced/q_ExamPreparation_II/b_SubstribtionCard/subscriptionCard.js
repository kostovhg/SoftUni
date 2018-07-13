class SubscriptionCard {
    constructor(firstName, lastName, SSN) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._SSN = SSN;
        this._subscriptions = [];
        this._blocked = false;
    }

    get firstName() {
        return this._firstName;
    }

    get lastName() {
        return this._lastName;
    }

    get SSN() {
        return this._SSN;
    }

    get isBlocked() {
        return this._blocked;
    }

    addSubscription(line, startDate, endDate) {
        this._subscriptions.push({
            line,
            startDate,
            endDate
        });
    }

    isValid(line, date) {
        if (this.isBlocked) return false;
        return this._subscriptions.filter(s => s.line === line || s.line === '*')
            .filter(s => {
                return s.startDate <= date &&
                    s.endDate >= date;
            }).length > 0;
    }

    block() {
        this._blocked = true;
    }

    unblock() {
        this._blocked = false;
    }
}
module.exports = SubscriptionCard;


const card = new SubscriptionCard('Pesho', 'Petrov', '00000000');
card.addSubscription('120', new Date('2018-04-22'), new Date('2018-05-21'));
card.addSubscription('*', new Date('2018-05-25'), new Date('2018-06-24'));
card.block();
card.unblock();
console.log(card.isValid('120', new Date('2018-04-22')));
card.firstName = 'Gosho';
console.log(card.firstName);