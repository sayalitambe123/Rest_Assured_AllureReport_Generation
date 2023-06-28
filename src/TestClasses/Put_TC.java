package TestClasses;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Common_Methods_Put;
import Common_API_Methods.Common_Utility_Method;
import Request_Repository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_TC {
	@Test(priority = 2)
	public static void extractor() throws IOException {
		System.out.println("Extractor mathod called");
		int statusCode = Common_Methods_Put.ResponseStatusCode(Put_Req_Repository.BaseURI(), Put_Req_Repository.Resource()
				, Put_Req_Repository.RequestBody() );
		
		System.out.println("Status Code is : "+statusCode);
		String responseBody = Common_Methods_Put.ResponseBody(Put_Req_Repository.BaseURI(), Put_Req_Repository.Resource()
				, Put_Req_Repository.RequestBody() );
		System.out.println("Response Body is : \n"+responseBody);
		
		String RequestBody = Put_Req_Repository.RequestBody();
		validator(RequestBody,responseBody);
		
		Common_Utility_Method.EvidenceFileCreator("PutTC1", RequestBody, responseBody, statusCode);
		
		
	}
	
	public static void validator(String RequestBody, String ResponseBody) {
		System.out.println("Validator mathod called");
		JsonPath jspRequest = new JsonPath(RequestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");
		LocalDateTime current = LocalDateTime.now();
		String currenttime = current.toString().substring(0,10);
		
		JsonPath jspResponse = new JsonPath(ResponseBody);
		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		String res_updated = jspResponse.getString("updatedAt");
		String res_updatedAt = res_updated.substring(0,10);
		
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, currenttime);
		
		
	}

}
