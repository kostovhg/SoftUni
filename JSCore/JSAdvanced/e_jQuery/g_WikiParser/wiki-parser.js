function wikiParser(selector) {

    let element = $(selector);
    let text = element.text();

    let patterns = {
        h3: /===(.+?)===/g,
        h2: /==(.+?)==/g,
        h1: /=(.+?)=/g,
        b: /'''(.*?)'''/g,
        i: /''(.+?)''/g,
        a: /\[\[([^\[\]]+?)((?:\|)(.*?))?\]\]/g
    };

    for (let tag of Object.getOwnPropertyNames(patterns)) {
        text = text.replace(patterns[tag], (m, ...group) => {
                return (tag === 'a') ?
                    `<${tag} href="/wiki/${group[0]}">${(group[2]) ? group[2] : group[0]}</${tag}>` :
                    `<${tag}>${group[0]}</${tag}>`
            }
        );
    }
    element.html(text);
}
