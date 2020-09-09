package com.grjt.socioinfonavit.ws;

import com.grjt.socioinfonavit.model.Member;
import com.grjt.socioinfonavit.model.User;
import com.grjt.socioinfonavit.model.Wallet;
import com.grjt.socioinfonavit.model.Wallets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.grjt.socioinfonavit.control.Global.userLogin;
import static com.grjt.socioinfonavit.control.Global.wallets;

public class Callback {

    public void processingResult(JSONObject result, String type) {
        switch (type){
            case "loginUser":
                try {
                    parseJSONLoginUser(result);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

    public void processingResult(JSONArray result, String type) {
        switch (type){
            case "wallet":
                try {
                    parseJSONWallets(result);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

    private void parseJSONLoginUser(JSONObject jsonObject) {
        try {
            int id = jsonObject.getInt("id");
            String email = jsonObject.getString("email");
            String role = jsonObject.getString("role");

            //Member
            JSONObject jsonMember = jsonObject.getJSONObject("member");
            int idMember = jsonMember.getInt("id");
            int userId = jsonMember.getInt("user_id");
            String id_socio_infonavit = jsonMember.getString("id_socio_infonavit");
            String name = jsonMember.getString("name");
            String lastname = jsonMember.getString("lastname");
            String mobile = jsonMember.getString("mobile");
            int zipcode = 0;
            String avatar = jsonMember.getString("avatar");
            Member member = new Member(idMember, userId, id_socio_infonavit,
                    name, lastname, mobile, zipcode, avatar);

            int sign_in_count = jsonObject.getInt("sign_in_count");

            userLogin = new User(id, email, role, member, sign_in_count);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseJSONWallets(JSONArray jsonArray){
        wallets.clear();

        for(int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject iObject = jsonArray.getJSONObject(i);
                int id = iObject.getInt("id");
                int displayIndex = iObject.getInt("display_index");
                String displayText = iObject.getString("display_text");
                String icon = iObject.getString("icon");
                String path = iObject.getString("path");
                int maxLevel = iObject.getInt("max_level");
                String avatar = iObject.getString("avatar");
                String name = iObject.getString("name");
                int benevitCount = iObject.getInt("benevit_count");
                String mobileCoverUrl = null;
                String desktopCoverUrl = null;
                int memberLevel = iObject.getInt("member_level");
                String primaryColor = iObject.getString("primary_color");
                wallets.add(new Wallet(id, displayIndex, displayText, icon, path,
                        maxLevel, avatar, name, benevitCount, mobileCoverUrl,
                        desktopCoverUrl, memberLevel, primaryColor));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
