/**
 * Service module responsible for user authentication that
 * returns with reveal module pattern auth functionality
 * @type {{isAuth, saveSession, register, login, logout}}
 */
let auth = (() => {

    // checkoutForm if current user is logged in depending of sessionStorage value of 'authtoken'
    function isAuth(){
        return sessionStorage.getItem('authtoken') !== null;
    }

    // Check if currently logged in user is the creator of entity
    function isAuthor(entity){
        return sessionStorage.getItem('userId') === entity._acl.creator;
    }

    // save session
    function saveSession(data) {
        sessionStorage.setItem('userId', data._id);
        sessionStorage.setItem('username', data.username);
        sessionStorage.setItem('authtoken', data._kmd.authtoken);
        sessionStorage.setItem('subscriptions', data.subscriptions)
    }

    // User Registration (Sign Up)
    function register(username, password){
        let obj = {username, password};
        obj['subscriptions'] = [];
        return requester.post('user', '', 'basic', obj)
    }

    //  User Login
    function login(username, password) {
        let obj = {username, password};
        return requester.post('user', 'login', 'basic', obj);
    }

    // User Logout
    function logout(){
        return requester.post('user', '_logout', 'kinvey');
    }

    // Additional method for getting collection to session storage
    function updateCollection (subscriptions) {
        sessionStorage.setItem('subscriptions', subscriptions.toString())
    }

    return {
        isAuth,
        saveSession,
        register,
        login,
        logout,
        updateCollection
    }
})();