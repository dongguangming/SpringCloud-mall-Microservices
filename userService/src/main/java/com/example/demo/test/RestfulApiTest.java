package com.example.demo.test;

/**
 * 
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.User;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class RestfulApiTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		httpPostWithjson("http://localhost:9999/user/login","");
	}

	 
    public static JSONObject httpPostWithjson(String url, String json) throws IOException {
		JSONObject jobj = new JSONObject();

        final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        User u = new User();
        u.setUsername("dongguangming");
        u.setPassword("ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=");
        String data = "{ 'username' : 'dongguangming', 'password' : 'ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=' }";

    	RequestBody requestBody = RequestBody.create(TYPE_JSON, JSON.toJSONString(u));

        RequestBody body = RequestBody.create( TYPE_JSON, data);
        Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build();
        try{
        	Response response = okHttpClient.newCall(request).execute();
        
        	jobj.put("result", "fail");
			
            //return response.body();
        }
        catch(Exception e){
            System.out.println("section1");
        	jobj.put("result", "fail");
            return null;
        }
        
        return jobj;
    }
}

