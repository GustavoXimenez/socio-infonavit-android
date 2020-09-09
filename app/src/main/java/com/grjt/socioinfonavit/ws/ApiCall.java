package com.grjt.socioinfonavit.ws;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.grjt.socioinfonavit.model.Wallet;
import com.grjt.socioinfonavit.model.Wallets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ApiCall extends AsyncTask<URL, Integer, Long> {

    private OnTaskCompletedArray listener;
    private String result;
    private String type;
    private boolean failConnection;
    private String urlError = "";

    public ApiCall(OnTaskCompletedArray listener)
    {
        this.listener = listener;
    }

    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 1; i < count; i++)
        {
            try
            {
                // Read all the text returned by the server
                InputStreamReader reader = new InputStreamReader(urls[i].openStream());
                urlError = urls[i].toString();
                BufferedReader in = new BufferedReader(reader);
                String resultPiece;
                while ((resultPiece = in.readLine()) != null)
                {
                    resultBuilder.append(resultPiece);
                }
                in.close();
                failConnection = false;
            }
            catch (Exception e)
            {
                String error = e.getMessage();
                e.printStackTrace();
                failConnection = true;
                break;
            }
            // if cancel() is called, leave the loop early
            if (isCancelled())
            {
                break;
            }
        }
        // save the result
        this.result = resultBuilder.toString();

        String x = urls[0].toString();
        this.type = x.replace("http://", "");
        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress) {
        // update progress here
    }

    // called after doInBackground finishes
    protected void onPostExecute(Long result) {

        try
        {
            JSONArray jsonObject = null;
            if(!failConnection)
            {
                jsonObject = new JSONArray(this.result);
            }
            else
            {
                this.type = "fail";
            }

            //Validate result
            String strResponse;
            switch(this.type)
            {
                case "wallets":
                case "logout":
                    strResponse = "00";
                    break;

                default:
                    strResponse = "Error";
                    break;
            }

            switch (this.type)
            {
                case "wallets":
                case "logout":
                    switch (strResponse)
                    {
                        case "00":
                            // call callback
                            listener.onTaskCompleted(jsonObject, "OK");
                            break;

                        default:
                            //Log.e("Error Message:", "Error");
                            listener.onTaskCompleted(jsonObject, "error");
                            break;
                    }
                    break;

                default:
                    break;
            }
        }
        catch (JSONException e)
        {
            JSONObject jsonObject = null;
            try {
                if(!failConnection && this.result != null)
                {
                    jsonObject = new JSONObject(this.result);
                }
                else
                {
                    this.type = "fail";
                }
            } catch (Exception ex){

            }
        }
    }

}
