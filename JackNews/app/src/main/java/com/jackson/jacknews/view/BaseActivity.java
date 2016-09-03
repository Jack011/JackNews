package com.jackson.jacknews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 90720 on 2016/8/26.
 */
public abstract class BaseActivity extends AppCompatActivity{



    public abstract int getContentViewId();
    Unbinder binder;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        binder=ButterKnife.bind(this);//一定要放在initview方法前
        initView(savedInstanceState);
        ActivityCollector.addActivity(this);

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
