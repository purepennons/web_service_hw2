var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.render('soap_service', { title: 'SOAP Service' });
});

module.exports = router;
