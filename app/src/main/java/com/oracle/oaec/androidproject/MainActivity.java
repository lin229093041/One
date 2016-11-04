package com.oracle.oaec.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    // 底部菜单4个Linearlayout
    public LinearLayout ll_home;
    public LinearLayout ll_address;
    public LinearLayout ll_friend;
    public LinearLayout ll_setting;

    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_address;
    private ImageView iv_friend;
    private ImageView iv_setting;

    // 底部菜单4个菜单标题
    private TextView tv_home;
    private TextView tv_address;
    private TextView tv_friend;
    private TextView tv_setting;

    // 4个Fragment
    private Fragment homeFragment;
    private Fragment addressFragment;
    private Fragment friendFragment;
    private Fragment settingFragment;


//    private ImageView letemenuview;

//    private SlidingMenu sl;//初始化侧边栏

    private TextView top_title;//标题

    private View toppp;//头部菜单
    private View bottom_menu;//底部菜单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 判断是否第一次打开
         */
        SharedPreferences share = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = share.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = share.edit();
        if (isFirstRun) {
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            Intent intent = new Intent(MainActivity.this, StartViewPagerActivity.class);
            startActivity(intent);
        }

        toppp = findViewById(R.id.toppppp);//关联头部标题的id
        toppp.setVisibility(View.GONE);//初始化设置标题隐藏

        bottom_menu = findViewById(R.id.bottom_menu);
        bottom_menu.setVisibility(View.GONE);//初始化底部隐藏

        //初始化控件
        initView();

        //初始化底部按钮事件
        intiEvent();
        //初始化并设置当前fragment
        initFragment(0);


//        //设置侧边栏的属性
//        sl = new SlidingMenu(this);//初始化
//        sl.setMode(SlidingMenu.LEFT);
//        sl.setBehindOffset(100);
//        sl.setMenu(R.layout.fragment_four);
//        sl.setFadeDegree(0.5f);
//        sl.attachToActivity(this, SlidingMenu.TOUCHMODE_FULLSCREEN);

    }

//    @Override
//    protected void onResume() {
//        String username = getIntent().getStringExtra("Username");
//        if(username!=null){
//            initFragment(3);
//        }
//        super.onResume();
//    }

    private void initFragment(int index) {
        //引用v4包的fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (addressFragment == null) {
                    addressFragment = new TwoFragment();
                    transaction.add(R.id.fl_content, addressFragment);
                } else {
                    transaction.show(addressFragment);
                }
                break;
            case 2:
                if (friendFragment == null) {
                    friendFragment = new ShoppingCartFragment();
                    transaction.add(R.id.fl_content, friendFragment);
                } else {
                    transaction.show(friendFragment);
                }
                break;
            case 3:
                if (settingFragment == null) {
                    settingFragment = new FourFragment();
                    transaction.add(R.id.fl_content, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }
                break;
            default:
                break;
        }
        //提交事务
        transaction.commit();

    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (addressFragment != null) {
            transaction.hide(addressFragment);
        }
        if (friendFragment != null) {
            transaction.hide(friendFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }

    }


    //设置按钮监听
    private void intiEvent() {
        ll_home.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_setting.setOnClickListener(this);

        //标题左边的imageview点击监听
//        letemenuview.setOnClickListener(this);

    }

    //初始化控件
    private void initView() {
        //底部菜单4个Linearlayout
        ll_home = (LinearLayout) findViewById(R.id.ll_home);
        ll_address = (LinearLayout) findViewById(R.id.ll_two);
        ll_friend = (LinearLayout) findViewById(R.id.ll_friend);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

        //底部菜单的4个ImageView
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_address = (ImageView) findViewById(R.id.iv_address);
        iv_friend = (ImageView) findViewById(R.id.iv_friend);
        iv_setting = (ImageView) findViewById(R.id.iv_setting);

        //底部菜单4个菜单标题
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_friend = (TextView) findViewById(R.id.tv_friend);
        tv_setting = (TextView) findViewById(R.id.tv_setting);

        //标题左边的imageview，用于打开侧边栏
//        letemenuview = (ImageView) findViewById(R.id.top_IV_left_menu);

        //标题
        top_title = (TextView) findViewById(R.id.top_title);

    }

    //点击事件
    @Override
    public void onClick(View v) {

        //设置点击变色
        restartBotton();

        switch (v.getId()) {
            case R.id.ll_home:
                iv_home.setImageResource(R.drawable.bottom_home1);
                tv_home.setTextColor(0xffea8010);
                toppp.setVisibility(View.GONE);//设置头部标题隐藏
                bottom_menu.setVisibility(View.GONE);//设置底部菜单隐藏
                initFragment(0);
                break;
            case R.id.ll_two:
                iv_address.setImageResource(R.drawable.bottom_activity1);
                tv_address.setTextColor(0xffea8010);
                top_title.setText("活动");
                toppp.setVisibility(View.VISIBLE);//设置标题可见
                bottom_menu.setVisibility(View.VISIBLE);//设置底部菜单可见
                initFragment(1);
                break;
            case R.id.ll_friend:
                iv_friend.setImageResource(R.drawable.bottom_goods1);
                tv_friend.setTextColor(0xffea8010);
                top_title.setText("商品");
                toppp.setVisibility(View.VISIBLE);//设置标题可见
                bottom_menu.setVisibility(View.VISIBLE);//设置底部菜单可见
                initFragment(2);
//                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
//                startActivity(intent);

                break;
            case R.id.ll_setting:
                iv_setting.setImageResource(R.drawable.bottom_my1);
                tv_setting.setTextColor(0xffea8010);
                toppp.setVisibility(View.VISIBLE);//设置标题可见
                bottom_menu.setVisibility(View.VISIBLE);//设置底部菜单可见
                initFragment(3);
                top_title.setText("我的");
                break;

//            case R.id.top_IV_left_menu://标题左边的imageview点击事件
//                sl.toggle();
//                break;

            default:
                break;
        }


    }

    private void restartBotton() {
        //ImageView设置变换颜色
        iv_home.setImageResource(R.drawable.bottom_home);
        iv_address.setImageResource(R.drawable.bottom_activity);
        iv_friend.setImageResource(R.drawable.bottom_goods);
        iv_setting.setImageResource(R.drawable.bottom_my);

        //TextView设置颜色
        tv_home.setTextColor(Color.parseColor("#000000"));
        tv_address.setTextColor(Color.parseColor("#000000"));
        tv_friend.setTextColor(Color.parseColor("#000000"));
        tv_setting.setTextColor(Color.parseColor("#000000"));
    }


}
