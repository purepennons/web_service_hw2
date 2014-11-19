var express = require('express');
var mysql = require('mysql');
var config = require('../config.json');

var router = express.Router();

var dbConfig = {
    host: config.host,
    user: config.user,
    password: config.password,
    database: config.database	
};

var connection = mysql.createConnection(dbConfig);

connection.connect(function(err) {
  if (err) {
    console.error('error connecting: ' + err.stack);
    return;
  }
  console.log('connected as id ' + connection.threadId);
});  

/* GET users listing. */
router.get('/:isbn', function(req, res) {
	var isbn = req.params.isbn;
	var sql = 'SELECT * FROM ' + config.table +  ' WHERE isbn = ' + connection.escape(String(isbn));
	res.setHeader('Content-Type', 'application/json');

	if(isbn.length != 13){
		res.status(400);
		res.end(JSON.stringify({"status": "error", "result": "wrong args"}));
	}

  connection.query(sql, function(err, rows) {
  	if(err){
			res.status(400);
			res.end(JSON.stringify({"status": "error"}));  		
  	}
  		var returnJSON = {};
  		returnJSON.status = 'success';
  		returnJSON.result = rows;
		res.status(200);
		res.write(JSON.stringify(returnJSON));
		res.end();
	});
});

module.exports = router;
