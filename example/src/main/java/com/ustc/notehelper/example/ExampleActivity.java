package com.ustc.notehelper.example;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ustc.latte.activities.ProxyActivity;
import com.ustc.latte.app.Latte;
import com.ustc.latte.delegates.LatteDelegate;
import com.ustc.latte.ec.launcher.LauncherDelegate;
import com.ustc.latte.ec.launcher.LauncherScrollDelegate;
import com.ustc.latte.ec.sign.ISignListener;
import com.ustc.latte.ec.sign.SignInDelegate;
import com.ustc.latte.ec.sign.SignUpDelegate;
import com.ustc.latte.ui.launcher.ILauncherListener;
import com.ustc.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已经登陆！", Toast.LENGTH_SHORT).show();
                start(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没有登陆！", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
