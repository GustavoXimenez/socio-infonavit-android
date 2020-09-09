package com.grjt.socioinfonavit.ws;

import android.os.AsyncTask;
import android.util.Log;

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

import static com.grjt.socioinfonavit.control.Global.signature;
import static com.grjt.socioinfonavit.control.Global.system_WServices;
import static com.grjt.socioinfonavit.control.Global.typeOfPostRequest;

public class PostRequest extends AsyncTask<Object,Void, JSONObject> {

    private boolean failConnection;

    private PostLogin postLogin = new PostLogin();

    private OnTaskCompleted delegate = null;
    public PostRequest(OnTaskCompleted asyncResponse) {
        delegate = asyncResponse;
    }

    @Override
    protected JSONObject doInBackground(Object... params) {
        JSONObject jsonResponse = null;
        try {
            URL url;
            switch (typeOfPostRequest){
                case "login":
                    url = new URL(system_WServices + "login");
                    break;
                default:
                    url = new URL("");
                    break;
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

            String jsonMessage = null;

            switch (typeOfPostRequest){
                case "login":
                    String email = (String) params[0];
                    String password = (String) params[1];
                    jsonMessage = postLogin.contructMessage(email, password).toString();
                    break;
            }

            writer.write(jsonMessage);
            writer.flush();
            writer.close();
            wr.close();

            // Get response
            InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            //InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
            //BufferedReader responseStreamReader = new BufferedReader(isr);

            int status = httpURLConnection.getResponseCode();
            String header = "";
            if (status == HttpURLConnection.HTTP_OK) {
                signature = httpURLConnection.getHeaderField("authorization");
            }
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            responseStreamReader.close();

            String response = stringBuilder.toString();
            jsonResponse = new JSONObject(response);

            failConnection = false;

        } catch (IOException | JSONException e){
            failConnection = true;
            e.printStackTrace();
        }
        return jsonResponse;
    }

    protected void onPostExecute(JSONObject result) {
        try{
            if(failConnection){
                switch (typeOfPostRequest){
                    case "login":
                        typeOfPostRequest = "fail";
                        delegate.onTaskCompleted(result, "errorLogin");
                        break;
                    default:
                        typeOfPostRequest = "fail";
                        delegate.onTaskCompleted(result, "failConnection");
                        break;
                }
            }

            if(result!=null){
                String strResponse;
                String strResponseMessage;
                switch (typeOfPostRequest){
                    case "login":
                        strResponse = "00";
                        break;
                    default:
                        strResponse = result.getString("ResponseCode");
                }

                switch (typeOfPostRequest){
                    case "login":
                        switch (strResponse){
                            case "00":
                                delegate.onTaskCompleted(result, "OK");
                                break;
                            case "200":
                                //Log.e("Error Message:", result.getString("Message"));
                                delegate.onTaskCompleted(result, "errorLogin");
                                break;
                            default:
                                //Log.e("Error Message:", "Error");
                                delegate.onTaskCompleted(result, "error");
                                break;
                        }
                        break;
                    case "fail":
                        delegate.onTaskCompleted(result, "failConnection");
                        break;

                    default:
                        break;
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
