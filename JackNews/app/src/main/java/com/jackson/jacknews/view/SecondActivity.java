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
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
    String url;
    @Override
    public int getContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {



        url=getIntent().getStringExtra("chooseUrl");

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
                showShare();
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

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("独乐乐不如众乐乐");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我觉得这条新闻太劲爆了,大家一起来看看吧");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

// 启动分享GUI
        oks.show(this);
    }


}