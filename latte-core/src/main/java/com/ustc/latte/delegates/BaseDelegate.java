package com.ustc.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by DELL on 2018/3/3.
 */

//不希望自己或别人创建一个实例

public abstract class BaseDelegate extends SwipeBackFragment {
    //butterknife定义
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    //传入的布局对象不明确，故使用Object
    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            //绑定根视图
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
