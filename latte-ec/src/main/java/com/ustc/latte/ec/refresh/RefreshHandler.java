package com.ustc.latte.ec.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.ustc.latte.app.ConfigType;
import com.ustc.latte.app.Latte;
import com.ustc.latte.ec.database.DatabaseManager;
import com.ustc.latte.ec.database.UserProfile;
import com.ustc.latte.ec.database.UserProfileDao;
import com.ustc.latte.net.RestClient;
import com.ustc.latte.net.callback.IError;
import com.ustc.latte.net.callback.IFailure;
import com.ustc.latte.net.callback.ISuccess;
import com.ustc.latte.util.storage.LattePreference;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by DELL on 2018/3/17.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        //模拟网络延迟
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    public void firstPage(){
        final JSONObject profileJson = LattePreference.getAppProfileJson();
        final String token = profileJson.getString("token");
        Toast.makeText(Latte.getApplicationContext(), token, Toast.LENGTH_SHORT).show();
//        RestClient.builder()
//                .url(Latte.getConfiguration(ConfigType.API_HOST) + "/api/topTenDoc/")
//                .token(token)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(final String response) {
////                        String status = JSON.parseObject(response).getString("status");
////                        if (TextUtils.isEmpty(status)) {
////                            JSONObject error = JSON.parseObject(response).getJSONObject("error");
////                            String message = error.getString("message");
////                            Toast.makeText(Latte.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
////                        } else {
////
////                        }
//                    }
//                })
//                .error(new IError() {
//                    @Override
//                    public void onError(int code, String msg) {
//                        Toast.makeText(Latte.getApplicationContext(), code + ":" + msg, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .failure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//                        Toast.makeText(Latte.getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build()
//                .post();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
