
function isbnSubmit() {
	var isbn = $('#inputISBN').val();
	window.location.href = location.href + '/result/' + isbn;
}