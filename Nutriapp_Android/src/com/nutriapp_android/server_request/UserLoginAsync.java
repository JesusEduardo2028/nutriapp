package com.nutriapp_android.server_request;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.nutriapp_android.ConfigurationProfileActivity;
import com.nutriapp_android.internal_storage.SharedPreferencesHelper;
import static com.nutriapp_android.config.ConstantsClientServer.USER_LOGIN_URL;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class UserLoginAsync extends AsyncTask<String, Void, Boolean> {
	SharedPreferencesHelper newUserSession;
	Activity loginActivity;
	
	public UserLoginAsync(Activity activity) {
		this.loginActivity = activity;
		newUserSession = new SharedPreferencesHelper(loginActivity);
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		boolean result = false;
		
		HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(USER_LOGIN_URL);
		
        JSONObject jsLogin = new JSONObject();
        JSONObject jsUser = new JSONObject();
        
        try {
        	jsLogin.put("user_name", params[1]);
            jsLogin.put("password", params[2]);
        	jsLogin.put("email", params[3]);
        	
        	jsUser.put("user_attributes",jsLogin);
			Log.i("Login","-> jsonUser: "+jsUser.toString());
        	
			StringEntity stringEntity = new StringEntity(jsUser.toString());
			stringEntity.setContentType("application/json;charset=UTF-8");
			stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
			
			httpPost.setEntity(stringEntity);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("body", jsUser.toString());
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			Log.i("Login","-- status session: "+httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == 200 || httpResponse.getStatusLine().getStatusCode() == 201) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String json = EntityUtils.toString(httpEntity);
				Log.i("Login","-- response session: "+json);
				
				JSONObject jsResult = new JSONObject(json);
	        	newUserSession.writeStringShared("user_id", jsResult.getString("$oid"));
	        	newUserSession.writeStringShared("user_provider", params[0]);
				
	        	result = true;
			} else {
				result = false;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(JSONException e) {
			e.printStackTrace();
		}
        
        return result;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if(result) {
        	Intent registerIntent = new Intent(loginActivity, ConfigurationProfileActivity.class);
        	loginActivity.startActivity(registerIntent);
        	loginActivity.finish();
        } else {
        	Toast.makeText(loginActivity, "registration without success!... please try again", Toast.LENGTH_SHORT).show();
        }
	}

}
