/**
 * Basic service module for manipulating entities (in this case receipts)
 * This module depends on requester.js
 * @type {{getAllPublicReceipts, createReceipt, editReceipt, deleteReceipt, myReceipts, receiptDetails}}
 */
let receiptService = (() => {

    // get all public receipts
    /**
     * Get all entities satisfying query parameters (in this case all receipts with property isPublic = "true")
     * @returns {*} promise from requester.get (response should be an Array of objects sorted by 'departure' property)
     */
    function getAllPublicReceipts(){
        // return requester.get('appdata', 'receipts?query={"isPublic":true}&sort={"_kmd.ect": -1}', 'kinvey')
        return requester.get('appdata',
            'receipts?query={"isPublic":"true"}&sort={"departure": 1}',
            'kinvey')

    }

    // Create Receipt
    /**
     * Create entity into kinvey database
     * @param receiptData - object to be send
     * @returns {*} - promise from requester.post (response should be created entity or error)
     */
    function createReceipt(receiptData){
        // Refine form input in to entity object to be send to database
        receiptData.isPublic = receiptData.isPublic === 'on';
        receiptData.imageUrl = receiptData.img;
        delete receiptData.departureDate;
        delete receiptData.departureTime;
        delete receiptData.img;
        return requester.post('appdata', 'receipts/', 'kinvey', receiptData)
    }

    // Edit Receipt
    /**
     * Modify entity
     * @param receiptData - input object from submitted form
     * @returns {*} - promise returned from requester.update (response should be entity object or error)
     */
    function editReceipt(receiptData){
        // Refine form input in to entity object to be send to database
        receiptData.isPublic = receiptData.public === 'on';
        receiptData.imageUrl = receiptData.img;
        receiptData._id = receiptData.receiptId;
        delete receiptData.departureDate;
        delete receiptData.departureTime;
        delete receiptData.img;
        delete receiptData.receiptId;
        delete receiptData.public;

        return requester.update('appdata', `receipts/${receiptData._id}`,auth, receiptData)
    }

    // Delete Receipt
    /**
     * Delete entity
     * @param receiptId - id of the entity that needs to be deleted
     * @returns {*} - promise from requester.remove (response should be count of removed entities or error)
     */
    function deleteReceipt(receiptId) {
        return requester.remove('appdata', `receipts/${receiptId}`, 'kinvey');
    }

    // Receipt Details
    /**
     * Get from database specific entity
     * @param receiptId - entity database id
     * @returns {*} - promise from requester.get (response should be single object or error)
     */
    function receiptDetails(receiptId) {
        return requester.get('appdata', `receipts/${receiptId}`, 'kinvey');
    }
    // My Receipts
    /**
     * Get from database all entities satisfying query parameters
     * @returns {*} - promise from requester.get (response should be Array of objects or error)
     */
    function myReceipts() {
        return requester.get(
            'appdata',
            `receipts?query={"_acl.creator":"${sessionStorage.getItem('userId')}"}&sort={"departure": 1}`,
            'kinvey')
    }

    return {
        getAllPublicReceipts,
        createReceipt,
        editReceipt,
        deleteReceipt,
        myReceipts,
        receiptDetails
    }
})();