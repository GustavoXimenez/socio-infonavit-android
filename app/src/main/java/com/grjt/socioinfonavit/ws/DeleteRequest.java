package com.grjt.socioinfonavit.ws;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.grjt.socioinfonavit.control.Global.system_WServices;
import static com.grjt.socioinfonavit.control.Global.typeOfPostRequest;

public class DeleteRequest extends AsyncTask<Object,Void, JSONObject> {

    private boolean failConnection;

    private OnTaskCompleted delegate = null;
    public DeleteRequest(OnTaskCompleted asyncResponse) {
        delegate = asyncResponse;
    }

    @Override
    protected JSONObject doInBackground(Object... params) {
        JSONObject jsonResponse = null;
        try {
            URL url;
            switch (typeOfPostRequest){
                case "logout":
                    url = new URL(system_WServices + "logout");
                    break;
                default:
                    url = new URL("");
                    break;
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("DELETE"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

            writer.flush();
            writer.close();
            wr.close();

            // Get response
            InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            //InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
            //BufferedReader responseStreamReader = new BufferedReader(isr);
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            responseStreamReader.close();

            failConnection = false;

        } catch (IOException e){
            failConnection = true;
            e.printStackTrace();
        }
        return jsonResponse;
    }

    protected void onPostExecute(JSONObject result) {
        try{
            if(failConnection){
                switch (typeOfPostRequest){
                    case "logout":
                        delegate.onTaskCompleted(result, "errorLogout");
                        break;
                    default:
                        delegate.onTaskCompleted(result, "failConnection");
                        break;
                }
            } else {
                delegate.onTaskCompleted(result, "OK");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
