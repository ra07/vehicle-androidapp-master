package com.vms.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by midhul on 7/4/15.
 */
public class RestAPIRequest {
    public static final String login = "rest/api-token-auth/";
    public static final String theftReport = "rest/theft-report/";
    public static final String suspiciousVehicle= "rest/suspicious-vehicle/";


    private URL _url;
    private List<NameValuePair> _data;
    private String _method;
    private String _authToken;
    private Boolean _authorized;
    public RestAPIRequest() {
        //empty constructor
    }
    public void init(String host , String method, List<NameValuePair> data, String action, String authToken) {
        //constructor
        try {
            _url = new URL(new URL(host), action);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
        _method = method;
        _data = data;
        _authToken = authToken;
        if(_authToken == "None") _authorized = Boolean.FALSE;
        else _authorized = Boolean.TRUE;


    }

    public void send() {
        //send the request
        Log.d("REST API", "reached send");
        new SendRequestTask().execute();

    }

    protected void onFinish(RestAPIResponse res) {
        //after response is received

    }

    private class SendRequestTask extends AsyncTask<Void, Void, RestAPIResponse> {
        @Override
        protected RestAPIResponse doInBackground(Void... arg0) {
            Log.d("REST API", "started background work...");
            String response = "None";
            int statusCode = 0;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpEntity httpEntity = null;
                HttpResponse httpResponse = null;
                if(_method == "POST") {
                    HttpPost httpPost = new HttpPost(_url.toString());
                    httpPost.setEntity(new UrlEncodedFormEntity(_data));
                    if(_authorized) httpPost.setHeader("Authorization", "Token " + _authToken);
                    httpResponse = httpClient.execute(httpPost);
                }
                else if(_method == "GET") {
                    HttpGet httpGet = new HttpGet(_url.toString());
                    if(_authorized) httpGet.setHeader("Authorization", "Token " + _authToken);
                    httpResponse = httpClient.execute(httpGet);
                }
                else if(_method == "PUT") {
                    HttpPut httpPut = new HttpPut(_url.toString());
                    httpPut.setEntity(new UrlEncodedFormEntity(_data));
                    if(_authorized) httpPut.setHeader("Authorization", "Token " + _authToken);
                    httpResponse = httpClient.execute(httpPut);
                }
                else if(_method == "DELETE") {
                    HttpDelete httpDelete = new HttpDelete(_url.toString());
                    if(_authorized) httpDelete.setHeader("Authorization", "Token " + _authToken);
                    httpResponse = httpClient.execute(httpDelete);

                }

                //if(httpEntity == null) Log.d("REST API", "fuck yeah httpEntity is null");
                if(httpResponse == null) Log.d("REST API", "fuck yeah httpResponse is null");
                httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
                statusCode = httpResponse.getStatusLine().getStatusCode();

                Log.d("REST API", response);



            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new RestAPIResponse(statusCode, response);


        }

        @Override
        protected void onPostExecute(RestAPIResponse res) {
            onFinish(res);

        }
    }

}
