function getArticleGenerator(articles){
    return function() {
        let content = $("#content");
        if (articles.length > 0){
            content.append($(`<article>${articles.shift()}</article>`))
        }
    }
}