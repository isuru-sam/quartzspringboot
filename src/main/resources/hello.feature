Feature: hello
Given url 'http://localhost:9080/api/hello'
When method get
Then status 200