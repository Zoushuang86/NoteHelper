package com.ustc.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.latte.app.AccountManager;
import com.ustc.latte.ec.database.DatabaseManager;
import com.ustc.latte.ec.database.UserProfile;

/**
 * Created by DELL on 2018/3/4.
 */

public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {
//    public static void onSignIn(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("result");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String email = profileJson.getString("email");
        final String token = profileJson.getString("token");

        final UserProfile profile = new UserProfile(userId, name, email, token);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("result");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String email = profileJson.getString("email");
        final String token = profileJson.getString("token");

        final UserProfile profile = new UserProfile(userId, name, email, token);
        DatabaseManager.getInstance().getDao().insert(profile);


        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
