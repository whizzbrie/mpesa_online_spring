package com.example.demo.service;

import java.text.SimpleDateFormat;

import java.util.Base64;
import java.util.Date;

import org.json.JSONObject;

import com.example.demo.auth.AuthToken;
import com.example.demo.auth.Credentials;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PaymentsService {
	OkHttpClient client = new OkHttpClient();
	
	
	public PaymentsService() {
		
	}
//	public PaymentsService(OkHttpClient client, MediaType mediaType) {
//		this.client = client;
//		this.mediaType = mediaType;
//	}
	
//	public String registerURL() throws Exception {		
//		JSONObject object = new JSONObject();
//		object.put("ShortCode", Credentials.shortCode);
//		object.put("ResponseType", Credentials.content_type);
//		object.put("ConfirmationURL", Credentials.confirmationURL);
//		object.put("ValidationURL", Credentials.validationURL);
//		RequestBody body = RequestBody.create(mediaType, object.toString());
//		Request request = new Request.Builder()
//		  .url(Credentials.register_utl)
//		  .post(body)
//		  .addHeader("authorization", "Bearer ACCESS_TOKEN")
//		  .addHeader("content-type", Credentials.content_type)
//		  .build();
//
//		Response response = client.newCall(request).execute();
//		return response.toString();
//	}

	public String initiateRequest(String amount, String msisdn) throws Exception {
		MediaType mediaType = MediaType.parse(Credentials.content_type);
		JSONObject request = new JSONObject();
		//Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String password = Credentials.shortCode+Credentials.pass_key+sdf.format(new Date());
		byte[] bytes = password.getBytes("ISO-8859-1");
		String auth = Base64.getEncoder().encodeToString(bytes);
		request.put("BusinessShortCode", Credentials.shortCode);
		request.put("Password", auth);
		request.put("Timestamp", sdf.format(new Date()) );
		request.put("TransactionType", Credentials.CustomerPayBillOnline);
		request.put("Amount", amount);
		request.put("PartyA", msisdn);
		request.put("PartyB", Credentials.shortCode);
		request.put("PhoneNumber", msisdn);
		request.put("CallBackURL", "http://62.12.114.150:8089/payment/confirm");
		request.put("AccountReference", "Account Reference");
		request.put("TransactionDesc", "Customer paybill online");
		RequestBody body = RequestBody.create(mediaType, request.toString());
		Request req = new Request.Builder()
		  .url(Credentials.STK)
		  .post(body)
		  .addHeader("Authorization", "Bearer "+getToken())
		  .addHeader("Content-Type", Credentials.content_type)
		  .build();
		Response response = client.newCall(req).execute();
		//System.out.println(Credentials.STK);
		System.out.println(getToken());
		//String resp = response.body().toString();
		//System.out.println();
		return response.body().string();
	}
	
	public String getToken() throws Exception{
		String appKeySecret = Credentials.app_key+":"+Credentials.app_secret;
		byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
		String auth = Base64.getEncoder().encodeToString(bytes);
		//OkHttpClient client = new OkHttpClient();
		

		Request request = new Request.Builder()
		  .url(Credentials.auth_url)
		  .get()
		  .addHeader("authorization", "Basic " + auth)
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = null;
	    response = client.newCall(request).execute();
	    String jsonData = response.body().string();
	    //System.out.println(jsonData);
	    return generateToken(jsonData);
	}
	
	public String generateToken(String jsonData) throws Exception{
		String auth_token = null;
	    JSONObject jobject = new JSONObject(jsonData);
	    if(jobject.has("access_token")) {
	    	 auth_token = jobject.getString("access_token");
	    }else {
	    	//access token has already expired
	    	String data = getToken();
	    	JSONObject resp = new JSONObject(data);
	    	auth_token = resp.getString("access_token");
	    }
	    return auth_token;
	}
}
