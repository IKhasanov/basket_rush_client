package ru.twoida.basketrush.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BasketRushAPISession {
	private static final String serverURI = "http://aistie.cloudapp.net";
	
	public String requestAccountCreation(final String login, final String gender, final String partnerLogin) {
		final String methodURI = "/users/create";
		final String uri = formUriString(serverURI, methodURI);

			JSONObject jsn = null;
			try {
				Log.d("Request", uri);
				jsn = sendPostRequest(uri.toString(), null);
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
			
		return jsn == null ? "" : jsn.toString();
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
