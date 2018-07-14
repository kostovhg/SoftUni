let expect = require('chai').expect;
let SubscriptionCard = require('../subscriptionCard');

describe('test subscriptionCard function', function () {

    let card;
    let dates = {
        '120': {
            dayBefore: new Date('2018-04-21'), startDay: new Date('2018-04-22'),
            dayBetween: new Date('2018-05-10'), endDay: new Date('2018-05-21'),
            dayAfter: new Date('2018-05-22')
        },
        '*': {
            dayBefore: new Date('2018-05-24'), startDay: new Date('2018-05-25'),
            dayBetween: new Date('2018-06-10'), endDay: new Date('2018-06-24'),
            dayAfter: new Date('2018-06-25')
        },
        'combined': {
            dayBefore: new Date('2018-04-21'), startFirst: new Date('2018-04-22'),
            dayInFirst: new Date('2018-05-10'), endFirst: new Date('2018-05-21'),
            dayBetween: new Date('2018-05-23'), startSecond: new Date('2018-05-25'),
            dayInSecond: new Date('2018-06-10'), endSecond: new Date('2018-06-24'),
            dayAfter: new Date('2018-06-25')
        }
    };

    beforeEach('Initialize object', () => {
        card = new SubscriptionCard('Pesho', 'Petrov', '00000000');
    });

    describe('card creation and properties publicity', function () {
        it('firstName accessor should return the firstName', function () {
            expect(card._firstName).to.be.equal('Pesho')
        });
        it('lastName accessor should return the lastName', function () {
            expect(card._lastName).to.be.equal('Petrov')
        });
        it('SSN accessor should return the SSN', function () {
            expect(card._SSN).to.be.equal('00000000')
        });
        it('_subscriptions should be the []', function () {
            expect(card).to.have.property('_subscriptions').that.eql([])
        });
        it('_blocked should be the false', function () {
            expect(card).to.have.property('_blocked').that.eql(false)
        });
    });
    describe('other functions', function () {

        beforeEach('add subscriptions', () => {
            card.addSubscription('120', new Date('2018-04-22'), new Date('2018-05-21'));
            card.addSubscription('*', new Date('2018-05-25'), new Date('2018-06-24'));
        });

        it('block should change is blocked ', function () {
            expect(card).to.have.property('_blocked').that.eql(false);
            card.block();
            expect(card).to.have.property('_blocked').that.eql(true);
        });
        it('unblock should change blocked to false', function () {
            card.block();
            expect(card).to.have.property('_blocked').that.eql(true);
            card.unblock();
            expect(card).to.have.property('_blocked').that.eql(false);
        });
        it('addSubscription should add subscription', function () {
            expect(card._subscriptions.length).to.be.eq(2)
        });
    });
    describe('isValid(line, date) function check', function () {
        describe('with only one subscription 120', function () {
            beforeEach('add 120 subscription', () => {
                card.addSubscription('120', dates['120'].startDay, dates['120'].endDay);
            });
            it('should return true with correct line and date', function () {
                let result = card.isValid('120', dates['120'].dayBetween);
                expect(result).to.eq(true);
            });
            it('should return false if card is blocked', function () {
                card.block();
                let result = card.isValid('120', dates['120'].dayBetween);
                expect(result).to.eq(false);
            });
            // check if _subscriptions does not contain 'line'
            it('should return false if _subscription does not contain line', function () {
                let result = card.isValid('122', dates['120'].dayBetween);
                expect(result).to.eq(false);
            });
            it('should return false if _subscription is a day before', function () {
                let result = card.isValid('120', dates['120'].dayBefore);
                expect(result).to.eq(false);
            });
            it('should return false if _subscription is a day after', function () {
                let result = card.isValid('120', dates['120'].dayAfter);
                expect(result).to.eq(false);
            });
        });
        describe('with second subscription *', function () {

            beforeEach('add * subscription', () => {
                card.addSubscription('120', dates['120'].startDay, dates['120'].endDay);
                card.addSubscription('*', dates['*'].startDay, dates['*'].endDay);
            });
            it('should return false if card is blocked', function () {
                card.block();
                let result = card.isValid('5', dates['120'].dayBetween);
                expect(result).to.eq(false);
            });
            it('should return true with correct line and date between', function () {
                let result = card.isValid('5', dates['*'].dayBetween);
                expect(result).to.eq(true);
            });
            it('should return true with correct line and date start', function () {
                let result = card.isValid('5', dates['*'].startDay);
                expect(result).to.eq(true);
            });
            it('should return true with correct line and date end', function () {
                let result = card.isValid('5', dates['*'].endDay);
                expect(result).to.eq(true);
            });
            // check if _subscriptions dates are outside the given date'
            it('should return false if _subscription is a day before', function () {
                let result = card.isValid('5', dates['*'].dayBefore);
                expect(result).to.eq(false);
            });
            it('should return false if _subscription is a day after', function () {
                let result = card.isValid('5', dates['*'].dayAfter);
                expect(result).to.eq(false);
            });
            // check all intervals
            // day [120] [*]
            it('should return false  is a day before all subscriptions', function () {
                let result = card.isValid('120', dates['combined'].dayBefore);
                expect(result).to.eq(false);
            });
            // [120] day [*]
            it('should return false if _subscription is a day between', function () {
                let result = card.isValid('120', dates['combined'].dayBetween);
                expect(result).to.eq(false);
            });
            it('should return false if _subscription is a day between', function () {
                let result = card.isValid('12', dates['combined'].dayBetween);
                expect(result).to.eq(false);
            });
            // [120] [*] day
            it('should return false if day is after all subscriptions', function () {
                let result = card.isValid('120', dates['combined'].dayAfter);
                expect(result).to.eq(false);
            });
            it('should return false if day is after all subscriptions', function () {
                let result = card.isValid('12', dates['combined'].dayAfter);
                expect(result).to.eq(false);
            });
            // [day <= 120] [*]
            it('should return true if day is at the start of named line', function () {
                let result = card.isValid('120', dates['combined'].startFirst);
                expect(result).to.eq(true);
            });
            // [120 day] [*]
            it('should return true if day is at the end of named line', function () {
                let result = card.isValid('120', dates['combined'].endFirst );
                expect(result).to.eq(true);
            });
            // [120] [day *]
            it('should return true if day is at the start of * line', function () {
                let result = card.isValid('1250', dates['combined'].startSecond );
                expect(result).to.eq(true);
            });
            // [120] [* day]
            it('should return true if day is at the end of * line', function () {
                let result = card.isValid('1250', dates['combined'].endSecond );
                expect(result).to.eq(true);
            });
        });
    });
});