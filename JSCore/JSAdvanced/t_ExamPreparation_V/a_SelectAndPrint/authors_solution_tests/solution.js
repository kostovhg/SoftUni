function move(direction) {
    if (direction === 'right') {
        let townsToMove = $('#available-towns').find('option:selected');
        townsToMove.prop('selected', false);
        $('#selected-towns').append(townsToMove);
    }
    if (direction === 'left') {
        let townsToMove = $('#selected-towns').find('option:selected');
        townsToMove.prop('selected', false);
        $('#available-towns').append(townsToMove);
    }
    if (direction === 'print') {
        let townsToPrint = $('#selected-towns').find('option').toArray().map(o => o.textContent);
        $('#output').text(townsToPrint.join('; '));
    }
}