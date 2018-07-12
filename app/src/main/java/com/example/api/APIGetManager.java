package com.example.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Rajat on 6/7/2016.
 */
public class APIGetManager {

    private static final String TAG = "APIManager";
    RequestQueue mQueue;
    private Context context;
    private ResponseInterface responseInterface;
    StringRequest jsonRequest;
    StringRequest jsonRequest1;
    String url;
    String url1;
    private static final String ALLOWED_URI_CHARS = "utf-8";
    Response.Listener<String> defaultResponseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            responseInterface.parseResponse(response);
        }
    };


    public ErrorListener defaultErrorListener = new ErrorListener() {

        @Override
        public void onErrorResponse(com.android.volley.error.VolleyError error) {
            responseInterface.ErrorResponse(error);
        }


    };
        public APIGetManager(Context context, ResponseInterface responseInterface) {
        this.context = context;
        this.responseInterface = responseInterface;
    }

    private void callGetAPI(String params, Response.Listener<String> responseListener, ErrorListener errorListener) {
        mQueue = Volley.newRequestQueue(context);

        url="https://swapi.co/api/"+params;

        Log.d("url is","url is"+url);
        jsonRequest = new StringRequest(Request.Method.GET, url,
                responseListener, errorListener);
       Log.d("json request","json request"+jsonRequest);

        /******************Code to increase the timeout value of volley response**************/
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        /**********************************************************************************************/
        mQueue.add(jsonRequest);
    }





    private void callGetAPI(String params) {
        callGetAPI(params, defaultResponseListener, defaultErrorListener);
    }

    public void callGetAPIExternal() {
        String params = "getAllEmployees";
        callGetAPI(params);
    }




    public void callAllPeople() {
        String params = "people";
        callGetAPI(params);
    }
}
