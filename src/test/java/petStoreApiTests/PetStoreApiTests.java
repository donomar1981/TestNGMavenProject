package petStoreApiTests;

import java.io.File;

import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreApiTests {
	
	int catID;
	int petTD;
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	}
	
  @Test (dependsOnMethods = "postAPet")
  public void getPetById() {
	  
	  RestAssured
	  .given().accept(ContentType.JSON)
	  .get("/pet/1337")
	  .then().statusCode(200);
	  
	 
  }
  
  @Test
  public void findPetByStatus() {
	  RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json").param("status", "pending")
	  .when().get("/pet/findByStatus")
	  .then().statusCode(200)
	  .and().contentType("application/json");
	  
  }
  
  @Test (dependsOnMethods = {"postACat", "updateCat"})
  public void getById() {
	  
	  Response myResponse=RestAssured
	  .given().accept(ContentType.JSON)
	  .when().get("/pet/2323448");
	  
	  myResponse.prettyPrint();
	  //verifying the status code.
	  myResponse.then()
	  .assertThat().statusCode(200)
	  .and().contentType("application/json");
	  
	 String petName= myResponse.path("name");
	 System.out.println("Pet name is : " +  petName);
	 Assert.assertEquals(petName, "jemy");
	 
	 int petId=myResponse.body().path("id");
	 System.out.println("Pet id is : " + petId);
	 Assert.assertEquals(petId, 2323448);
	 
	 int tagsId=myResponse.path("tags[0].id");
	 System.out.println("Pet tage are : " + tagsId);
	 Assert.assertEquals(tagsId, 38);
	 
	 //how to access the tags name from the second object
	 
	 String tags2Name=myResponse.path("tags[1].name");
	 System.out.println("Pet second tag are : " + tags2Name);
	 Assert.assertEquals(tags2Name, "yourkshair");
	 
	 //using jsonpath function
	 
	 String categoryName=myResponse.jsonPath().get("category.name");
	 System.out.println("Pet category name is : " + categoryName);
	 Assert.assertEquals(categoryName, "dog");  
	 
	 String catStatus=myResponse.body().jsonPath().get("status");
	 Assert.assertEquals(catStatus, "available");
	  
  }
  
  @Test
  public void postACat() {
	  String catRequestBody="{\n" + "    \"id\": 353234,\n"	+ "    \"category\": {\n" + "        \"id\": 0,\n"	+ "        \"name\": \"cat\"\n"
	  		+ "    },\n" + "    \"name\": \"Ghost\",\n"	+ "    \"photoUrls\": [\n"	+ "        \"string\"\n" + "    ],\n"
	  		+ "    \"tags\": [\n"	+ "        {\n"	+ "            \"id\": 11,\n"	+ "            \"name\": \"egybt\"\n"	+ "        },\n"
	  		+ "        {\n"	+ "            \"id\": 3,\n" + "            \"name\": \"Anatolian\"\n"	+ "        }\n"	+ "    ],\n"
	  		+ "    \"status\": \"available\"\n"	+ "}";
	  
	  Response myResponse=RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json")
	  .body(catRequestBody)
	  .when().post("/pet");
	  
	  myResponse.then().statusCode(200)
	  .and().contentType("application/json");
	  
	  myResponse.prettyPrint();
	  
	  catID=myResponse.jsonPath().get("id");
  }
  
  //update the cat status to pending
  
  @Test(dependsOnMethods = "postACat")
  public void updateCat() {
	  String catRequestBody="{\n"	+ "    \"id\": 353234,\n" 		+ "    \"category\": {\n"
	  		+ "        \"id\": 17,\n"		+ "        \"name\": \"cat\"\n"		+ "    },\n"		+ "    \"name\": \"Ghost\",\n"
	  		+ "    \"photoUrls\": [\n"		+ "        \"string\"\n"		+ "    ],\n"	+ "    \"tags\": [\n"		+ "        {\n"
	  		+ "            \"id\": 38,\n"	+ "            \"name\": \"golden\"\n"	+ "        },\n"	+ "        {\n"
	  		+ "            \"id\":34,\n"	+ "            \"name\":\"jemy\"\n"	+ "        }\n"		+ "    ],\n"
	  		+ "    \"status\": \"pending\"\n"
	  		+ "}";
	  
	  
	 Response catResponse= RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json").body(catRequestBody)
	  .when().put("/pet");
	 
	 catResponse.then().statusCode(200).and().contentType("application/json");
	 Assert.assertEquals(catResponse.body().jsonPath().get("status"), "pending");
	 
	 
	  
  }
  
  
  
  @Test
  public void postAPet() {
	  
	  String requestBody="{\n"	+ "    \"id\": 2323448,\n"	+ "    \"category\": {\n" + "        \"id\": 17,\n"	+ "        \"name\": \"dog\"\n"
	  		+ "    },\n" + "    \"name\": \"jemy\",\n"	+ "    \"photoUrls\": [\n"	+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n"
	  		+ "        {\n"	+ "            \"id\": 38,\n" + "            \"name\": \"golden\"\n" + "        },\n" + "        {\n"
	  		+ "            \"id\":34,\n" + "            \"name\":\"yourkshair\"\n"	+ "        }\n"	+ "    ],\n" 
	  		+ "    \"status\": \"available\"\n"	+ "}";
	  
	  
	  Response myResponse=RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
	  .when().post("/pet");
	  
	  myResponse.then().statusCode(200)
	  .and().contentType("application/json");
	  
	  myResponse.prettyPrint();
	  
	  petTD=myResponse.jsonPath().get("id");
  }
  
  public void deleteThePet() {
	  Response deleteResponse=RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json")
	  .when().delete("/pet/" + petTD);
	  
	  deleteResponse.then().statusCode(200).contentType("application/json");
	  Assert.assertEquals(deleteResponse.body().jsonPath().get("message"), String.valueOf(petTD));
  }
  
  public void deleteTheCat() {
	  Response deleteResponse=RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json")
	  .when().delete("/pet/" + catID);
	  
	  deleteResponse.then().statusCode(200).contentType("application/json");
	  Assert.assertEquals(deleteResponse.body().jsonPath().get("message"), String.valueOf(catID));
	  
	  
  }
  
  //create a cat with request body in json file - example
  
  @Test
  public void createCatWithJsonFile() {
	  
	  File catRequestBodyFile=new File("./src/test/resources/JsonTestData/createCat.json");
	  
	  Response myResponse=RestAssured
			  .given().accept(ContentType.JSON).contentType("application/json")
			  .body(catRequestBodyFile)
			  .when().post("/pet");
			  
			  myResponse.then().statusCode(200)
			  .and().contentType("application/json");
			  
			  myResponse.prettyPrint();
			  
			  catID=myResponse.jsonPath().get("id");
	  
  }
  
  // RestAssured chain validation
  @Test
  public void chainValidation() {
 File catRequestBodyFile=new File("./src/test/resources/JsonTestData/createCat.json");
	  
	  Response myResponse=RestAssured
			  .given().accept(ContentType.JSON).contentType("application/json")
			  .body(catRequestBodyFile)
			  .when().post("/pet");
			  
			  myResponse
			  .then().assertThat().statusCode(200)
			  .and().contentType("application/json")
			  .and().assertThat().body("id", equalTo(2323448))
			  .and().assertThat().body("category.id", equalTo(17))
			  .and().assertThat().body("category.name", equalTo("cat"))
			  .and().assertThat().body("name", equalTo("Ghost"))
			  .and().assertThat().body("tags[0].id", equalTo(28))
			  .and().assertThat().body("tags[0].name", equalTo("persian"))
			  .and().assertThat().body("tags[1].id", equalTo(24))
			  .and().assertThat().body("tags[1].name", equalTo("Anatolian"))
			  .and().assertThat().body("status", equalTo("available"));
			  
			  myResponse.prettyPrint();
			  
			  catID=myResponse.jsonPath().get("id");
  }
  
  @AfterTest
  public void cleanup() {
	  deleteTheCat();
	  deleteThePet();
  }
  // negative test cases
  @Test
  public void invalidIdUpdateCat() {
	  String catRequestBody="{\n"	+ "    \"id\": '87345',\n" 		+ "    \"category\": {\n"
	  		+ "        \"id\": 17,\n"		+ "        \"name\": \"cat\"\n"		+ "    },\n"		+ "    \"name\": \"Ghost\",\n"
	  		+ "    \"photoUrls\": [\n"		+ "        \"string\"\n"		+ "    ],\n"	+ "    \"tags\": [\n"		+ "        {\n"
	  		+ "            \"id\": 38,\n"	+ "            \"name\": \"golden\"\n"	+ "        },\n"	+ "        {\n"
	  		+ "            \"id\":34,\n"	+ "            \"name\":\"jemy\"\n"	+ "        }\n"		+ "    ],\n"
	  		+ "    \"status\": \"pending\"\n"
	  		+ "}";
	  
	  
	 Response catResponse= RestAssured
	  .given().accept(ContentType.JSON).contentType("application/json").body(catRequestBody)
	  .when().put("/pet");
	 
	 catResponse.then().statusCode(400).and().contentType("application/json");
	 catResponse.prettyPrint();
	 Assert.assertEquals(catResponse.body().jsonPath().get("message"), "bad input");
	 
	 
	 
	  
  }
  
  
}
