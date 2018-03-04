package com.ustc.latte.app;

import com.ustc.latte.util.storage.LattePreference;

/**
 * Created by DELL on 2018/3/4.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登陆状态，登陆后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    //判断是否登陆
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSign();
        }
    }
}
