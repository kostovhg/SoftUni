function extractText() {
    document.getElementById('result').value =
        [...document.querySelectorAll('ul#items li')]
            .map(l => l.textContent)
            .join('\n')
}