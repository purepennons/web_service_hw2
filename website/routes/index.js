var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.render('index', { title: 'Web Service HW2: REST & SOAP Services' });
});

module.exports = router;
