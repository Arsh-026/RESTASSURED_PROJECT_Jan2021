package testCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PUT_or_Update_A_Product {

	public class POST_or_Create_A_Product {
		SoftAssert softAssert = new SoftAssert();
			
			@Test
			public void update_A_Product() {
				
				HashMap<String, String> payload = new HashMap<String, String>();
				payload.put("id", "1809");
				payload.put("name", "Automation Basic 1.0");
				payload.put("price", "100");
				payload.put("description", "Best book you can buy!");
				payload.put("category_id", "6");
//				
				Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json")
					.body(payload).
				when()
					.put("/update.php").
				then()
					.extract().response();
				
				int statusCode = response.getStatusCode();
				System.out.println("Status Code:" + statusCode);
				//Assert.assertEquals(statusCode, 200);
				softAssert.assertEquals(statusCode, 200);
				
				long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
				System.out.println("Response Time: " + responseTime);
				
				if(responseTime <= 2000) {
					System.out.println("Response time is within range!");
				}else {
					System.out.println("Response time is out of range!");
				}
				
				String responseBody = response.getBody().asString();
				System.out.println("Response Body: "+ responseBody);
				
				JsonPath jp = new JsonPath(responseBody);
				
				String successMessage = jp.getString("message");
				System.out.println("Success Message: "+ successMessage);       
				softAssert.assertEquals(successMessage, "Product was updated.");
				
				
			}
			
			@Test
			public void read_A_Product() {
				
				Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json")
					.queryParams("id","1809").
				when()
					.post("/read_one.php").
				then()
					.extract().response();
				
				int statusCode = response.getStatusCode();
				System.out.println("Status Code:" + statusCode);
				//Assert.assertEquals(statusCode, 200);
				softAssert.assertEquals(statusCode, 200);
				
				long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
				System.out.println("Response Time: " + responseTime);
				if(responseTime <= 2000) {
					System.out.println("Response time is within range!");
				}else {
					System.out.println("Response time is out of range!");
				}
				
				String responseBody = response.getBody().asString();
				System.out.println("Response Body: "+ responseBody);
				
				JsonPath jp = new JsonPath(responseBody);
				String productName = jp.getString("name");
				System.out.println("Product Name: "+ productName);
				//Assert.assertEquals(productName, "iPad");
				softAssert.assertEquals(productName, "Automation Basic 1.0", "Product name is not matching!");
				
				String productId = jp.getString("id");
				System.out.println("Product Id: "+ productId);
				//Assert.assertEquals(productName, "iPad");
				softAssert.assertEquals(productId, "1809", "Product id is not matching!");
				
				String productPrice = jp.getString("price");
				System.out.println("Product Price: "+ productPrice);
				//Assert.assertEquals(productPrice, "799");
				softAssert.assertEquals(productPrice, "100", "Product name is not matching!");
				//System.out.println("Response Body:"+ response.getBody().prettyPrint());
				
				softAssert.assertAll();
	
		}
			
	}

}
