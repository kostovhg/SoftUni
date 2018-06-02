function extractTheLinks(input) {

    const regex = /(www\.([a-zA-Z0-9\-]+)(?:\.[a-z]+)+)/m;
    let result = [];

    for (let sentence of input) {
        let match = regex.exec(sentence);
        if (match) result.push(match[1])
    }

    console.log(result.join('\n'));
}

extractTheLinks([
    'Join WebStars now for free, at www.web-stars.com.',
    'You can also support our partners:',
    'Internet - www.internet.com',
    'WebSpiders - www.webspiders101.com',
    'Sentinel - www.sentinel.-ko'
]);


extractTheLinks([
    'Need information about cheap hotels in London?',
    'You can check us at www.london-hotels.co.uk!',
    'We provide the best services in London.',
    'Here are some reviews in some blogs:',
    '"London Hotels are awesome!" - www.indigo.bloggers.com',
    '"I am very satisfied with their services" - ww.ivan.bg',
    '"Best Hotel Services!" - www.rebel21.sedecrem.moc'
]);

