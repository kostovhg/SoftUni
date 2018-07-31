const sdk = require('kinvey-flex-sdk');
const request = require('request'); // assumes that the request module was added to package.json
sdk.service({host: "localhost", port: 63342}, (err, flex) => {

        const data = flex.data; // gets the FlexData object from the service
        const flexFunctions = flex.functions;   // gets the FlexFunctions object from the service
        function onPostFetch(request, response, modules) {  // Add logging capability
// Add logging capability
            var logger = modules.logger;
            var collectionName = request.collectionName;

            var entity = modules.kinvey.entity();
            var data = {};

            if (request.entityId !== undefined) {

                var entityId = request.entityId;
                logger.info('entity id: ' + entityId);

                // Log the parameter "_id" passed in the request body
                logger.info(entityId);

                //modules.collectionAccess.collection('adv').findAndModify(
                modules.collectionAccess.collection(collectionName).findAndModify(
                    {_id: entityId},
                    [],
                    {$inc: {views: 1}},
                    {/*upsert: true, views: 0*/},
                    function (err, result, body) {
                        logger.info('Error from fundAndModify: ' + JSON.stringify(err));
                        logger.info('result from findAndModify:  ' + JSON.stringify(result));
                        logger.info('Body from findAndModify: ' + JSON.stringify(body));
                        logger.info('request from findAndModify: ' + JSON.stringify(request.body));
                        logger.info('response from findAndModify: ' + JSON.stringify(response.body));
                        //modules.collectionAccess.collection('adv').find({_id: entityId}, function (e, r) {
                        modules.collectionAccess.collection(collectionName).find({_id: entityId}, function (e, r, b) {
                            logger.info('The error and the result from findcommand:');
                            logger.info('Error from find: ' + JSON.stringify(e));
                            logger.info('result from find:  ' + JSON.stringify(r));
                            logger.info('Body from find: ' + JSON.stringify(b));
                            logger.info('request from find: ' + JSON.stringify(request.body));
                            logger.info('response from find: ' + JSON.stringify(response.body));
                            response.continue();
                            //return response.complete();
                        });
                        logger.info('request after find: ' + JSON.stringify(request.body));
                        logger.info('response after find: ' + JSON.stringify(response.body));
                        response.continue();
                        //return rsponse.complete(200);
                    });
            } else {
                response.continue();
                return response.complete();
            }
        }

    function onPreFetch(request, response, modules) {
        // Add logging capability
        var logger = modules.logger;
        var collectionName = request.collectionName;
        var data = {};

        if (request.entityId !== undefined) {

            var entityId = request.entityId;
            // Log the parameter "_id" passed in the request body
            logger.info('entity _id: ' + entityId);
            // The ID in Kinvey appears to be not a string (even it is represented that way in json)
            // but some Object!!!
            var objId = modules.collectionAccess.objectID(entityId)
            // Log the parameter "_id" but as obj
            logger.info('entity _id: ' + objId);

            modules.collectionAccess.collection(collectionName).update(
                {_id: objId},
                {$inc: {views: 1}}, // increce the views count
                /*{upsert: true, views: 0},*/
                function (err, result) {
                    if(err){
                        logger.warn(err);
                    } else {
                        logger.info(result); // result from increase
                        response.continue();
                    }
                });

        } else {
            response.continue();
            return response.complete();
        }
    }

        // set the serviceObject
        const widgets = data.serviceObject('widgets');
        // wire up the event that we want to process
        widgets.onGetById(onPostFetch)
    }
);

