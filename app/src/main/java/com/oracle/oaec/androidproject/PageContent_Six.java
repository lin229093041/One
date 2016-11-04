package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PageContent_Six extends Activity {
    private ImageView iv_return;//左上角返回图标

    private TextView activity_six_text1;
    private TextView activity_six_text2;
    private TextView activity_six_text3;
    private TextView activity_six_text4;
    private TextView header_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏
        setContentView(R.layout.activity_page_content__six);

        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        header_text= (TextView) findViewById(R.id.header_text);
        header_text.setText("我与咖啡有个约会");

        activity_six_text1 = (TextView) findViewById(R.id.activity_six_text1);
        activity_six_text1.setText("        咖啡，这个带着小资情怀的代名词，不同的人品出不同的人生滋味。已记不得初次喝咖啡时的心情和味道。只知道，这个来自太平洋彼岸的新鲜饮品与中国几千的茶文化相比，它更能体现此时的心情。开心时喝下的咖啡是甜中带苦，微微苦涩也掩盖不住你心中的喜悦之情；难过之时喝下的咖啡，即使添加再多的糖，你也责怪咖啡太苦。");
        activity_six_text2 = (TextView) findViewById(R.id.activity_six_text2);
        activity_six_text2.setText("        仔细观察坐在咖啡屋享受咖啡的人，每个人的表情之下就像每一杯咖啡都是有故事的人。每一个故事的开始就像一杯充满热气的咖啡，空气中弥漫着甜或苦的味道。你不知道这个故事的结局，但已不重要。也许做在角落的那个安静的美女，纤细的手指紧握咖啡杯，紧皱的眉头透露出她的心情，与咖啡的味道不谋而合，有的人享受过程，有些人更看重结果。");
        activity_six_text3 = (TextView) findViewById(R.id.activity_six_text3);
        activity_six_text3.setText("        依稀记得初次喝漫猫咖啡时的心情，一切都是好奇，一切都是新奇，这种感觉好像是我和咖啡有个约会。一滴馨香入口，已像是走过春夏秋冬，淡淡回味却不及黄连而未加糖的感觉。喜欢漫猫独特设计的咖啡屋，一切都与这个主题文化相结合，咖啡屋内的音乐，灯光，空间布置，随便一个装饰品都能凸显漫猫的悠闲，漫节奏的生活，心境平和的享受一下午的咖啡时光才是一个不错的选择。");
        activity_six_text4 = (TextView) findViewById(R.id.activity_six_text4);
        activity_six_text4.setText("        请不要辜负与我们的每一次约会，请用心对待你手中的每一杯咖啡，请认真倾听他们背后的故事。");
    }
}
