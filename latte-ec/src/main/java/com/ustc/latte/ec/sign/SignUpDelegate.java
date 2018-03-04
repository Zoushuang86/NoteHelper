package com.ustc.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.ustc.latte.app.ConfigType;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by DELL on 2018/3/4.
 */

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_repeat_password)
    TextInputEditText mRePassword = null;
    @BindView(R2.id.edit_sign_up_identity)
    TextInputEditText mIdentity = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_address)
    TextInputEditText mAddress = null;
    @BindView(R2.id.edit_sign_up_education)
    TextInputEditText mEducation = null;
    @BindView(R2.id.edit_sign_up_brithday)
    TextInputEditText mBrithday = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {

            Map<String, Object> map = new HashMap<>();
            map.put("name", mName.getText().toString());
            map.put("email", mEmail.getText().toString());
            map.put("password", mPassword.getText().toString());
            map.put("userIdentity", mIdentity.getText().toString());
            map.put("userPhone", mPhone.getText().toString());
            map.put("userAddress", mAddress.getText().toString());
            map.put("userEducation", mEducation.getText().toString());
            map.put("userBrithday", mBrithday.getText().toString());
            String raw = JSONObject.toJSONString(map);


            RestClient.builder()
                    .url(Latte.getConfiguration(ConfigType.API_HOST)+"/api/users/")
                    .raw(raw)
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
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
//            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickSignInLink(){
        start(new SignInDelegate());
    }

    private Boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();
        final String identity = mIdentity.getText().toString();
        final String phone = mPhone.getText().toString();
        final String address = mAddress.getText().toString();
        final String education = mEducation.getText().toString();
        final String brithday = mBrithday.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("姓名不能为空");
            isPass = false;
        } else {
            mName.setError(null);
        }

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

        if (identity.isEmpty()) {
            mIdentity.setError("身份证不能为空");
            isPass = false;
        } else if (identity.length() != 18) {
            mIdentity.setError("身份证位数为18");
            isPass = false;
        } else {
            mIdentity.setError(null);
        }

        if (rePassword.isEmpty()) {
            mRePassword.setError("不能为空");
            isPass = false;
        } else if (rePassword.length() < 6) {
            mRePassword.setError("请填写至少6位数");
            isPass = false;
        } else if (!rePassword.equals(password)) {
            mRePassword.setError("密码不一致");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        if (phone.isEmpty()) {
            mPhone.setError("手机号码不能为空");
            isPass = false;
        } else if (phone.length()<11) {
            mPhone.setError("手机号码至少11位数");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (address.isEmpty()) {
            mAddress.setError("地址不能为空");
            isPass = false;
        } else {
            mAddress.setError(null);
        }

        if (education.isEmpty()) {
            mEducation.setError("教育程度不能为空");
            isPass = false;
        } else {
            mEducation.setError(null);
        }

        if (brithday.isEmpty()) {
            mBrithday.setError("出生日期不能为空");
            isPass = false;
        } else {

            final String matchStr = "^(\\d{4})(-)(\\d{2})(-)(\\d{2})$";
            Pattern pattern = Pattern.compile(matchStr);
            Matcher matcher = pattern.matcher(brithday);

            if (!matcher.matches()) {
                mBrithday.setError("出生日期格式错误");
                isPass = false;
            } else {
                mBrithday.setError(null);
            }
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
