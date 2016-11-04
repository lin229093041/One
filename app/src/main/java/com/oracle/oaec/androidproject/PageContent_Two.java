package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PageContent_Two extends Activity {
    private ImageView iv_return;

    private TextView activity_two_text1;
    private TextView activity_two_text2;
    private TextView header_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏
        setContentView(R.layout.activity_page_content__two);

        header_text= (TextView) findViewById(R.id.header_text);
        header_text.setText("2016第三季新品发布");
        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activity_two_text1 = (TextView) findViewById(R.id.activity_two_text1);
        activity_two_text1.setText("        这个世界上不会变动的就是变动本身，与时俱进也是漫猫一直在做的事。互联网在不断进步，人们思想在不断转变，唯有不断推出新品，才能避免消费者的审美疲劳。特别是现在的年轻人，都拥有着猎奇心理，他们不断追求时尚，变相同为不同，争取走在时代的前端。新旧事物更新之快，我们在保证原来产品味道的同时，不断创新。");
        activity_two_text2 = (TextView) findViewById(R.id.activity_two_text2);
        activity_two_text2.setText("        同时为了根据季节的变化和市场的需要，在不断优化已有的产品，最大化满足顾客的需求。现在我们又推出第三季新品和一些产品价格的调整。本着从客户的利益出发的原则，新推出10个新产品，精益求精，在秋冬到来之季，饮品分为冷热口感。10个系列，包含13个单品。新推出的甜点的口味与当下季节更为般配。同时为了感谢顾客一直以来的支持与配合，我们在部分产品物料上也做了一下略微的调整。");

    }
}
