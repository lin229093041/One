package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PageContent_Five extends Activity {
    private ImageView iv_return;//左上角返回图标

    private TextView activity_five_text1;
    private TextView activity_five_text2;
    private TextView activity_five_text3;
    private TextView activity_five_text4;
    private TextView header_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏
        setContentView(R.layout.activity_page_content__five);

        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        header_text= (TextView) findViewById(R.id.header_text);
        header_text.setText("伴在当下，许在未来——巡店感悟");

        activity_five_text1 = (TextView) findViewById(R.id.activity_five_text1);
        activity_five_text1.setText("       你熟悉的街角，你爱走进的那一两家咖啡店，你钟爱的咖啡口味以及你每次必点的甜点，给你带来了无数次的感动和惊喜。你的快乐，是对我们最大的支持。这段时间，我们例行巡店，走进每一家代理的咖啡店，不为其他，只为让大家度过充满希望的每一天。");
        activity_five_text2 = (TextView) findViewById(R.id.activity_five_text2);
        activity_five_text2.setText("       没有抱怨天气的好坏，没有埋怨路程的遥远，没有嫌弃公交的不便，市场督导依旧风雨无阻的前去每家代理咖啡店进行观察，指导，发现问题，并及时的解决每家店所存在的大大小小的问题。每走进一家店，市场督导都会认真的观察店里物品的摆设，员工的着装以及他们对待顾客的态度。每走进一家店就意味着责任，走进吧台市场督导要观察他们所做产品的方法是否正确，并及时纠正指导，品尝产品的口感是否纯正，我们要做就要做到最好，这才是我们服务的宗旨。");
        activity_five_text3 = (TextView) findViewById(R.id.activity_five_text3);
        activity_five_text3.setText("       店里每一台做产品的机器的好坏都是保证出品口感的源泉，所以，市场督导一定会仔细的检查咖啡机，华芙炉，扒炉等等一切吧台的机器，认真倾听咖啡师和西点师所反应的问题。询问店长本店客流量和运营管理，及时深刻的去解决一切存在的问题。据一位巡店人员回来说，为了把咖啡机调试好，一共喝了十几杯咖啡。");
        activity_five_text4 = (TextView) findViewById(R.id.activity_five_text4);
        activity_five_text4.setText("       每一杯咖啡都是用心在做，每一句话都是用心在听，每一个感动都是在瞬间生成，用心做好每一天。");
    }
}
