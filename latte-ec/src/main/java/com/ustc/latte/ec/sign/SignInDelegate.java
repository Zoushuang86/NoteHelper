package com.ustc.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.latte.app.ConfigType;
import com.ustc.latte.app.Configurator;
import com.ustc.latte.app.Latte;
import com.ustc.latte.delegates.LatteDelegate;
import com.ustc.latte.ec.R;
import com.ustc.latte.ec.R2;
import com.ustc.latte.net.RestClient;
import com.ustc.latte.net.callback.IError;
import com.ustc.latte.net.callback.IFailure;
import com.ustc.latte.net.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by DELL on 2018/3/4.
 */

public class SignInDelegate extends LatteDelegate{

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){

            Map<String, Object> map = new HashMap<>();
            map.put("email", mEmail.getText().toString());
            map.put("password", mPassword.getText().toString());
            String raw = JSONObject.toJSONString(map);

            RestClient.builder()
//                    .url("http://news.baidu.com/")
                    .url(Latte.getConfiguration(ConfigType.API_HOST)+"/api/auth/")
                    .raw(raw)
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(final String response) {
                            String status = JSON.parseObject(response).getString("status");
                            if (TextUtils.isEmpty(status)) {
                                JSONObject error = JSON.parseObject(response).getJSONObject("error");
                                String message = error.getString("message");
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            } else {
//                                Toast.makeText(getContext(), "登陆成功！", Toast.LENGTH_SHORT).show();
                                SignHandler.onSignIn(response, mISignListener);
//                                final Handler HANDLER = new Handler();
//                                HANDLER.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //跳转主页面
//                                    }
//                                }, 2000);
                            }
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(), code+":"+msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignUpLink(){
        start(new SignUpDelegate());
    }

    private Boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty()) {
            mEmail.setError("邮箱不能为空");
            isPass = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty()) {
            mPassword.setError("密码不能为空");
            isPass = false;
        } else if (password.length() < 6) {
            mPassword.setError("请填写至少6位数");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
