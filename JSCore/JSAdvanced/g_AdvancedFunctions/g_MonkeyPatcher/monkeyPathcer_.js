function monkeyPatcher(action) {

    let that = this;

    let patcher = (function () {
        let actions = {
            'upvote': () => that.upvotes++,
            'downvote': () => that.downvotes++,
            'score': () => {
                let [up, down] = [that.upvotes, that.downvotes];
                let [score, total] = [up - down, up + down];
                let add = total > 50 ? Math.ceil(Math.max(up, down) * 0.25) : 0;
                let rating = total < 10 ? 'new' :
                    score < 0 ? 'unpopular' :
                        (up / total > 0.66) ? 'hot' :
                            (up > 100 || down > 100) ? 'controversial' :
                                'new';
                return [up + add, down + add, score, rating];
            }
        };
        return actions[action];
    }());

    return patcher();
}

let solution = monkeyPatcher;

let post = {
    id: '1',
    author: 'pesho',
    content: 'hi guys',
    upvotes: 0,
    downvotes: 0
};

solution.call(post, 'upvote');
console.log(solution.call(post, 'score'));