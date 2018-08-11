/**
 * Basic service module for manipulating entries (project main collection entries)
 * This module depends on requester.js
 * @type {{getAllPublicReceipts, receipts, editReceipt, deleteReceipt, myReceipts, entityDetails}}
 * returns functions returning ajax promises.
 * All of the functions that executes GET request should have specific queries
 * All of the functions that execute POST or PUT should modify data object before send it.
 */
let entityService = (() => {

    // Get entity by query
    function getActiveEntity(userId) {
        let endpoint = `${entities}?query={"active":"true"}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }

    // Create entity
    /**
     * Create entity into kinvey database
     * @param entityData - object to be send
     * @returns {*} - promise from requester.post (response should be created entity or error)
     */
    function postEntity(entityData){
        // TODO: modify data and endpoint
        let data = {
            active: true,
            productsCount: 0,
            total: 0
        };
        let endpoint = `${entities}`;
        return requester.post('appdata', endpoint, 'kinvey', data)
    }

    // Edit Entity
    /**
     * Modify entity
     * @param entityData - input object from submitted form
     * @returns {*} - promise returned from requester.update (response should be entity object or error)
     */
    function updateEntity(entityData){
        // Refine form input in to entity object to be send to database
        // TODO: modify data and endpoint
        let endpoint = `${entities}/${entityData._id}`;
        // only creator can edit entity (if roles are not edited in backend)
        return requester.update('appdata', endpoint, 'kinvey', entityData)
    }

    // Delete Entity
    /**
     * Delete entity
     * @param entityId - id of the entity that needs to be deleted
     * @returns {*} - promise from requester.remove (response should be count of removed entries or error)
     */
    function deleteEntity(entityId) {
        // TODO: modify endpoint
        let endpoint = `${entities}/${entityId}`;
        // note: only creator and admin can delete entity with post request (if roles are not modified in the backend)
        return requester.remove('appdata', endpoint, 'kinvey');
    }

    // Entity Details
    /**
     * Get from database specific entity
     * @param entityId - entity database id
     * @returns {*} - promise from requester.get (response should be single object or error)
     */
    function getEntityDetails(entityId) {
        // TODO: modify endpoint
        let endpoint = `${entities}/${entityId}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }
    // My Entities
    /**
     * Get from database all entries satisfying query parameters
     * @returns {*} - promise from requester.get (response should be Array of objects or error)
     */
    function getMyEntities() {
        // TODO: modify the query in endpoint
        let userId = sessionStorage.getItem('userId');
        let endpoint = `${entities}?query={"_acl.creator":"${userId}","active":"false"}`;
        return requester.get('appdata', endpoint, 'kinvey')
    }

    /**
     * A function for create entry with collection of entries
     * @param entityId - the entity id
     * @param entriesCount - some property based on collection
     * @param total - some other property based on collection
     * @returns {Promise<*>} - response from update request to database
     */
    async function checkout(entityId, entriesCount, total) {
        // TODO: modify all necessary parameters
        const endpoint = `${entities}/${entityId}`;
        //const endpoint = `receipts/`;
        let data = await getEntityDetails(entityId);
        // TODO: remove or edit all those data parameters
        data['active'] = false;
        data['productsCount'] = entriesCount;
        data['total'] = total;
        data['_acl']['creator'] = sessionStorage.getItem('userId');

        return requester.update('appdata', endpoint, 'kinvey', data);
    }

    return {
        postEntity,
        updateEntity,
        deleteEntity,
        getMyEntities,
        getEntityDetails,
        getActiveEntity,
        checkout,
    }
})();