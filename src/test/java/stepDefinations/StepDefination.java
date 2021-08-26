package stepDefinations;

import org.testng.Assert;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bdd.APIFramework.JsonBody;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	 HashMap<String, String> rqheader = new HashMap<String, String>();
	 HashMap<String, String> rqquery = new HashMap<String, String>();
	

@Given("Add Place Payload with {string}  {string} {string}")
public void add_Place_Payload_with(String Status, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	
		 
		 res=given().spec(requestSpecification())	
		.body(data.addPlacePayLoad(Status,language,address));
		 System.out.println(res);
		 System.out.println("DATA is +++ " + Status);
		String obj12 =  ReUsableMethods.getreqBodyAsstring("Basebody.json");
		 System.out.println("The data obj is ****" +obj12);
		 String obj = new String( Files.readAllBytes(Paths.get("C:\\API Framework Copy\\APIFramework\\src\\test\\java\\bdd\\APIFramework\\Basebody.json")));
	//	 Files.readAllBytes(Paths.get("C:\\API Framework Copy\\APIFramework\\src\\test\\java\\bdd\\APIFramework\\Basebody.json"));
              //MY CODE
		/* RestAssured.baseURI= "https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	//.body(payload.AddPlace())  JsonBody
	//.body(JsonBody.AddPlace())
	.body(obj12)
		//	.body(ReUsableMethods.getreqBodyAsstring("Basebody.json"))
	.when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200)
	.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString(); */
		 
		rqheader.put("Content-Type","application/json");
		 rqquery.put ("key", "qaclick123");
		 
		//String response =  Utils.httpPost(obj12);
		String response = Utils.httpPostnew(obj12, rqheader, rqquery);
		
		System.out.println("********HAAHAAAAAAAAAAAA");
		System.out.println(response);
		
	
	System.out.println("********");
	System.out.println(response);
	/*JsonPath js=new JsonPath(response); //for parsing Json
	String placeId=js.getString("place_id");
	String status=js.getString("status");
	System.out.println("***************");
	System.out.println(placeId);
	System.out.println("Status is " + status);*/
	//JsonPath js=new JsonPath(response); 
	String PlaceID = ReUsableMethods.rawToJson(response,"place_id");
	System.out.println("Place id  is ++++++++ = " +PlaceID);
	System.out.println("***************");
	String StatusValue = ReUsableMethods.rawToJson(response,"status");
	System.out.println("Status is &&&&&&&&& = " + StatusValue);
	System.out.println("Thanks");
	Assert.assertEquals(StatusValue,Status);
//	Assert.assertEquals(PlaceID, "OK");
	
	}
@When("user calls http requestdata")
public void user_calls_http_requestdata() {
	System.out.println("Thanks for this");
}

@Given("Add Place Payload with the {string}")
public void add_Place_Payload_with_the(String Status) throws IOException {
	System.out.println("++++Start 2nd one +++++");
	System.out.println("DATA is +++ " + Status);
	String obj12 =  ReUsableMethods.getreqBodyAsstring("Basebody.json");
	 System.out.println("The data obj is ****" +obj12);
	// String obj = new String( Files.readAllBytes(Paths.get("C:\\API Framework Copy\\APIFramework\\src\\test\\java\\bdd\\APIFramework\\Basebody.json")));
	 rqheader.put("Content-Type","application/json");
	 rqquery.put ("key", "qaclick123");
	String response = Utils.httpPostnew(obj12, rqheader, rqquery);
	System.out.println(response);
	String PlaceID = ReUsableMethods.rawToJson(response,"place_id");
	System.out.println("Place id  is ++++++++ = " +PlaceID);
	System.out.println("***************");
	String StatusValue = ReUsableMethods.rawToJson(response,"status");
	System.out.println("Status is &&&&&&&&& = " + StatusValue);
	System.out.println("Thanks");
	Assert.assertEquals(StatusValue,Status);
}

@When("User call demo data")
public void user_call_demo_data() {
	System.out.println("Thanks for this 2nd steps data ***********************");
}
public String jsonbodydata;
@Given("the input request {string} payload")
public void the_input_request_payload(String jsonbody) throws IOException {
	System.out.println(jsonbody);
	jsonbodydata =  ReUsableMethods.getreqBodyAsstring(jsonbody);
	 System.out.println("The data obj is ****" +jsonbodydata);
}
public Response responsedata;
public String responsejsonstring;
@When("Brand api is invoke via POST operation")
public void brand_api_is_invoke_via_POST_operation() throws IOException {
	rqheader.put("Content-Type","application/json");
	 rqquery.put ("key", "qaclick123");
	 responsedata = Utils.httpPostdata(jsonbodydata, rqheader, rqquery);
	System.out.println("************************");
	System.out.println(responsedata.asString());
	responsejsonstring= responsedata.asString();
	
}

@Then("api response return {string}")
public void api_response_return(String ExpectedStatusCode) {
	System.out.println("Expected Status code is" +ExpectedStatusCode);
	int ActualStatus = responsedata.getStatusCode();
	String ActualStatusCode=Integer.toString(ActualStatus);
	System.out.println("Actual Status code is" +ActualStatusCode);
	//Assert.assertEquals(ExpectedStatusCode,ActualStatusCode);
	Assert.assertEquals(ActualStatusCode, ExpectedStatusCode);

}
@Then("api response returns attribute {string} has a value {string}")
public void api_response_returns_attribute_has_a_value(String JsonPath, String nodeValue) {
	System.out.println("***************");
	System.out.println("JsonPath is" +JsonPath);
	System.out.println("JsonPath is" +nodeValue);
	System.out.println("Response in output" +responsedata.asString());
	String ActualValue = ReUsableMethods.rawToJson(responsejsonstring,JsonPath);
	System.out.println("Status is &&&&&&&&& = " + ActualValue);
	Assert.assertEquals(ActualValue, nodeValue);
}



@When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
//constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		 response =res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resourceAPI.getResource());
		
}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
	
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	  
	 AssertJUnit.assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	
	   // requestSpec
	     place_id=getJsonPath(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		 user_calls_with_http_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  AssertJUnit.assertEquals(actualName,expectedName);
		 
	    
	}
	

@Given("DeletePlace Payload")
public void deleteplace_Payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
   
	res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
}



}
