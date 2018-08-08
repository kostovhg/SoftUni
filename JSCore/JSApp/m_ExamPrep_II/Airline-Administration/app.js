/**
 * Sammy application configuration
 */
$(() => {

    /**
     * Define Sammy instance to app and set its element_selector to #container
     */
    let app = Sammy('#container', function () {

        // We are going to use sammy.handlebars.js plugin
        this.use('Handlebars', 'hbs');

        // A fixed variable that points to the context here.
        let appCtx = this;

        // Routes variables
        let [
            homeRoute, loginRoute, registerRoute, logoutRoute,
            catalogRoute, createRoute, viewFlightDetailsRoute,
            viewEditFlightRoute, viewMyFlightsRoute, flightRemoveRoute
        ] = [
            '#/home', '#/login', '#/register', '#/logout',
            '#/catalog', '#/create', '#/flights/:flightId',
            '#/flight/edit/:flightId', '#/flights', '#/flight/remove/:flightId'
        ];

        // Handlebars files location variables
        let [
            header, navigation, footer, layout, home,
            loginForm, registerForm,
            catalogPage, flightPreview, viewAddFlight, formAddFlight, viewFlightDetails,
            viewEditFlight, viewMyFlights, flightTicket

        ] = [
            './templates/common/header.hbs',
            './templates/common/navigation.hbs',
            './templates/common/footer.hbs',
            './templates/common/layout.hbs',
            './templates/common/home.hbs',
            './templates/user/viewLogin.hbs',
            './templates/user/viewRegister.hbs',
            './templates/flights/catalog.hbs',
            './templates/flights/flightPreview.hbs',
            './templates/flights/viewAddFlight.hbs',
            './templates/flights/formAddFlight.hbs',
            './templates/flights/viewFlightDetails.hbs',
            './templates/flights/viewEditFlight.hbs',
            './templates/flights/viewMyFlights.hbs',
            './templates/flights/flightTicket.hbs',
        ];

        // Some additional functions that can be used in multiple routes procedures
        function getStringDate(date) {
            let theDate = new Date(date);
            let months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            return `${theDate.getDate()} ${months[theDate.getMonth()]}`
        }

        function isNormalInteger(str) {
            var n = Math.floor(Number(str));
            return String(n) === str && n >= 0;
        }

        function isPositiveNumber(n) {
            return !isNaN(n) && n > 0;
        }

        function isValidDate(departureDate) {
            return new Date(departureDate) instanceof Date;
        }

        function isValidTime(departureTime) {
            return /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/.test(departureTime);
        }

        // A most commonly used procedures saved as helpers to Sammy.Application
        this.helpers({
            /**
             * The function will be used in all get requests and in few post request
             * It is a pre-script (something like http://sammyjs.org/docs/api/master/Sammy.Application._methods_.before)
             * that is going to execute loading of common templates and context.params variables
             * @param addPartials - partials to be loaded before rendering final template
             * @param addParams - a parameters to be added to current context
             * @returns {*} - RenderContext (http://sammyjs.org/docs/api/master/all#Sammy.RenderContext)
             */
            loadCommon: function (addPartials, addParams) {
                // prepare default partials
                let partials = {
                    header, footer, navigation
                };
                // If there is additional parameters passed as object, attach them to current partials
                if (addPartials) {
                    for (let key of Object.keys(addPartials)) {
                        partials[key] = addPartials[key];
                    }
                }
                // if function caller has params property (probably it is a sammy app)
                if (this.params) {
                    // load default parameters to params (or directly to the app context, which sounds badly)
                    if (this.params._acl && this.params._acl.creator === sessionStorage.getItem('userId')) {
                        this.isAuthor = true;
                    }
                    this.loggedIn = sessionStorage.getItem('authtoken') !== null;
                    this.username = sessionStorage.getItem('username');
                    // Add additional passed as object properties parameters if any
                    if (addParams) {
                        for (let param of Object.keys(addParams)) {
                            this.params[param] = addParams[param];
                        }
                    }
                } else {
                    console.log('this is not proper type');
                    console.log(this)
                    // I don't know
                }
                //console.log(that);
                return this.loadPartials(partials);
            },
        });

        /* Routes */
        // On get home route, render home template
        this.get(homeRoute, function (eventContext) {
            // Debugging
            // console.log('Event Context\n==================');
            // console.log(eventContext);
            // console.log(this);

            // If user is logged in, redirect to catalog
            if (auth.isAuth()) {
                this.redirect(catalogRoute);
                return;
            }
            // load common partials plus loginForm (which name in the template is regLogForm,
            // that way, same template can use two form, loginForm or registerForm)
            this.loadCommon({regLogForm: loginForm})
                .then(function (renderContext) {
                    // Debugging
                    // console.log('Render Context last partial\n================');
                    // console.log(renderContext);
                    // console.log('Event Context === this.event_context\n==============');
                    // console.log(eventContext === this.event_context);
                    // console.log('Event Context\n==================');
                    // console.log(eventContext);
                    // console.log('This from this.loadCommon');
                    // console.log(this);

                    // pass a parameter to template to know if it should render regLogForm
                    this.partial(home, {regLogForm: true});
                });
        });

        // On route /register show the above, but this time with register form
        this.get(registerRoute, function (eventContext) {

            this.loadCommon({regLogForm: registerForm})
                .then(function (renderContext) {
                    this.partial(home, {regLogForm: true});
                });
        });

        // on registration
        this.post(registerRoute, (eventContext) => {
            // collect form parameters from eventContext (forms should have action to this route and method equal to post)
            let username = eventContext.params.username;
            let password = eventContext.params.pass;
            let repeatPassword = eventContext.params.checkPass;

            // few tedious verifications
            if (!/^[a-zA-Z]{5,}$/g.test(username)) {
                notify.showError('A username should be at least 5 characters long');
            } else if (!password === '') {
                notify.showError('A password should not be empty')
            } else if (repeatPassword !== password) {
                notify.showError('Passwords should match');
            } else {
                // get authService method register
                auth.register(username, password)
                    .then((userData) => {
                        // and if request passes, save session
                        auth.saveSession(userData);
                        // notify the user for the success
                        notify.showInfo('User registration successful.');
                        // go to catalog
                        eventContext.redirect(catalogRoute);
                    }).catch(notify.handleError);
            }
        });

        // on get login
        this.get(loginRoute, function (eventContext) {

            this.loadCommon({regLogForm: loginForm})
                .then(function (renderContext) {
                    this.partial(home, {regLogForm: true});
                });
        });

        // on post login
        this.post(loginRoute, function (eventContext) {
            let username = eventContext.params.username;
            let password = eventContext.params.pass;

            // Again checks //TODO: export common checks in method
            // I already have kinvey users that does not pass those checks, and
            if (!/^[a-zA-Z]{5,}$/g.test(username)) {
                notify.showError('A username should be at least 5 characters long');
            } else if (!password === '') {
                notify.showError('A password should not be empty')
            } else {
                auth.login(username, password)
                    .then((userData) => {
                        // Again, on success, save, notify, redirect
                        auth.saveSession(userData);
                        notify.showInfo('Successfully logged in.');
                        eventContext.redirect(catalogRoute);
                    }).catch((err) => {
                    notify.handleError(err)
                })
            }
        });

        // get logout
        this.get(logoutRoute, function (eventContext) {
            auth.logout()
                .then(() => {
                    // clear session, notify, redirect
                    sessionStorage.clear();
                    notify.showInfo('Logout successful.');
                    eventContext.redirect(homeRoute);
                }).catch(notify.handleError)
        });

        // get catalog
        this.get(catalogRoute, (eventContext) => {
            // If in some particular way, somebody reach catalog route without authentication
            // redirect him to home
            if (!auth.isAuth()) {
                eventContext.redirect(homeRoute);
                return;
            }
            // call flight service getAllPublicFlights
            flightsService.getAllPublicFlights()
                .then((flights) => {
                    // Get the resulting array of flight objects
                    //console.log(flights);
                    // Modify departure with method from line 52 to string for each flight object
                    flights.forEach((f, i) => {
                        //console.log(p);
                        f.departure = getStringDate(f.departure);
                    });

                    // ctx.isAuth = auth.isAuth(); // I don't know
                    // ctx.username = sessionStorage.getItem('username'); // do not understand it any more

                    // transfer the modified array to event context property "flights"
                    //eventContext.flights = flights; // depreciated

                    // load partials and finally the catalog page
                    eventContext.loadCommon({flightPreview})
                        .then(function (renderContext) {
                            // send to template data for user authentication (not exactly necessary)
                            // and the array of flights as partial variable with name publicFlights (catalog.hbs line 8)
                            this.partial(catalogPage, {
                                loggedIn: auth.isAuth(), // problem description is not very clear
                                publicFlights: flights, // better way than as an eventContext parameter
                            });
                        })
                }).catch(notify.handleError)
        });

        this.post(createRoute, (eventContext) => {
            // Again, no magics for unsigned users
            if (!auth.isAuth()) {
                this.redirect(homeRoute);
                return;
            }
            // Save all parameters to new obj
            let flightDetails = eventContext.params;
            // go trough all fields and validate their values
            if (flightDetails.destination === '') {
                notify.showError('Destination field can not be empty');
            } else if (flightDetails.origin === '') {
                notify.showError('Origin field can not be empty');
            } else if (!isNormalInteger(flightDetails.seats)) {
                notify.showError('Number of Seats field should be positive integer number.');
            } else if (!isPositiveNumber(flightDetails.cost)) {
                notify.showError('Cost per Seat field should be positive number.');
            } else if (!isValidDate(flightDetails.departureDate)) {
                notify.showError('Departure date is not valid.');
            } else if (!isValidTime(flightDetails.departureTime)) {
                notify.showError('Departure time is not valid');
            } else {
                // at the end brand new variable for form fields values
                let flightData = {};
                // combine date and time fields values to newly formatted string
                flightData.departure = eventContext.params.departureDate + 'T' + eventContext.params.departureTime;
                // later on those and some other properties will be deleted, to not ruin the database entity format
                // TODO: optimize properties transfer.
                for (let prop in eventContext.params) {
                    if (eventContext.params.hasOwnProperty(prop)) {
                        flightData[prop] = eventContext.params[prop];
                    }
                }
                // Call corresponding method from flightService (which will refine the object)
                flightsService.createFlight(flightData)
                    .then(function (response) {
                        notify.showInfo('Created flight');
                        eventContext.redirect(homeRoute);
                    }).catch(notify.handleError);
            }
        });

        // get create view
        this.get(createRoute, (eventContext) => {
            // console.log('Event Context\n==================');
            // console.log(eventContext);
            // console.log(this);
            if (!auth.isAuth()) {
                this.redirect(homeRoute);
                return;
            }
            eventContext.loadCommon({formAddFlight})
                .then(function (renderContext) {
                    this.partial(viewAddFlight);
                });
        });

        // get details view
        this.get(viewFlightDetailsRoute, (eventContext) => {

            // Call corresponding method from flight service module
            flightsService.flightDetails(eventContext.params.flightId)
                .then((result) => {
                    // assign to the result obj boolean isCreator
                    // which will allow the template to decide if there is going to have
                    // a edit button or not
                    result.isCreator = sessionStorage.getItem('userId') === result._acl.creator;

                    eventContext.loadCommon({})
                        .then(function (renderContext) {
                            // All necessary parameters are in ajax return result object,
                            // so we can pass it as data parameter to template rendering method
                            this.partial(viewFlightDetails, result);
                        });
                })
                .catch(notify.handleError);
        });

        // get edit view
        this.get(viewEditFlightRoute, (eventContext) => {
            // again corresponding method from entity service
            flightsService.flightDetails(eventContext.params.flightId)
                .then((result) => {
                    // load common (for navigation and footer)
                    eventContext.loadCommon({})
                        .then(function (renderContext) {
                            this.partial(viewEditFlight, result);
                        });
                })
                .catch(notify.handleError);
        });


        // post edited entity
        this.post(viewEditFlightRoute, (eventContext) => {
            // temp variable that copy context parameters
            let flightDetails = eventContext.params;
            // validations
            if (flightDetails.destination === '') {
                notify.showError('Destination field can not be empty');
            } else if (flightDetails.origin === '') {
                notify.showError('Origin field can not be empty');
            } else if (!isNormalInteger(flightDetails.seats)) {
                notify.showError('Number of Seats field should be positive integer number.');
            } else if (!isPositiveNumber(flightDetails.cost)) {
                notify.showError('Cost per Seat field should be positive number.');
            } else if (!isValidDate(flightDetails.departureDate)) {
                notify.showError('Departure date is not valid.');
            } else if (!isValidTime(flightDetails.departureTime)) {
                notify.showError('Departure time is not valid');
            } else {
                // second copy of context parameters (forms input fields values)
                let flightData = {};
                // again transform time and date values to departure datetime representation
                // TODO: combine common procedures for create and edit logic in separate method
                flightData.departure = flightDetails.departureDate + 'T' + eventContext.params.departureTime;
                for (let prop in flightDetails) {
                    if (flightDetails.hasOwnProperty(prop)) {
                        flightData[prop] = flightDetails[prop];
                    }
                }
                flightsService.editFlight(flightData)
                    .then((result) => {
                        notify.showInfo('Successfully edited flight.');
                        // this time the route to be redirected to will depend on
                        // request return object _id. That will redirect us to
                        // view flight details to edited entity with new details (probably back button functionality can do the same)
                        eventContext.redirect('#/flights/' + result._id)
                    })
                    .catch(notify.handleError);
            }
        });

        // get view my flights
        this.get(viewMyFlightsRoute, (eventContext) => {
            flightsService.myFlights()
                .then((result) => {
                    // load additional partial 'flightTicket.hbs' that holds preview for
                    // each flight
                    eventContext.loadCommon({flightTicket})
                        .then(function (renderContext) {
                            // create temp object with property flights
                            // that holds a array of objects to iterate through into the template
                            let data = {};
                            data.flights = result;
                            this.partial(viewMyFlights, data);
                        });
                })
                .catch(notify.handleError);
        });

        this.get(flightRemoveRoute, (eventContext) => {
            flightsService.deleteFlight(eventContext.params.flightId)
                .then((response) => {
                    notify.showInfo('Flight deleted');
                    eventContext.redirect(viewMyFlightsRoute);
                }).catch(notify.handleError)
        })
    });

    // Config is done, run this miracle
    app.run('#/home');
});