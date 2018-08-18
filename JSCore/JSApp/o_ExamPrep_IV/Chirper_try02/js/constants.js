// Project specific entries and entries
const [entities, entries] = ['entities', 'entries'];

/*
Handlebars templates locations as constants
 */
/* Handlebars templates locations */
// common for all projects
const [headerSection, footerSection, // footer and header
    navigation, welcomeSection, // navigation and welcome section (welcome is the home for non registered users)
    loginForm, registerForm, // login and register
    homeSection, // home section is home for logged in users
] = [
    './templates/common/headerSection.hbs', './templates/common/footerSection.hbs',
    './templates/common/navigation.hbs', './templates/welcomeSection.hbs',
    './templates/forms/loginForm.hbs',
    './templates/forms/registerForm.hbs',
    './templates/mainViews/homeSection.hbs'];
// Specific for the project
const [
    submitChirpForm,
    createReceiptView,
    createReceiptEntryRow,
    createEntryForm,
    createReceiptForm,
    allReceiptView,
    allReceiptDetailsRow,
    receiptDetailsView,
    receiptDetailsEntryRow,
] = [
    './templates/partialViews/forms/submitEntryForm.hbs',
    './templates/receipts/createReceiptView.hbs',
    './templates/receipts/createReceiptEntryRow.hbs',
    './templates/forms/createEntryForm.hbs',
    './templates/forms/createReceiptForm.hbs',
    './templates/receipts/allReceiptView.hbs',
    './templates/receipts/allReceiptDetailsRow.hbs',
    './templates/receipts/receiptDetailsView.hbs',
    './templates/receipts/receiptDetailsEntryRow.hbs',
];

/*
Routes as parameters of constant 'authRoutes'
 */
// common routes
const authRoutes = {
    index: 'index.html', // this
    welcome: '#/welcome', // and this should be the same
    register: '#/register', // to show register form
    login: '#/login', // tho show login form
    logout: '#/logout', // to logout user
    home: '#/home', // for logged in users,
    profile: '#/profile', // open usr profile
};
// custom routes
const customRoutes = {
    createEntity: `#/${entities}/create`, // ... create entity route
    deleteEntity: `#/${entities}/delete/:entityId`, // .. deleting entity route
    overviewEntities: `#/${entities}/overview`, // ... review all entries
    entityDetails: `#/${entities}/details/:entityId`, // ... get data for specific entity
    createEntry: `#/${entries}/create`, // .. create entry route
    deleteEntry: `#/${entries}/delete/:entryId`, // .. delete entry route
    entryDetails: `#/${entries}/details/:entryId`, // .. get entry detailsroute
    // TODO: revise/add/remove/edit custom routes
};

// and few messages probably
