package testCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Delete_A_Product {
	SoftAssert softAssert = new SoftAssert();
	
	
//	 "id": "1829",
//     "name": "eat pray love",
//     "description": "one of the best book",
//     "price": "50",
//     "category_id": "6",
//     "category_name": "Books"
	
	@Test
	public void delete_A_Product() {
		
		HashMap payload = new HashMap();
		payload.put("id", "1829");
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json; charset=UTF-8")
					.body(payload).
				when()
					.get("/delete.php").
				then()
					.extract().response();
				
				int statusCode = response.getStatusCode();
				System.out.println("Status Code:" + statusCode);
				softAssert.assertEquals(statusCode, 200, "Status code is not matching!");
				
				String responseBody = response.getBody().asString();
				JsonPath jp = new JsonPath(responseBody);
				String deleteMessage = jp.getString("message");
				softAssert.assertEquals(deleteMessage,"Product was deleted.","Product was not deleted.");
				
				
				
	}

}
