function extractText() {
   $('#result').text([...$('#items li')].map(x => $(x).text()).join(', '))
}
