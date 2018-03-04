package com.ustc.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ustc.latte.delegates.LatteDelegate;
import com.ustc.latte.ec.R;
import com.ustc.latte.ec.R2;

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

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){
            Toast.makeText(getContext(), "验证成功", Toast.LENGTH_SHORT).show();
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
        } else if (password.length() < 8) {
            mPassword.setError("请填写至少8位数");
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
