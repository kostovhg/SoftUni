function attachEvents() {

    //sessionStorage.clear();

    const KINVEY_BASE_URL = "https://baas.kinvey.com/";
    const KINVEY_APP_ID = "kid_SJRn6W6QX";
    const KINVEY_APP_SECRET = "????";
    const KINVEY_GUEST_USERNAME = "guest";
    const KINVEY_POSTS_COLLECTION = `${KINVEY_BASE_URL}appdata/${KINVEY_APP_ID}/posts`;
    const KINVEY_COMMENTS_COLLECTION = `${KINVEY_BASE_URL}appdata/${KINVEY_APP_ID}/comments`;
    const KINVEY_USERS_COLLECTION = `${KINVEY_BASE_URL}user/${KINVEY_APP_ID}/`;
    const KINVEY_APP_AUTH_HEADERS = (username = KINVEY_GUEST_USERNAME, password = KINVEY_GUEST_USERNAME) => {
        return {
            'Authorization': "Basic " +
                btoa(username + ":" + password)
        };
    };

    let [selectDropdown, postTitle, postBody, postComments] = [
        $('#posts'),
        $('#post-title'),
        $('#post-body'),
        $('#post-comments'),
    ];

    function registerUser(user, pass) {
        let userData = {
            username: user,
            password: pass
        };
        $.ajax({
            method: "POST",
            url: KINVEY_USERS_COLLECTION,
            headers: KINVEY_APP_AUTH_HEADERS(KINVEY_APP_ID, KINVEY_APP_SECRET),
            data: userData,
        }).then(function (res) {
            console.log(result)
        }).catch(function (err) {
            console.log(err)
        })
    }

    function getUserId(user) {
        $.ajax({
            method: "GET",
            url: `${KINVEY_BASE_URL}user/${KINVEY_APP_ID}/`
        }).then(function (res) {
            for (let users of res) {
                if (users.username === user) {
                    return users._id;
                }
            }
            throw new Error('No such user')
        }).catch(function (err) {
            console.log(err)
        })
    }

    function createPost(title, body, authorUsername = KINVEY_GUEST_USERNAME, authorPass = KINVEY_GUEST_USERNAME) {
        let postData = {
            title: title,
            body: body
        };
        $.ajax({
            method: "POST",
            url: KINVEY_POSTS_COLLECTION,
            headers: KINVEY_APP_AUTH_HEADERS(),
            data: postData
        }).then(function (res) {
            console.log(res)
        }).catch(function (err) {
            console.log(err)
        })
    }

    function clearFields() {
        postTitle.text('Post Details');
        postBody.empty();
        postComments.empty();
    };
    // registerUser('peter', 'p');
    // createPost('Post3', 'Post #3 body', KINVEY_GUEST_USERNAME, KINVEY_GUEST_USERNAME);
    // createPost('Post1', 'Post #1 body', KINVEY_GUEST_USERNAME, KINVEY_GUEST_USERNAME);
    // createPost('Post2', 'Post #2 body', KINVEY_GUEST_USERNAME, KINVEY_GUEST_USERNAME);


    $('#btnLoadPosts').on('click', function () {
        $.ajax({
            method: "GET",
            url: KINVEY_POSTS_COLLECTION,
            headers: KINVEY_APP_AUTH_HEADERS()
        }).then(displayPosts).catch(displayError)
    });

    $('#btnViewPost').on('click', function () {

            let selectedPostId = selectDropdown.find(":selected").val();

            let getSelectedPost = $.ajax({
                method: "GET",
                url: `${KINVEY_POSTS_COLLECTION}/${selectedPostId}`,
                headers: KINVEY_APP_AUTH_HEADERS()
            });

            let getPostComments = $.ajax({
                method: "GET",
                url: `${KINVEY_COMMENTS_COLLECTION}/?query={"post_id":"${selectedPostId}"}`,
                headers: KINVEY_APP_AUTH_HEADERS()
            });


            Promise.all([getSelectedPost, getPostComments]).then(displayPostsInfo).catch(displayError)
        }
    );

    function displayPosts(posts) {
        selectDropdown.empty();
        //console.log(res);
        for (let post of posts) {
            selectDropdown.append($('<option>').val(post._id).text(post.title))
        }
    }

    function displayPostsInfo([postInfo, comments]){

        clearFields();

        postTitle.text(postInfo.title);
        postBody.text(postInfo.body);

        for (let comment of comments) {
            $('#post-comments').append($('<li>').text(comment.text));
        }
    }

    function displayError(err) {
        clearFields();
        let errorDiv = $('<div>').text(`Error: ${err.status} (${err.statusText})`);
        $(document.body).prepend(errorDiv);

        setTimeout(() => errorDiv.fadeOut(() => errorDiv.remove()), 3000);
    }
}