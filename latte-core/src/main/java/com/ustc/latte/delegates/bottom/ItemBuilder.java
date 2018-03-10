package com.ustc.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by DELL on 2018/3/10.
 */

public final class ItemBuilder {
    //LinkedHashMap有序序列
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    //简单工厂模式创建
    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItems(BottomTabBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
