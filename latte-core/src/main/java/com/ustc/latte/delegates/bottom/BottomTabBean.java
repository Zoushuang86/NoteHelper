package com.ustc.latte.delegates.bottom;

/**
 * Created by DELL on 2018/3/10.
 */

public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence  TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
