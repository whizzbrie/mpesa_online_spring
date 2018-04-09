package com.example.demo.auth;
import java.util.Base64;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AuthToken {
	
//	public String getToken() throws Exception{
//		String appKeySecret = Credentials.app_key + ":" + Credentials.app_secret;
//		byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
//		String auth = Base64.getEncoder().encodeToString(bytes);
//		OkHttpClient client = new OkHttpClient();
//		
//
//		Request request = new Request.Builder()
//		  .url(Credentials.auth_url)
//		  .get()
//		  .addHeader("authorization", "Basic " + auth)
//		  .addHeader("cache-control", "no-cache")
//		  .build();
//
//		Response response = null;
//	    response = client.newCall(request).execute();
//	    String jsonData = response.body().string();
//	    //System.out.println(jsonData);
//	    return generateToken(jsonData);
//	}
//	
//	public String generateToken(String jsonData) throws Exception{
//		String auth_token = null;
//	    JSONObject jobject = new JSONObject(jsonData);
//	    if(jobject.has("access_token")) {
//	    	 auth_token = jobject.getString("access_token");
//	    }else {
//	    	//access token has already expired
//	    	String data = getToken();
//	    	JSONObject resp = new JSONObject(data);
//	    	auth_token = resp.getString("access_token");
//	    }
//	    return auth_token;
//	}
}
