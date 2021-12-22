package api;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CurrentOpenWeather {
	
	@DataProvider
	public Object[][] testData() {
		
		Object[][] data = new Object[6][3];
		data[0][0] = "2643743";
		data[0][1] = "9d50450a48809637b4862bdcb125927d";
		data[0][2]= "London";
		
		data[1][0] = "2988507";
		data[1][1] = "9d50450a48809637b4862bdcb125927d";
		data[1][2] = "Paris";
		
		data[2][0] = "5128581";
		data[2][1] = "9d50450a48809637b4862bdcb125927d";
		data[2][2] = "New York";
		
		data[3][0] = "2650225";
		data[3][1] = "9d50450a48809637b4862bdcb125927d";
		data[3][2] = "Delhi";

		data[4][0] = "1850147";
		data[4][1] = "9d50450a48809637b4862bdcb125927d";
		data[4][2] = "Tokyo";
		
		data[5][0] = "xxxxx";
		data[5][1] = "xxxxx";
		data[5][2] = "Toronto";
		
		return data;
	}
	
	@Test(dataProvider="testData")
	public void main(String id, String appid, String city) {
		
		RestAssured.baseURI="http://api.openweathermap.org";
		
		Response response = given().queryParam("id", id).queryParam("appid", appid).queryParam("units", "metric")
		.when().get("data/2.5/weather");
		
		
		int statusCode = response.getStatusCode();

		String responseString = response.asString();
		
		JsonPath jp = new JsonPath(responseString);
		String responseCity = jp.getString("name");
		
		
		if(id=="2643743"&&appid=="9d50450a48809637b4862bdcb125927d")
		{
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(responseCity, city);
			Assert.assertTrue(responseString.contains("temp"));
			Assert.assertTrue(responseString.contains("humidity"));
			Assert.assertTrue(responseString.contains("temp_min"));
			Assert.assertTrue(responseString.contains("temp_max"));
			Assert.assertTrue(responseString.contains("description"));
		}
		if(id=="2988507"&&appid=="9d50450a48809637b4862bdcb125927d")
		{
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(responseCity, city);
			Assert.assertTrue(responseString.contains("temp"));
			Assert.assertTrue(responseString.contains("humidity"));
			Assert.assertTrue(responseString.contains("temp_min"));
			Assert.assertTrue(responseString.contains("temp_max"));
			Assert.assertTrue(responseString.contains("description"));
		}
		if(id=="5128581"&&appid=="9d50450a48809637b4862bdcb125927d")
		{
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(responseCity, city);
			Assert.assertTrue(responseString.contains("temp"));
			Assert.assertTrue(responseString.contains("humidity"));
			Assert.assertTrue(responseString.contains("temp_min"));
			Assert.assertTrue(responseString.contains("temp_max"));
			Assert.assertTrue(responseString.contains("description"));
		}
		if(id=="2650225"&&appid=="9d50450a48809637b4862bdcb125927d")
		{
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(responseCity, city);
			Assert.assertTrue(responseString.contains("temp"));
			Assert.assertTrue(responseString.contains("humidity"));
			Assert.assertTrue(responseString.contains("temp_min"));
			Assert.assertTrue(responseString.contains("temp_max"));
			Assert.assertTrue(responseString.contains("description"));
		}	
		if(id=="1850147"&&appid=="9d50450a48809637b4862bdcb125927d")
		{
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(responseCity, city);
			Assert.assertTrue(responseString.contains("temp"));
			Assert.assertTrue(responseString.contains("humidity"));
			Assert.assertTrue(responseString.contains("temp_min"));
			Assert.assertTrue(responseString.contains("temp_max"));
			Assert.assertTrue(responseString.contains("description"));
		}	
		if(appid!="9d50450a48809637b4862bdcb125927d")
		{
			Assert.assertEquals(statusCode, 401);
			String responseMessage = jp.getString("message");
			Assert.assertEquals(responseMessage, "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.");
		}
		
	}

}
   