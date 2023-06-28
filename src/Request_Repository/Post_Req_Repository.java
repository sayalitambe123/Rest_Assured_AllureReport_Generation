package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_API_Methods.Common_Utility_Method;

public class Post_Req_Repository {
	public static String BaseURI() {
		String baseuri = "https://reqres.in/";
		return baseuri;
	}

	public static String Resource() {
		String resource = "api/users";
		return resource;
	}

	public static String RequestBody() throws IOException {
		ArrayList<String> Data = Common_Utility_Method.readDataExcel("PostAPI", "TC1");
		String Req_name = Data.get(1);
		String Req_job = Data.get(2);
		String requestbody = "{\r\n"
				+ "    \"name\": \""+Req_name+"\",\r\n"
				+ "    \"job\": \""+Req_job+"\"\r\n"
				+ "}";
		return requestbody;

	}
}
