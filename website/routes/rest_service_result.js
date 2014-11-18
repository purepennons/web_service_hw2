var express = require('express');
var http = require('http');
var config = require('../config.json');

var router = express.Router();

router.get('/:isbn', function(req, res) {
	var isbn = req.params.isbn;

	if(isbn.length != 13){
		res.status(400);
	}
	
	http.get('http://' + config.rest_host + '/book/' + isbn, function(response) {
		var recData = '';
		response.setEncoding('utf8');
	  if(response.statusCode === 200){

		  //receive data by chunk
		  response.on('data', function (chunk) {
		  	recData += chunk;
		  });

		  //wait all data to be received
		  response.on('end', function(){
  			res.render('rest_service_result', JSON.parse(recData)[0]);
		  }); 

		} else {

		}
	}).on('error', function(e) {
	  console.log("Got error: " + e.message);
	});
});

module.exports = router;
