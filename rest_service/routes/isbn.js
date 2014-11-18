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
	var sql = 'SELECT * FROM book WHERE isbn = ' + connection.escape(String(isbn));
	
	res.setHeader('Content-Type', 'application/json');

	if(isbn.length != 13){
		res.status(400);
		res.end(JSON.stringify({"status": "error"}));
	}

  connection.query(sql, function(err, rows) {
		// connected! (unless `err` is set)
		console.log(rows);
		res.end();
	});
});

module.exports = router;
