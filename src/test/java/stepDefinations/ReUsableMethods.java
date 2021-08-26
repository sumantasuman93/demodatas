package stepDefinations;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	
	public static String rawToJson(String response, String Jsonpath)
	{
		JsonPath js1 =new JsonPath(response);
		String PathValue = js1.getString(Jsonpath);
		return PathValue;
	}
	
	public static String getreqBodyAsstring(String Bodypath) throws IOException
	{
		 //String obj = new String( Files.readAllBytes(Paths.get("C:\\API Framework Copy\\APIFramework\\src\\test\\java\\bdd\\APIFramework\\Basebody.json")));
		 String obj = new String( Files.readAllBytes(Paths.get("C:\\API Framework Copy\\APIFramework\\src\\test\\java\\bdd\\APIFramework\\"+Bodypath+"")));
		 return obj;
	}
	

}
