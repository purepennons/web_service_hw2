
function isbnSubmit() {
	var isbn = $('#inputISBN').val();
	// console.log(location.href.charAt(location.href.length-1) == '/');
	var redirectURL = 'http://' + location.host + '/restService/result/' + isbn;
	console.log(redirectURL);
	// var redirectURL = (location.href.charAt(location.href.length - 1) == '/')? location.href + 'result/' + isbn : location.href + '/result/' + isbn;
	window.location.href = redirectURL;
}