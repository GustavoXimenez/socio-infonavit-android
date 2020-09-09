package com.grjt.socioinfonavit.ws;

import org.json.JSONObject;

public class PostLogin {

    JSONObject contructMessage(String email, String password){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("email", email);
            jsonUser.put("password", password);
            jsonObject.put("user", jsonUser);
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

}
