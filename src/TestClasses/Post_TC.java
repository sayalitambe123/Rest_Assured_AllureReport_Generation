package TestClasses;
import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Common_Methods_Post;
import Common_API_Methods.Common_Utility_Method;
import Request_Repository.Post_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Post_TC {
	@Test(priority =1)
	public static void extractor() throws IOException {
		int statusCode = Common_Methods_Post.ResponseStatusCode(Post_Req_Repository.BaseURI(), Post_Req_Repository.Resource()
				, Post_Req_Repository.RequestBody() );
		System.out.println("Status Code : "+statusCode);
		
		
		String responseBody = Common_Methods_Post.ResponseBody(Post_Req_Repository.BaseURI(), Post_Req_Repository.Resource()
				, Post_Req_Repository.RequestBody() );
		System.out.println("Response Body is : \n"+responseBody);
		
		String RequestBody = Post_Req_Repository.RequestBody();
		validator(RequestBody,responseBody);
		
		Common_Utility_Method.EvidenceFileCreator("PostTC1", RequestBody, responseBody, statusCode);
		
		
	}
	public static void validator(String RequestBody, String ResponseBody) {
		JsonPath jspRequest = new JsonPath(RequestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");
		LocalDateTime current = LocalDateTime.now();
		String currenttime = current.toString().substring(0,10);
		
		JsonPath jspResponse = new JsonPath(ResponseBody);
		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		String res_id = jspResponse.getString("id");
		String res_createdAt = jspResponse.getString("createdAt").substring(0,10);
		
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_createdAt, currenttime);
		Assert.assertNotNull(res_id);
		Assert.assertNotEquals(res_id, 0);
		
	}

}
