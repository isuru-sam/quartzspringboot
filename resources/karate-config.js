function() {
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 120000);
  var config = {
		  
		 
		  couchbase_username : 'admin',
		  couchbase_password : 'password'
  };
  return config;
}