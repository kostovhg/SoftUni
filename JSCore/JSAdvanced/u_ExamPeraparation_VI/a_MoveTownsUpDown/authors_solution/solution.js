function move(direction) {
	let townToMove = $('#towns option:selected');
	if (direction == -1) {
		townToMove.insertBefore(townToMove.prev());
	}
	if (direction == +1) {
		townToMove.insertAfter(townToMove.next());
	}
}