package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PageContent_Seven extends Activity {
    private ImageView iv_return;//左上角返回图标

    private TextView activity_seven_text1;
    private TextView activity_seven_text2;
    private TextView activity_seven_text3;

    private TextView header_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏
        setContentView(R.layout.activity_page_content__seven);

        header_text= (TextView) findViewById(R.id.header_text);
        header_text.setText("感受咖啡，感受典雅生活");


        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activity_seven_text1 = (TextView) findViewById(R.id.activity_seven_text1);
        activity_seven_text1.setText("      咖啡是典雅、悠闲的。\n        一个独自走在街上，孤独的感受这份在日新月异加快的时代节奏下难以享受到的幽静、淡然。纵然时光也可被消费、生活也将被顽固的事实所固化，但唯一不变的就是那一份向往，向往享受、向往悠闲、向往那一份不一样的时光。\n     直到有一天，一抹浓浓的咖啡香吸引了我，使我走进这一间咖啡馆，慵懒的找了一个靠窗的位置，沉沉的陷入软软的沙发里，招招手，要了一份拿铁，剩下的便是那静静的等待，而这等待也是一种享受，放下快速的节奏，放下生活的繁琐，只是静静的、静静的、等着属于自己的那份享受。");
        activity_seven_text2 = (TextView) findViewById(R.id.activity_seven_text2);
        activity_seven_text2.setText("      感受着深沉，感受着幽静，却突然这份感受被打断，大厅传出一声慵懒的猫叫，然而转眼间便忘却，就当是生活的调剂品罢了。目光又扫到桌面上的猫玩具，透着可爱，透着不一样的气息，对此笑笑，然而很久没有这么真诚的笑过了。。。\n     很快咖啡上了，然而没想到的是咖啡里面也有着一只猫，一只可爱的猫，调皮的对你笑着，无法打断自己的思绪，却仿佛使自己的思绪添加了一种“喵”的元素，静静的品着咖啡，享受着这份淡然，是心灵的升华与慰藉。");
        activity_seven_text3 = (TextView) findViewById(R.id.activity_seven_text3);
        activity_seven_text3.setText("      夕阳西下，静静的靠在窗前，外面是天高宽广的蓝天，里面却是一份幽静。\n     在这物欲横飞、的快速时代，难得的是有一个这样的咖啡馆，拥有着咖啡的悠闲却还有着猫的可爱慵懒的，时不时去感受一番，不妨也是一种享受吧。");

    }
}
