package com.ustc.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ustc.latte.app.Latte;

/**
 * Created by DELL on 2018/3/3.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
