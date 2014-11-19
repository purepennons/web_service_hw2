var express = require('express');
var http = require('http');
var soap = require('soap');
var config = require('../config.json');

var router = express.Router();

router.get('/:isbn', function(req, res) {
	var isbn = req.params.isbn;

	if(isbn.length != 13){
		res.status(400);
		res.send('Fail to query!');
		res.end();		
	}else {

	  var url = 'http://' + config.soap_host + config.service_path + '?wsdl';
	  var args = {
	  	isbn: isbn
	  };
	  soap.createClient(url, function(err, client) {
	      client.queryISBN(args, function(err, result) {
	      	var resultString = JSON.stringify(result['return']).replace(/'/g, '"');
	      	var resultString = resultString.substring(1, resultString.length-1);

			  	var recDataJSON = JSON.parse(resultString);

			  	if(recDataJSON.status === 'success'){
	  				res.render('service_result', recDataJSON.result[0]);
	  			}else {
	  				return;
	  			}

	      });
	  });	
	}
});

module.exports = router;
