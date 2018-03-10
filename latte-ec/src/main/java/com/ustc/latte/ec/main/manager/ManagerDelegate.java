package com.ustc.latte.ec.main.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ustc.latte.delegates.bottom.BottomItemDelegate;
import com.ustc.latte.ec.R;

/**
 * Created by DELL on 2018/3/10.
 */

public class ManagerDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_manager;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
