package com.ustc.latte.ec.main;

import android.graphics.Color;

import com.ustc.latte.delegates.bottom.BaseBottomDelegate;
import com.ustc.latte.delegates.bottom.BottomItemDelegate;
import com.ustc.latte.delegates.bottom.BottomTabBean;
import com.ustc.latte.delegates.bottom.ItemBuilder;
import com.ustc.latte.ec.main.index.IndexDelegate;
import com.ustc.latte.ec.main.manager.ManagerDelegate;

import java.util.LinkedHashMap;

/**
 * Created by DELL on 2018/3/10.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-folder-open-o}","管理"),new ManagerDelegate());
        items.put(new BottomTabBean("{fa-cloud-download}","下载"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#303F9F");
    }
}
