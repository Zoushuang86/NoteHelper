package com.ustc.notehelper.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ustc.latte.activities.ProxyActivity;
import com.ustc.latte.app.Latte;
import com.ustc.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
