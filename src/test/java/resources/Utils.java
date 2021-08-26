package resources;


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepDefinations.ReUsableMethods;

public class Utils {

	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
		
		
	}
	
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("C:\\API Framework Copy\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	
		
		
	}
	
	
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	

	public static String httpPost(String obj12) throws IOException
	{
		//RestAssured.baseURI= "https://rahulshettyacademy.com/maps/api/place/add/json";
		RestAssured.baseURI= getGlobalValue("baseUrl");
		String response=given().log().all()
				.queryParams("key", "qaclick123").headers("Content-Type","application/json")
		//.body(payload.AddPlace())  JsonBody
		//.body(JsonBody.AddPlace())
		.body(obj12)
				//.body(ReUsableMethods.getreqBodyAsstring(obj12))
		.when().post()
		.then().assertThat().statusCode(200)
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		return response;
	}
	
	public static String httpPostnew(String obj12,HashMap<String, String> rqheader,HashMap<String, String> rqquery) throws IOException
	{
		RestAssured.baseURI= getGlobalValue("baseUrl");
		String response=given().log().all()
				.queryParams(rqquery).headers(rqheader)
				
		.body(obj12).when().post()
		.then().assertThat().statusCode(200)
		//.header("server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		return response;
	}
	public static Response httpPostdata(String obj12,HashMap<String, String> rqheader,HashMap<String, String> rqquery) throws IOException
	{
		RestAssured.baseURI= getGlobalValue("baseUrl");
		Response response=given().log().all()
				.queryParams(rqquery).headers(rqheader)
				
		.body(obj12).when().post()
		.then()
		.extract().response();
		return response;
	}
}
