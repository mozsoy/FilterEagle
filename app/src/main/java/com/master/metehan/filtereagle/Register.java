package com.master.metehan.filtereagle;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Register Activity Class
 *
 */
public class Register {

    String server_url = null;
    String key = null;
    Context context = null;

    /**
    * @param server_url, key, context
    */
    public Register(String server_url, String key, Context context) {
        this.server_url = server_url;
        this.key = key;
        this.context = context;
    }
    /**
     * Method that registers app
     */
    public void registerApp() {

        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When key is not null
        if (key != null) {
            // Put Http parameters
            params.put("name", "FilterEagle");
            params.put("key", key);
            // Invoke RESTful Web Service with Http parameters
            invokeWS(params);
        }
        // key is null
        else {
            // do nothing
            Log.e("App key error: ", "key is null");
        }
    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params){
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(server_url + "register/doregister"
                ,params ,new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if(!obj.getString("uid").isEmpty()){
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("uid", obj.getString("uid")).commit();
                        // Display successfully registered message using Toast
                        Toast.makeText(context, "You are successfully registered!", Toast.LENGTH_LONG).show();
                    }
                    // Else display error message
                    else{
                        Log.e("Server error: ", obj.getString("error_msg"));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.e("Registration error: ", "Error Occured [Server's JSON " +
                            "response might be invalid]!");
                    e.printStackTrace();
                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // When Http response code is '404'
                if(statusCode == 404){
                    Log.e("Server error: ", "Requested resource not found");
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Log.e("Server error: ", "Something went wrong at server end");
                }
                // When Http response code other than 404, 500
                else{
                    Log.e("Server error: "
                            , "Unexpected Error occcured! [Most common Error: " +
                                    "Device might not be connected to Internet " +
                                    "or remote server is not up and running]");
                }
            }
        });
    }
}
