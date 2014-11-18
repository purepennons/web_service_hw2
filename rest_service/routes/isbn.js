var express = require('express');
var config = require('../config.json');

var router = express.Router();

var dbConfig = {
    host: config.host,
    user: config.user,
    password: config.password,
    database: config.database	
};

console.log(dbConfig);

/* GET users listing. */
router.get('/:isbn', function(req, res) {
  res.send('respond with a resource');
});

module.exports = router;
