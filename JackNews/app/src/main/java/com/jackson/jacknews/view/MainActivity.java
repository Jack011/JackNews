package com.jackson.jacknews.view;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jackson.jacknews.R;

import com.jackson.jacknews.adapter.RecycleHolder;
import com.jackson.jacknews.adapter.RecyclerAdapter;
import com.jackson.jacknews.moudle.HuaBianResponse;
import com.jackson.jacknews.moudle.bean.HuaBainListBean;
import com.jackson.jacknews.util.HttpService;
import com.jackson.jacknews.util.RetrofitUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends BaseActivity{

    @BindView(R.id.main_recycler_view)
    RecyclerView mainRecyclerView;

    @BindView(R.id.bidir_sliding_layout)
    BidirSlidingLayout bidirSldingLayout;

    @BindView(R.id.main_bar1)
    TextView mainBar1;
    @BindView(R.id.main_bar2)
    TextView mainBar2;
    @BindView(R.id.main_bar3)
    TextView mainBar3;
    @BindView(R.id.main_bar4)
    TextView mainBar4;
    @BindView(R.id.main_bar5)
    TextView mainBar5;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView title_right;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.main_bar6)
    ImageButton mainBar6;


    StaggeredGridLayoutManager mLayoutManager;
    List<String> newsUrlList = new ArrayList<>();


    List<HuaBainListBean> huaBainBeanList = new ArrayList<>();

    HttpService httpService;
    boolean isAnimGo = true;
    boolean isNotify = false;

    RecyclerAdapter<HuaBainListBean> adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }



    @Override
    protected void initView(Bundle savedInstanceState) {


        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mainRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mainRecyclerView.setHasFixedSize(true);
        httpService = RetrofitUtil.instanceHttpService();
        Call<HuaBianResponse> startCall = httpService.getKejiNewsData("2a6e5e547f262b90a410b4dc842ee7df", "20", "1");
        getNewsData(startCall);

        bidirSldingLayout.setScrollEvent(mainRecyclerView);

        btnBidirSlding();
    }



    private void btnBidirSlding() {

        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bidirSldingLayout.isLeftLayoutVisible()) {
                    bidirSldingLayout.scrollToContentFromLeftMenu();
                } else {
                    bidirSldingLayout.initShowLeftState();
                    bidirSldingLayout.scrollToLeftMenu();
                }
            }
        });
        title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bidirSldingLayout.isRightLayoutVisible()) {
                    bidirSldingLayout.scrollToContentFromRightMenu();
                } else {
                    bidirSldingLayout.initShowRightState();
                    bidirSldingLayout.scrollToRightMenu();
                }
            }
        });
    }


    private void getNewsData(Call<HuaBianResponse> callWhich) {

        Call<HuaBianResponse> call = callWhich;
        call.enqueue(new Callback<HuaBianResponse>() {

            @Override
            public void onResponse(Call<HuaBianResponse> call, retrofit2.Response<HuaBianResponse> response) {

                if (huaBainBeanList.size() > 0) {
                    huaBainBeanList.clear();
                }

                huaBainBeanList.addAll(response.body().getNewslist());
                if (isNotify) {
                    adapter.notifyDataSetChanged();

                } else {
                    isNotify = true;
                    mainRecyclerView.setAdapter(adapter = new RecyclerAdapter<HuaBainListBean>(MainActivity.this, huaBainBeanList, R.layout.item_main_recycler) {

                        @Override
                        public void convert(RecycleHolder holder, HuaBainListBean huaBainListBean,int position) {
                            holder.setText(R.id.item_main_recycler_title, huaBainListBean.getTitle());
                            holder.setText(R.id.item_main_recycler_time, huaBainListBean.getCtime());
                            final String newsUrl = huaBainListBean.getUrl();
                            newsUrlList.add(newsUrl);
                            String picUrl = huaBainListBean.getPicUrl();
                            holder.setImageNet(R.id.item_main_recycler_image,picUrl);

                        }
                    });
                    adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClickListener(View view, int position) {
                            String url=newsUrlList.get(position);
                            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                            intent.putExtra("chooseUrl",url);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<HuaBianResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "请求数据失败" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.main_bar1, R.id.main_bar2, R.id.main_bar3, R.id.main_bar4, R.id.main_bar5, R.id.main_bar6})
    public void onClick(View view) {

        Call<HuaBianResponse> call = null;
        switch (view.getId()) {
            case R.id.main_bar1:
                titleText.setText("百汇-科技");
                call = httpService.getKejiNewsData("2a6e5e547f262b90a410b4dc842ee7df", "20", "1");
                getNewsData(call);
                break;
            case R.id.main_bar2:
                titleText.setText("百汇-体育");
                call = httpService.getTiYuNewsData("2a6e5e547f262b90a410b4dc842ee7df", "20", "1");
                getNewsData(call);
                break;
            case R.id.main_bar3:
                titleText.setText("百汇-娱乐");
                call = httpService.getHuaBianNewsData("2a6e5e547f262b90a410b4dc842ee7df", "20", "1");
                getNewsData(call);
                break;
            case R.id.main_bar4:
                titleText.setText("百汇-奇闻");
                call = httpService.getQiWenNewsData("2a6e5e547f262b90a410b4dc842ee7df", "20", "1");
                getNewsData(call);
                break;
            case R.id.main_bar5:
                titleText.setText("百汇-美女");
                call = httpService.getMeiNvPicData("2a6e5e547f262b90a410b4dc842ee7df", "20", "1");
                getNewsData(call);
                break;
            case R.id.main_bar6:
                break;


        }
        showMenuBar();
        if (isAnimGo) {
            isAnimGo = false;
        } else {
            isAnimGo = true;
        }
    }

    private void showMenuBar() {
        //设置动画时间
        int duration = 2000;
        //动画距离,屏幕宽度的60%
        float distance = getScreenWidth() * 0.6f;//432

        //相邻ImageView运动角度式22.5度
        float angle1 = (float) (22.5f * Math.PI / 180);
        float angle2 = (float) (45f * Math.PI / 180);
        float angle3 = (float) (67.5f * Math.PI / 180);

        PropertyValuesHolder p1;
        PropertyValuesHolder p21;
        PropertyValuesHolder p22;
        PropertyValuesHolder p31;
        PropertyValuesHolder p32;
        PropertyValuesHolder p41;
        PropertyValuesHolder p42;
        PropertyValuesHolder p5;

        if (isAnimGo) {
            //icon1
            p1 = PropertyValuesHolder.ofFloat("TranslationX", 0f, -distance);
            //icon2
            p21 = PropertyValuesHolder.ofFloat("TranslationX", 0f, -(float) (distance * Math.cos(angle1)));
            p22 = PropertyValuesHolder.ofFloat("TranslationY", 0f, -(float) (distance * Math.sin(angle1)));
            //icon3
            p31 = PropertyValuesHolder.ofFloat("TranslationX", 0f, -(float) (distance * Math.cos(angle2)));
            p32 = PropertyValuesHolder.ofFloat("TranslationY", 0f, -(float) (distance * Math.sin(angle2)));
            //icon4
            p41 = PropertyValuesHolder.ofFloat("TranslationX", 0f, -(float) (distance * Math.cos(angle3)));
            p42 = PropertyValuesHolder.ofFloat("TranslationY", 0f, -(float) (distance * Math.sin(angle3)));
            //icon5
            p5 = PropertyValuesHolder.ofFloat("TranslationY", 0f, -distance);
        } else {
            //icon1
            p1 = PropertyValuesHolder.ofFloat("TranslationX", -distance, 0f);
            //icon2
            p21 = PropertyValuesHolder.ofFloat("TranslationX", -(float) (distance * Math.cos(angle1)), 0f);
            p22 = PropertyValuesHolder.ofFloat("TranslationY", -(float) (distance * Math.sin(angle1)), 0f);
            //icon3
            p31 = PropertyValuesHolder.ofFloat("TranslationX", -(float) (distance * Math.cos(angle2)), 0f);
            p32 = PropertyValuesHolder.ofFloat("TranslationY", -(float) (distance * Math.sin(angle2)), 0f);
            //icon4
            p41 = PropertyValuesHolder.ofFloat("TranslationX", -(float) (distance * Math.cos(angle3)), 0f);
            p42 = PropertyValuesHolder.ofFloat("TranslationY", -(float) (distance * Math.sin(angle3)), 0f);
            //icon5
            p5 = PropertyValuesHolder.ofFloat("TranslationY", -distance, 0f);
        }
        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mainBar1, p1).setDuration(duration);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(mainBar2, p21, p22).setDuration(duration);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(mainBar3, p31, p32).setDuration(duration);
        ObjectAnimator animator4 = ObjectAnimator.ofPropertyValuesHolder(mainBar4, p41, p42).setDuration(duration);
        ObjectAnimator animator5 = ObjectAnimator.ofPropertyValuesHolder(mainBar5, p5).setDuration(duration);

        //添加自由落体效果插值器
        animator1.setInterpolator(new BounceInterpolator());
        animator2.setInterpolator(new BounceInterpolator());
        animator3.setInterpolator(new BounceInterpolator());
        animator4.setInterpolator(new BounceInterpolator());
        animator5.setInterpolator(new BounceInterpolator());

        //启动动画
        animator1.start();
        animator2.start();
        animator3.start();
        animator4.start();
        animator5.start();
    }

    /**
     * 竖屏时获取屏幕宽度，横屏时，获取高度
     *
     * @return
     */
    public int getScreenWidth() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int x = outMetrics.widthPixels;
        int y = outMetrics.heightPixels;
        return x > y ? y : x;
    }


}