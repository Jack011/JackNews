package com.jackson.jacknews.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;


import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jackson.jacknews.R;


import butterknife.BindView;

/**
 * Created by 90720 on 2016/8/28.
 */
public class SecondActivity extends BaseActivity {


    @BindView(R.id.second_web)
    WebView webView;
    @BindView(R.id.second_title_left)
    TextView secondTitleLeft;
    @BindView(R.id.second_title_right)
    TextView secondTitleRight;

    @Override
    public int getContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {



        String url=getIntent().getStringExtra("chooseUrl");

        WebSettings webSettings = webView.getSettings();
              //设置WebView属性，能够执行Javascript脚本
               webSettings.setJavaScriptEnabled(true);
             //设置可以访问文件
              webSettings.setAllowFileAccess(true);
           //设置支持缩放
              webSettings.setBuiltInZoomControls(true);
              //加载需要显示的网页
        webView.loadUrl(url);
               //设置Web视图
        webView.setWebViewClient(new webViewClient ());

        initTitleBar();

    }

    private void initTitleBar() {
        secondTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        secondTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    //Web视图
       private class webViewClient extends WebViewClient {
              public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                       return true;
                   }
          }




}