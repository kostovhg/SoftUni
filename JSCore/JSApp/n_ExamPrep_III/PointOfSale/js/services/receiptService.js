/**
 * Basic service module for manipulating entities (in this case flights)
 * This module depends on requester.js
 * @type {{getAllPublicFlights, createFlight, editFlight, deleteFlight, myFlights, flightDetails}}
 */
let flightsService = (() => {

    // get all public flights
    /**
     * Get all entities satisfying query parameters (in this case all flights with property isPublic = "true")
     * @returns {*} promise from requester.get (response should be an Array of objects sorted by 'departure' property)
     */
    function getAllPublicFlights(){
        // return requester.get('appdata', 'flights?query={"isPublic":true}&sort={"_kmd.ect": -1}', 'kinvey')
        return requester.get('appdata',
            'flights?query={"isPublic":"true"}&sort={"departure": 1}',
            'kinvey')

    }

    // Create Flight
    /**
     * Create entity into kinvey database
     * @param flightData - object to be send
     * @returns {*} - promise from requester.post (response should be created entity or error)
     */
    function createFlight(flightData){
        // Refine form input in to entity object to be send to database
        flightData.isPublic = flightData.isPublic === 'on';
        flightData.imageUrl = flightData.img;
        delete flightData.departureDate;
        delete flightData.departureTime;
        delete flightData.img;
        return requester.post('appdata', 'flights/', 'kinvey', flightData)
    }

    // Edit Flight
    /**
     * Modify entity
     * @param flightData - input object from submitted form
     * @returns {*} - promise returned from requester.update (response should be entity object or error)
     */
    function editFlight(flightData){
        // Refine form input in to entity object to be send to database
        flightData.isPublic = flightData.public === 'on';
        flightData.imageUrl = flightData.img;
        flightData._id = flightData.flightId;
        delete flightData.departureDate;
        delete flightData.departureTime;
        delete flightData.img;
        delete flightData.flightId;
        delete flightData.public;

        return requester.update('appdata', `flights/${flightData._id}`,auth, flightData)
    }

    // Delete Flight
    /**
     * Delete entity
     * @param flightId - id of the entity that needs to be deleted
     * @returns {*} - promise from requester.remove (response should be count of removed entities or error)
     */
    function deleteFlight(flightId) {
        return requester.remove('appdata', `flights/${flightId}`, 'kinvey');
    }

    // Flight Details
    /**
     * Get from database specific entity
     * @param flightId - entity database id
     * @returns {*} - promise from requester.get (response should be single object or error)
     */
    function flightDetails(flightId) {
        return requester.get('appdata', `flights/${flightId}`, 'kinvey');
    }
    // My Flights
    /**
     * Get from database all entities satisfying query parameters
     * @returns {*} - promise from requester.get (response should be Array of objects or error)
     */
    function myFlights() {
        return requester.get(
            'appdata',
            `flights?query={"_acl.creator":"${sessionStorage.getItem('userId')}"}&sort={"departure": 1}`,
            'kinvey')
    }

    return {
        getAllPublicFlights,
        createFlight,
        editFlight,
        deleteFlight,
        myFlights,
        flightDetails
    }
})();