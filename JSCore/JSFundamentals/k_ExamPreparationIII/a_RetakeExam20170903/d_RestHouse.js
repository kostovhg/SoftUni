function restHouse(availableRooms, guests) {
    let rooms = new Map();
    let guestsWithoutRooms = 0;
    for (let currentRoom of availableRooms) {
        let roomSpace = currentRoom.type === 'double-bedded' ? 2 : 3;
        rooms.set(currentRoom.number, { type: currentRoom.type, emptyBeds: roomSpace });
    }
    for (let currentPair of guests) {
        let roomFound = false;
        if (currentPair.first.gender != currentPair.second.gender) {
            for (let [key, value] of rooms) {
                if (value.type == 'double-bedded' && value.emptyBeds == 2) {
                    value.guests = [];
                    value.guests = [currentPair.first, currentPair.second];
                    value.emptyBeds = 0;
                    roomFound = true;
                    break;
                }
            }
        } else {
            for (let [key, value] of rooms) {
                if (value.type == 'triple' && value.emptyBeds > 1) {
                    if (value.guests == undefined) {
                        value.guests = [];
                    } else if (value.guests[0].gender != currentPair.second.gender) {
                        continue;
                    }
                    if (currentPair.first != undefined) {
                        value.guests.push(currentPair.first);
                        value.emptyBeds -= 1;
                    }
                    value.guests.push(currentPair.second);
                    value.emptyBeds -= 1;
                    roomFound = true;
                    break;
                } else if (value.type == 'triple' && value.emptyBeds == 1) {
                    if (value.guests[0].gender == currentPair.second.gender) {
                        value.guests.push(currentPair.first == undefined ? currentPair.second : currentPair.first);
                        value.emptyBeds -= 1;
                        currentPair.first = undefined;
                    }
                }
            }
        }
        if (!roomFound) {
            guestsWithoutRooms += currentPair.first == undefined ? 1 : 2;
        }
    }
    for (let [room, value] of [...rooms].sort()) {
        console.log(`Room number: ${room}`);
        if (value.guests != undefined) {
            for (let guest of value.guests.sort(function (a, b) {
                return a.name.toLowerCase().localeCompare(b.name.toLowerCase());
            })) {
                console.log(`--Guest Name: ${guest.name}`);
                console.log(`--Guest Age: ${guest.age}`);
            }
        }
        console.log(`Empty beds in the room: ${value.emptyBeds}`);
    }
    console.log(`Guests moved to the tea house: ${guestsWithoutRooms}`);
}

restHouse([
    {number: '206', type: 'double-bedded'},
    {number: '311', type: 'triple'}], [
    {
        first: {name: 'Tanya Popova', gender: 'female', age: 24},
        second: {name: 'Miglena Yovcheva', gender: 'female', age: 23}
    },
    {
        first: {name: 'Katerina Stefanova', gender: 'female', age: 23},
        second: {name: 'Angel Nachev', gender: 'male', age: 22}
    },
    {
        first: {name: 'Tatyana Germanova', gender: 'female', age: 23},
        second: {name: 'Boryana Baeva', gender: 'female', age: 22}
    }]);

restHouse([
    {number: '101A', type: 'double-bedded'},
    {number: '104', type: 'triple'},
    {number: '101B', type: 'double-bedded'},
    {number: '102', type: 'triple'}], [
    {
        first: {name: 'Sushi & Chicken', gender: 'female', age: 15},
        second: {name: 'Salisa Debelisa', gender: 'female', age: 25}
    },
    {
        first: {name: 'Daenerys Targaryen', gender: 'female', age: 20},
        second: {name: 'Jeko Snejev', gender: 'male', age: 18}
    },
    {
        first: {name: 'Pesho Goshov', gender: 'male', age: 20},
        second: {name: 'Gosho Peshov', gender: 'male', age: 18}
    },
    {
        first: {name: 'Conor McGregor', gender: 'male', age: 29},
        second: {name: 'Floyd Mayweather', gender: 'male', age: 40}
    }]);