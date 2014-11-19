
function isbnSubmitbyRest() {
	var isbn = $('#inputISBN').val();
	if(isbn.length == 13){
		var redirectURL = 'http://' + location.host + '/restService/result/' + isbn;
		window.location.href = redirectURL;
	}
}

function isbnSubmitbySOAP() {
	var isbn = $('#inputISBN').val();
	if(isbn.length == 13){
		var redirectURL = 'http://' + location.host + '/restService/result/' + isbn;
		window.location.href = redirectURL;
	}
}