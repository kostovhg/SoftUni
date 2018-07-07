function posts() {

    class Post {
        constructor(title, content) {
            [ this.title, this.content ] = [ title, content ]
        }

        toString() {
            return `Post: ${this.title}\nContent: ${this.content}`
        }
    }

    class SocialMediaPost extends Post {
        constructor(title, content, likes, dislikes) {
            super(title, content);
            [ this.likes, this.dislikes, this.comments ] = [ likes, dislikes, [] ];
        }

        addComment(comment) {
            this.comments.push(comment);
        }

        toString() {
            return `${super.toString()}\nRating: ${this.likes - this.dislikes}` +
                `${this.comments.length > 0 ?
                    `\nComments:\n * ` + this.comments.join('\n * ') : ''}`;
        }
    }

    class BlogPost extends Post {
        constructor(title, content, views){
            super(title, content);
            this.views = views || 0;
        }
        view(){
            this.views++;
            return this;
        }
        toString() {
            return `${super.toString()}\nViews: ${this.views}`;
        }
    }

    return {Post: Post, SocialMediaPost: SocialMediaPost, BlogPost: BlogPost}
}

let classes = posts();
let Post = classes.Post;
let SocialMediaPost = classes.SocialMediaPost;
let post = new Post("Post", "Content");

console.log(post.toString());

// Post: Post
// Content: Content

let scm = new SocialMediaPost("TestTitle", "TestContent", 25, 30);

scm.addComment("Good post");
scm.addComment("Very good post");
scm.addComment("Wow!");

console.log(scm.toString());

// Post: TestTitle
// Content: TestContent
// Rating: -5
// Comments:
//  * Good post
//  * Very good post
//  * Wow!

let BlogPost = classes.BlogPost;

let test = new BlogPost('A blog post', 'Awesome content for the blog post', 20);
test.view().view().view().view();
console.log(test.toString());