package com.grjt.socioinfonavit.ws;

import org.json.JSONObject;

public interface OnTaskCompleted {

    void onTaskCompleted(JSONObject result, String type);

}
