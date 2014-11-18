var ToolsImmed =  (function() {
	var Tools = function(){

		// http/https request function 
		// httpType: http/https
		this.httpRequest = function(httpType, options, cb, errCb){
			var req = require(httpType).request(options, function(res){
				var recData = '';
				res.setEncoding('utf8');
			  if(res.statusCode === 200){

				  //receive data by chunk
				  res.on('data', function (chunk) {
				  	recData += chunk;
				  });

				  //wait all data to be received
				  res.on('end', function(){
				  	cb(recData);
				  }); 

				} else {
					errCb(res.statusCode);
				}
			});

			req.on('error', function(e) {
		  		console.log('problem with request: ' + e.message);
			});
			req.end();
		};

		this.toUnixTime = function(date) {
			var d = new Date(date);
			return Math.round((d.getTime()/1000));
		};

	};

	return Tools;

})();

module.exports = ToolsImmed;