package com.vms.rest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by midhul on 7/4/15.
 */
public class RestAPIResponse {
    public int statusCode;
    public JSONObject body;

    public RestAPIResponse(int status, String resBody) {
        statusCode = status;
        try {
            body = new JSONObject(resBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
