package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PageContent_Three extends Activity {
    private ImageView iv_return;//左上角返回图标
    private TextView header_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏
        setContentView(R.layout.activity_page_content__three);
        header_text= (TextView) findViewById(R.id.header_text);
        header_text.setText("端午节活动海报");

        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
