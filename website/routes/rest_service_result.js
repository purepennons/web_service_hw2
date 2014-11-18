var express = require('express');
var http = require('http');
var Tools = require('../js_module/tools');
var config = require('../config.json');

var router = express.Router();
var tools = new Tools();

/* GET home page. */
router.get('/:isbn', function(req, res) {
	var isbn = req.params.isbn;
	var options = {
		hostname: 'localhost:3000',
		port: 80,
		method: 'GET',
		path: '/book/' + isbn
	};

	console.log(options);
	if(isbn.length != 13){
		res.status(400);
	}

	// tools.httpRequest('http', options, function(data) {
	// 	console.log(data);
	// 	res.render('service', { title: 'Rest Service' });
	// }, function(errCode) {

	// });
	
	http.get("http://localhost:3000/book/" + isbn, function(response) {
		var recData = '';
		response.setEncoding('utf8');
	  if(response.statusCode === 200){

		  //receive data by chunk
		  response.on('data', function (chunk) {
		  	recData += chunk;
		  });

		  //wait all data to be received
		  response.on('end', function(){
		  	console.log(recData);
		  	res.render('service', { title: 'Rest Service' });
		  }); 

		} else {

		}
	}).on('error', function(e) {
	  console.log("Got error: " + e.message);
	});
});

module.exports = router;
