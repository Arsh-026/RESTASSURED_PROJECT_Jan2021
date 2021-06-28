package testCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_or_Read_A_Product {
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void read_A_Product() {
		
		Response response = 
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.headers("Content-Type","application/json; charset=UTF-8")
			.queryParams("id", "1650").
		when()
			.get("/read_one.php").
		then()
			.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status Code:" + statusCode);
		//Assert.assertEquals(statusCode, 200);
		softAssert.assertEquals(statusCode, 200);
		
//		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
//		System.out.println("Response Time: " + responseTime);
//		if(responseTime <= 200) {
//			System.out.println("Response time is within range!");
//		}else {
//			System.out.println("Response time is out of range!");
//		}
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: "+ responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String productId = jp.getString("id");
		System.out.println("Product Id: " + productId);
		//Assert.assertEquals(productId, "1650");
		softAssert.assertEquals(productId, "1650");
//		softAssert.assertEquals(productId, "1652", "Product id is not matching!");--(for failing test)
		
		String productName = jp.getString("name");
		System.out.println("Product Name: "+ productName);
		//Assert.assertEquals(productName, "iPad");
		softAssert.assertEquals(productName, "iPad");
		
		String productDescription = jp.getString("description");
		System.out.println("Product Description: "+ productDescription);
		//Assert.assertEquals(productName, "iPad");
		softAssert.assertEquals(productDescription, "Fast iPad");
		
		String productPrice = jp.getString("price");
		System.out.println("Product Price: "+ productPrice);
		//Assert.assertEquals(productPrice, "799");
		softAssert.assertEquals(productPrice, "799");
		//System.out.println("Response Body:"+ response.getBody().prettyPrint());
		
//		softAssert.assertAll(); --(if we are failing the test)
		
	}
	

}
