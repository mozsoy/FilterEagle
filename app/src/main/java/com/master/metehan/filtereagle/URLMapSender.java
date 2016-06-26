package com.master.metehan.filtereagle;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Metehan on 6/8/2016.
 */
public class URLMapSender {

    String server_url = null;
    String uid = null;
    Context context = null;

    /**
     * @param server_url,context
     */
    public URLMapSender(String server_url, String uid, Context context) {
        this.server_url = server_url;
        this.uid = uid;
        this.context = context;
    }

    /**
     * Method that sends URL map to server database
     */
    public void sendURLMap() {
        RequestParams params = new RequestParams();
        // When uid is not null
        if(uid != null){
            // Put Http parameter uid
            params.put("user_id", uid);
            // Put URL map in
            URLCache urlCache = URLCache.getInstance();
            HashMap<String, Integer> urlMap = urlCache.getMap();
            /* TRY SENDING URL MAP AS A CUSTOM DATA TYPE
            HashMapAcceptModel urlMapAsString = new HashMapAcceptModel();
            urlMapAsString.setCompleteHashMapListList(urlMap);
            */
            System.out.println(urlMap.size());
            Toast.makeText(context,"Url map size is " + urlMap.size(),Toast.LENGTH_LONG).show();
            params.put("urldata", urlMap.toString());
            // Put Http parameter password with value of Password Edit Value control
            invokeWS(params);
        } else{
            Toast.makeText(context, "Wrong user id", Toast.LENGTH_LONG).show();
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
        client.get("http://" + server_url + "/useraccount/urlupdate/doupdate"
                ,params ,new AsyncHttpResponseHandler() {
                    // When the response returned by REST has Http response code '200'
                    @Override
                    public void onSuccess(String response) {
                        // Hide Progress Dialog
                        try {
                            // JSON Object
                            JSONObject obj = new JSONObject(response);
                            // When the JSON response has status boolean value assigned with true
                            if(obj.getBoolean("status")){
                                Toast.makeText(context, "URL map successfully sent to Database Server!", Toast.LENGTH_LONG).show();
                                // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                            }
                            // Else display error message
                            else{
                                Toast.makeText(context, obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(context, "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
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
