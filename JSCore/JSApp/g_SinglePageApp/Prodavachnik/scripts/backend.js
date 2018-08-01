// const sdk = require('kinvey-flex-sdk');
// const request = require('request'); // assumes that the request module was added to package.json
// sdk.service({host: "localhost", port: 63342}, (err, flex) => {
//
//     const data = flex.data; // gets the FlexData object from the service
//     const flexFunctions = flex.functions;   // gets the FlexFunctions object from the service
// });

// currently working hooks on the host

function onPreFetch(request, response, modules) {
    // Add logging capability
    var logger = modules.logger;
    var collection = modules.collectionAccess.collection(request.collectionName);
    var data = {};
    var currentUser = modules.requestContext.getAuthenticatedUserId();

    if (request.entityId) {

        var entityId = request.entityId;
        // Log the parameter "_id" passed in the request body
        logger.info('entity _id: ' + entityId);
        // The ID in Kinvey appears to be not a string (even it is represented that way in json)
        // but some Object!!!
        var objId = modules.collectionAccess.objectID(entityId);
        // Log the parameter "_id" but as obj
        logger.info('entity _id: ' + objId);

        collection.update(
            {_id: objId, '_acl.creator': {$ne: currentUser}}, // do not update if requester ID is the ID of creator
            {$inc: {views: 1}}, // increase the views count
            /*{upsert: true, views: 0},*/
            function (err, result) {
                if (err) {
                    logger.warn(err);
                } else {
                    logger.info(result); // result from increase
                    // response.continue();
                }
            });
        response.continue();
    } else {
        // if get is for the collection, continue. (probably sort collection here)
        logger.info('Fetch full collection')
        response.continue();
        //return response.complete();
    }
}

function onPostSave(request, response, modules) {

    var logger = modules.logger;

    if (!request.entityId) {
        var collection = modules.collectionAccess.collection('adv');
        var objId = collection.objectID(response.body._id);

        collection.update(
            {_id: objId},
            {
                $set:
                    {
                        views: 0
                    }
            },
            {},
            function (err, result) {
                if (err) {
                    logger.warn(err);
                    response.continue();
                } else {
                    logger.info(result);
                    response.continue();
                }
            }
        );
        //response.continue();
    } else {
        response.continue();
    }
}

