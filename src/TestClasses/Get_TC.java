package TestClasses;

import java.io.IOException;

import org.testng.annotations.Test;

import Common_API_Methods.Common_Methods_Get;
import Common_API_Methods.Common_Utility_Method;
import Request_Repository.Get_Req_Repository;

public class Get_TC {
	@Test(priority = 1)
	public static void extractor() throws IOException {
		int statusCode = Common_Methods_Get.ResponseStatusCode(Get_Req_Repository.BaseURI(),
				Get_Req_Repository.Resource());
		System.out.println("Status Code : " + statusCode);

		String responseBody = Common_Methods_Get.ResponseBody(Get_Req_Repository.BaseURI(),
				Get_Req_Repository.Resource());
		System.out.println("Response Body is : \n" + responseBody);

		Common_Utility_Method.EvidenceFileCreator("GetTC1", null, responseBody, statusCode);

	}

}
