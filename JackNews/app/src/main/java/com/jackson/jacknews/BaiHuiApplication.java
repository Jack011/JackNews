package com.jackson.jacknews;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 90720 on 2016/8/26.
 */
public class BaiHuiApplication extends Application {

    public static BaiHuiApplication instance = null;

    public static BaiHuiApplication getInstance() {
        if (instance == null) {
            instance = new BaiHuiApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

}
