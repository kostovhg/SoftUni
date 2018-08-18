/**
 * Basic service module for manipulating entries (project specific entries - project sub collection records)
 * This module depends on requester.js
 * @type {{getAllPublicEntries, entryDetails, editEntry, deleteEntity, myEntries, entryDetails}}
 * returns functions returning ajax promises.
 * All of the functions that executes GET request should have specific queries
 * All of the functions that execute POST or PUT should modify data object before send it.
 * For exam the queries are given in problem description
 */
let entriesService = (() => {

    // get all public entries
    /**
     * Get all entries satisfying query parameters
     * @returns {*} promise from requester.get (response should be an Array of objects)
     */
    function getAllEntityEntries(entityId) {
        // TODO: replace/modify endings !
        let endpoint = `${entries}?query={"receiptId":"${entityId}"}&sort={"departure": 1}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }

    // Create entry
    /**
     * Create entry into kinvey database
     * @param entryData - object to be send
     * @returns {*} - promise from requester.post (response should be created entry or error)
     */
    function createEntry(entryData) {
        // Refine form input in to entity object to be send to database
        // TODO: modify data and endpoint
        let data = {
            type: entryData.type,
            qty: entryData.qty,
            price: entryData.price,
            subtotal: +entryData.qty * +entryData.price,
            entityId: entryData.entityId,
        };
        let endpoint = `${entries}/`;
        return requester.post('appdata', endpoint, 'kinvey', data)
    }

    // Edit Entry
    /**
     * Modify entry
     * @param entryData - input object from submitted form
     * @returns {*} - promise returned from requester.update (response should be entity object or error)
     */
    function editEntry(entryData) {
        // Refine form input in to entity object to be send to database
        // TODO: modify data and endpoint
        let data = entryData;
        let endpoint = `${entries}/${entryData._id}`;
        return requester.update('appdata', endpoint, auth, data)
    }

    // Delete Entry
    /**
     * Delete entry
     * @param entityId - id of the entity that needs to be deleted
     * @returns {*} - promise from requester.remove (response should be count of removed entries or error)
     */
    function deleteEntry(entityId) {
        // TODO: check/change endpoint
        let endpoint = `${entries}/${entityId}`;
        return requester.remove('appdata', endpoint, 'kinvey');
    }

    // Entry Details
    /**
     * Get from database specific entry
     * @param entryId - entity database id
     * @returns {*} - promise from requester.get (response should be single object or error)
     */
    function entryDetails(entryId) {
        // TODO: modify/check endpoint
        let endpoint = `${entries}/${entryId}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }

    // Collection of Entries
    /**
     * Get from database all entries satisfying query parameters
     * @returns {*} - promise from requester.get (response should be Array of objects or error)
     */
    function getAllEntriesByQuery() {
        // TODO: modify/change endpoint query
        let endpoint = `${entries}?query={"_acl.creator":"${sessionStorage.getItem('userId')}"}&sort={"departure": 1}`;
        return requester.get('appdata', endpoint, 'kinvey')
    }

    return {
        getAllEntityEntries,
        createEntry,
        editEntry,
        deleteEntry,
        getAllEntriesByQuery,
        entryDetails,
    }
})();