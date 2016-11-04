package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/10.
 */

public class Introduce_Activity extends Activity {


    private TextView tv_1;
    private TextView tv_2;

    private ImageView iv_return;//返回

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_layout);

        iv_return = (ImageView) findViewById(R.id.iv_return);//返回
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Introduce_Activity.this.finish();
            }
        });

        tv_1 = (TextView) findViewById(R.id.text1);
        tv_1.setText("      咖啡厅、餐馆、图书馆（图书馆装修效果图）已日益成为我们生活中不可替代的空间，latitude studio在这个空间中，探索将传统的咖啡厅功能与公共视觉艺术结合的可能。空间分两层，一楼通过去除玻璃外墙，形成一个扩展的公共开放空间。其内部包含两个区域：快饮区（快）和休闲区（慢）。快饮区选用高脚桌椅，临近服务区——由白色玻璃包裹的纵向柜台；休闲区采用矮桌，长椅紧贴墙壁。这两个区域用一个纵向钢制元素隔开，它包括镜子和投影仪，打造出一种奇妙的视觉错觉。");
        tv_2 = (TextView) findViewById(R.id.text2);
        tv_2.setText("      二楼为环形，利用照明采光，营造出一个集中的开放空间，可以提供不同类型的活动场地。可移动的墩状圆柱咖啡桌沿圆形长椅摆放，使用者可根据需要随意移动。设计完成的空间，超越提供咖啡和饮品的基本功能，成为一个公共活动空间，使顾客感受到不同的情绪氛围。");


    }
}
