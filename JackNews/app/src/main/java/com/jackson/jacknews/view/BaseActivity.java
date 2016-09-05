package com.jackson.jacknews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.tauth.Tencent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 90720 on 2016/8/26.
 */
public abstract class BaseActivity extends AppCompatActivity{



    public abstract int getContentViewId();
    Unbinder binder;
    Tencent mTencent;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        binder=ButterKnife.bind(this);//一定要放在initview方法前
        ShareSDK.initSDK(this,"16901c57cdb4c");
        initView(savedInstanceState);
        ActivityCollector.addActivity(this);
        mTencent = Tencent.createInstance("1105650644", this.getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }





    protected abstract void initView(Bundle savedInstanceState);
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.addActivity(this);
        binder.unbind();

    }
}
