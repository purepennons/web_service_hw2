var express = require('express');
var http = require('http');
var config = require('../config.json');

var router = express.Router();

router.get('/:isbn', function(req, res) {
	var isbn = req.params.isbn;

	if(isbn.length != 13){
		res.status(400);
		res.send('Fail to query!');
		res.end();		
	}else {

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
			  	var recDataJSON = JSON.parse(recData);
			  	if(recDataJSON.status === 'success'){
	  				res.render('service_result', recDataJSON.result[0]);
	  			}else {
					res.status(400);	  				
	  				res.send('Fail to query!');
	  				res.end();
	  			}
			  }); 

			} else {
				res.status(response.statusCode);
				res.send('Fail to query!');
				res.end();
			}
		}).on('error', function(e) {
		  console.log("Got error: " + e.message);
		});
	}
});

module.exports = router;
