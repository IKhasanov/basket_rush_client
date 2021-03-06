package ru.twoida.basketrush.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.twoida.basket_rush.models.Task;
import ru.twoida.basket_rush.models.User;
import android.util.Log;

public class BasketRushAPISession {
	
	private static final String serverURI = "http://aistie.cloudapp.net";
	private static final JSONObject jsnAll = null;
	
	public User requestAccountCreation(final String login, final String gender, final String partnerLogin) {
		final String methodURI = "/users/create";
		final String uri = formUriString(serverURI, methodURI);

			JSONObject jsn = null;
			try {
				Log.d("Request", uri);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		        nameValuePairs.add(new BasicNameValuePair("login", login));
		        nameValuePairs.add(new BasicNameValuePair("gender", gender));
		        nameValuePairs.add(new BasicNameValuePair("partner_login",partnerLogin));
				
				jsn = sendPostRequest(uri.toString(), new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		User user = new User();
		try {
			user.mapUser(jsn);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public String requestRegId(final String login, final String secretKey, final String regId)
	{
		final String methodURI = "/users/set_push_id";
		final String uri = formUriString(serverURI,methodURI);
		
		JSONObject jsn = null;
		
		try {
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("login",login));
		    nameValuePairs.add(new BasicNameValuePair("secret",secretKey));
			nameValuePairs.add(new BasicNameValuePair("push_id", regId));
	       
	       Log.d("Password", secretKey);
			Log.d("Request",uri);
			try {
				jsn = sendPostRequest(uri.toString(), new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsn == null ? "" : jsn.toString();
	}
	
	public List<Task> requestList(final String login, final String secretKey)
	{
		final String methodURI = "/users/list";
		final String uri = formUriString(serverURI,methodURI);
		JSONObject jsn = null;
		
		List<Task> result = new ArrayList<Task>();
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("login",login));
			nameValuePairs.add(new BasicNameValuePair("secret",secretKey));
			try {
				jsn = sendPostRequest(uri.toString(),new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				JSONArray arr = jsn.getJSONArray("items");
				for (int i = 0; i < arr.length(); i++) {
					Task task = new Task();
					task.mapTask(arr.getJSONObject(i));
					result.add(task);
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public void requestDeleteListItem(String login, String secretKey, String itemId)
	{
		final String methodURI = "/users/removeitem";
		final String uri = formUriString(serverURI,methodURI);
		
		JSONObject jsn = null;
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("login",login));
			nameValuePairs.add(new BasicNameValuePair("secret",secretKey));
			nameValuePairs.add(new BasicNameValuePair("item_id",itemId));
			try {
				jsn = sendPostRequest(uri.toString(),new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	
	public String requestAddListItem(String login, String secretKey, String title, String count, String photo)
	{
		final String methodURI = "/users/additem";
		final String uri = formUriString(serverURI,methodURI);
		
		JSONObject jsn = null;
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("login",login));
			nameValuePairs.add(new BasicNameValuePair("secret",secretKey));
			nameValuePairs.add(new BasicNameValuePair("data[title]",title));
			if (count != null) {
				nameValuePairs.add(new BasicNameValuePair("data[count]",count));
			}
			if (photo != null) {
				nameValuePairs.add(new BasicNameValuePair("data[photo]",photo));
			}
			try {
				jsn = sendPostRequest(uri.toString(),new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  jsn == null ? "" : jsn.toString();
		
	}
 
	private JSONObject sendPostRequest(String uri, Object entity) throws URISyntaxException, ClientProtocolException, IOException, JSONException {
			final HttpPost request = new HttpPost();
			request.setURI(new URI(uri));
			if (entity != null) {
				request.setEntity((HttpEntity) entity);
			}

			return sendAPIRequest(request);
	}

		private JSONObject sendAPIRequest(final HttpUriRequest request) throws ClientProtocolException, IOException, JSONException {
			BufferedReader in = null;

			try {
				final HttpClient client = new DefaultHttpClient();
				final HttpResponse response = client.execute(request);

				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				final StringBuilder jsnStr = new StringBuilder("");
				String line = "";

				while ((line = in.readLine()) != null) {
					jsnStr.append(line);
				}

				Log.d("Response", jsnStr.toString());

				final JSONObject jsnObj = new JSONObject(jsnStr.toString());

				return jsnObj;
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (final IOException e) {
						final StackTraceElement[] stack = e.getStackTrace();
						final StackTraceElement lastElem = stack[stack.length - 1];
						Log.e(lastElem.getClassName() + '.' + lastElem.getMethodName() + ": Error while closing input stream", e.getMessage());
					}
				}
			}
		}
	private String formUriString(final Object... args) {
		final StringBuilder uri = new StringBuilder("");

		for (final Object arg : args) {
			uri.append(arg);
		}

		return uri.toString();
	}
}
