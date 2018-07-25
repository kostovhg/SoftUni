/**
 * Source : https://github.com/Tuscann/Softuni/blob/master/JS%20core/JavaSrpt%20Apps/04.Promises%20LAB/02.Blog.js
 *
 */
function attachEvents() {
    let postsUrl = 'https://baas.kinvey.com/appdata/kid_SJRn6W6QX/posts/';
    let commentsUrl = 'https://baas.kinvey.com/appdata/kid_SJRn6W6QX/comments/';
    let auth = {'Authorization': 'Basic ' + btoa('peter:p')};
    let [selectPost, postTitle, postBody, postComments] = [
        $('#posts'), $('#post-title'), $('#post-body'), $('#post-comments')
    ];

    $('#btnLoadPosts').click(function () {
        $.get({url: postsUrl, headers: auth}).then(displayPosts).catch(displayError);
    });

    $('#btnViewPost').click(function () {
        let selectedPostId = selectPost.val();
        if (!selectedPostId) return;

        let postsRequest = $.get({url: postsUrl + selectedPostId, headers: auth});
        let commentsRequest = $.get({url: commentsUrl + `?query={"post_id":"${selectedPostId}"}`, headers: auth});

        Promise.all([postsRequest, commentsRequest]).then(displayPostInfo).catch(displayError);
    });

    function displayPosts(posts) {
        selectPost.empty();

        for (let post of posts) {
            selectPost.append($('<option>').text(post.title).val(post._id));
        }
    }

    function displayPostInfo([postInfo, comments]) {
        postTitle.text(postInfo.title);
        postBody.text(postInfo.body);

        //console.log(postInfo.title);

        postComments.empty();

        for (let comment of comments) {
            postComments.append($('<li>').text(comment.text));
        }
    }

    function displayError(error) {
        let errorDiv = $('<div>').text(`Error: ${error.status} (${error.statusText})`);
        $(document.body).prepend(errorDiv);

        setTimeout(() => errorDiv.fadeOut(() => errorDiv.remove()), 3000);
    }
}