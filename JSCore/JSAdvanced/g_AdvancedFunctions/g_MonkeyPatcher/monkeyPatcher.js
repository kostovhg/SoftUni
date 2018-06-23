function monkeyPatcher(action) {

    let score = () => {
        let [up, down] = [this.upvotes, this.downvotes];
        let [score, total] = [up - down, up + down];
        let add = total > 50 ? Math.ceil(Math.max(up, down) * 0.25) : 0;
        let rating = total < 10 ? 'new' :
            score < 0 ? 'unpopular' :
                (up/total > 0.66) ? 'hot' :
                    (up > 100 || down > 100) ? 'controversial' :
                        'new';
        return [up + add, down + add, score, rating]
    };

    switch (action) {
        case 'upvote':
            this.upvotes++;
            break;
        case 'downvote':
            this.downvotes++;
            break;
        case 'score':
            return score();
    }
}

let solution = monkeyPatcher;

// let post = {
//     id: '1',
//     author: 'pesho',
//     content: 'hi guys',
//     upvotes: 0,
//     downvotes: 0
// };
//
// solution.call(post, 'upvote');
// console.log(solution.call(post, 'score'));


let post = {
    id: '3',
    author: 'emil',
    content: 'wazaaaaa',
    upvotes: 100,
    downvotes: 100
};
solution.call(post, 'upvote');
solution.call(post, 'downvote');
let score = solution.call(post, 'score'); // [127, 127, 0, 'controversial']
console.log(score);
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
solution.call(post, 'downvote');
score = solution.call(post, 'score');
console.log(score);