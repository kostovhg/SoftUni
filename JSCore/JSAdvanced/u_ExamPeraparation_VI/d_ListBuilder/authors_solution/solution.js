function listBuilder(selector) {
	return {
		createNewList: function() {
			let ul = $("<ul>");
			$(selector).empty();
			$(selector).append(ul);
		},
		addItem: function(text) {
			let li = $("<li>").text(text);
			li.append($("<button>Up</button>").click(this.buttonUp));
			li.append($("<button>Down</button>").click(this.buttonDown));
			$(selector + " ul").append(li);
		},
		buttonUp: function() {
			let li = $(this).parent();
			li.insertBefore(li.prev());
		},
		buttonDown: function() {
			let li = $(this).parent();
			li.insertAfter(li.next());
		}
	}
}