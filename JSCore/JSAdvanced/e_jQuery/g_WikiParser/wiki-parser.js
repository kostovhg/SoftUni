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

    Object.getOwnPropertyNames(patterns).forEach(tag =>
        text = text.replace(patterns[tag], (m, ...group) => (tag === 'a') ?
            `<${tag} href="/wiki/${group[0]}">${group[2] ? group[2] : group[0]}</${tag}>` :
            `<${tag}>${group[0]}</${tag}>`));
    element.html(text);
}
