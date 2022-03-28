Feature: Quote generator
 
    Background:
        * url 'http://localhost:8090'
    
    Scenario: Fetch random quote
    
        Given path 'api/hello'
        When method GET
        Then status 200
        And match $ == {quote:'#notnull'} 