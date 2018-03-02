package com.ustc.latte.app;

import java.util.HashMap;

/**
 * Created by DELL on 2018/3/2.
 */

public class Configurator {
    //存放配置文件
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    //存放图标并封装
//    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        //开始配置，未完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //静态内部类单例初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
//        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //初始化图标
//    private void  initIcons(){
//        if(ICONS.size()>0){
//            //初始化
//            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
//            for(int i=1;i<ICONS.size();i++){
//                initializer.with(ICONS.get(i));
//            }
//        }
//    }

//    public  final Configurator withIcon(IconFontDescriptor descriptor){
//        ICONS.add(descriptor);
//        return this;
//    }
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguaration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
