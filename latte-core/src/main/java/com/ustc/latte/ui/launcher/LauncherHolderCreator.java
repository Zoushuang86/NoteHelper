package com.ustc.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by DELL on 2018/3/4.
 */

public class LauncherHolderCreator implements CBViewHolderCreator{
    @Override
    public Object createHolder() {
        return new LauncherHolder();
    }
}
