/**
 * Basic service module for manipulating entries (project main collection entries)
 * This module depends on requester.js
 * @type {{*}}
 * returns functions returning ajax promises.
 * All of the functions that executes GET request should have specific queries
 * All of the functions that execute POST or PUT should modify data object before send it.
 */
let entityService = (() => {

    // Create entity
    /**
     * Create entity into kinvey database
     * @param entityData - object to be send
     * @returns {*} - promise from requester.post (response should be created entity or error)
     */
    function postEntity(entityData){
        // Form data modification before sending it to requester
        let data = entityData;
        data.seller = sessionStorage.getItem('username');
        let endpoint = `${ENTITY_NAME}`;
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
        let endpoint = `${ENTITY_NAME}/${entityData._id}`;
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
        let endpoint = `${ENTITY_NAME}/${entityId}`;
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
        let endpoint = `${ENTITY_NAME}/${entityId}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }
    // My Entities
    /**
     * Get from database all entries satisfying query parameters
     * @returns {*} - promise from requester.get (response should be Array of objects or error)
     */
    function getMyEntities() {
        let username = sessionStorage.getItem('username');
        let endpoint = `${ENTITY_NAME}?query={"seller":"${username}"}&sort={"_kmd.ect": -1}`;
        return requester.get('appdata', endpoint, 'kinvey')
    }

    function getAll(custom_query) {
        let endpoint = `${ENTITY_NAME}`;
        if(custom_query.startsWith('?query')) endpoint = endpoint + custom_query;
        return requester.get('appdata', endpoint, 'kinvey')
    }

    return {
        postEntity,
        updateEntity,
        deleteEntity,
        getMyEntities,
        getEntityDetails,
        getAll,
    }
})();