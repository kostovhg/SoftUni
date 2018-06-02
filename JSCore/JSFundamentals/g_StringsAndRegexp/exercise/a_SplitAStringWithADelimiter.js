function splitAStringWithADelimiter(input) {

    let [text, delimiter] = [arguments[0], arguments[1]];

    console.log(text.split(delimiter).join('\n'))

}

splitAStringWithADelimiter('One-Two-Three-Four-Five', '-');
splitAStringWithADelimiter('http://platform.softuni.bg', '.');