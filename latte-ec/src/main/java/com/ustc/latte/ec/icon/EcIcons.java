package com.ustc.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by DELL on 2018/3/3.
 */

public enum EcIcons implements Icon{
    icon_scan('\ue606'),
    icon_ali_pay('\ue602');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
