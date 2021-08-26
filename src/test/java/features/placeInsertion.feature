Feature: Validating Brand API
	

@NUO40
Scenario Outline: Validate Brand API data
	Given the input request "Basebody.json" payload 
	When Brand api is invoke via POST operation
	Then api response return "200"
	Then api response returns attribute "<responseAttribute>" has a value "<nodeValue>"
	Examples:
	|responseAttribute	  |nodeValue   |
	|status               |  OK        |
	|scope                |  APPP       |
	  
	
	




	 


	

	
	
	
	
	
	

	
	
	