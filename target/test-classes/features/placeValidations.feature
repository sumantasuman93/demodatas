Feature: Validating Place API's
@AddPlace 
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<Status>"  "<language>" "<address>"
	When user calls http requestdata
	
	
Examples:
	|Status 	 | language |address		   |
	|OK |  English |World cross center|
	
@Funtionaltest
Scenario Outline: Verify if Place functionality is working properly or not 

	Given Add Place Payload with the "<Status>" 
	When User call demo data
	
	Examples:
	|Status 	 | 
	|OK |
	

@Funtionaltest1
Scenario Outline: Validate getparty search pagination response
	Given the input request "Basebody.json" payload 
	When Brand api is invoke via POST operation
	Then api response return "200"
	Then api response returns attribute "<responseAttribute>" has a value "<nodeValue>"
	Examples:
	|responseAttribute	  |nodeValue   |
	|status               |  OK        |
	|scope                |  APP       |
	  
	
	




	 


	

	
	
	
	
	
	

	
	
	