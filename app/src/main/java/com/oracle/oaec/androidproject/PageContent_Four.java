package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PageContent_Four extends Activity {
    private ImageView iv_return;//左上角返回图标

    private TextView activity_four_text1;
    private TextView activity_four_text2;
    private TextView activity_four_text3;

    private TextView header_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏
        setContentView(R.layout.activity_page_content__four);

        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        header_text= (TextView) findViewById(R.id.header_text);
        header_text.setText("一眼就爱上这种新格调");

        activity_four_text1 = (TextView) findViewById(R.id.activity_four_text1);
        activity_four_text1.setText("       每次逛街时，看上一件东西时，总觉得后面可能还有更好的，便毫不犹豫的继续逛下去，直到逛了好几家便还是觉得第一家比较好。有时候喜欢一件东西，只需一眼便被打动。可大多数人还是觉得后面还有更好的，这个“更”字说是更高层次的追求，却不知适合自己的才是最好的。");

        activity_four_text2 = (TextView) findViewById(R.id.activity_four_text2);
        activity_four_text2.setText("       走进一家咖啡店你是被里面的装饰品所吸引，还是被里面的一种氛围，一种格调所感动，从此，你便爱上了咖啡，爱上了这种格调，没有太多的理由，就在那一瞬间，你喜欢上它有一种不被世俗所污染的干净，你认为这就是自己的追求，这就是属于自己的风格。走进这家咖啡屋，就被这种干净，精致的装饰所感染，这是一种享受，是一种人生的态度，与吧台相般配的凳子，高度刚刚好，点上一杯自己喜欢的卡布奇诺或者是拿铁，总之是自己喜欢的，便觉得在这里度过一天都不为过，不用担心午餐或者晚餐，因为这里同样可以享受的到。这种明亮的灯光，照耀在你目光的每一处。");

        activity_four_text3 = (TextView) findViewById(R.id.activity_four_text3);
        activity_four_text3.setText("       这温馨的一角更是独具特色，更加适合小清新，小文艺的人，或者是喜欢安静的美男子or美女子，在或者是与久别重逢的朋友，伴侣在这里享受着不被外界纷扰的声音所打扰的漫时光。复古的照片墙和书柜里摆放的小熊娃娃，使整个布置更显得温馨。那一具灯饰更是吸引人的眼球，特别是以动物样子而做成的灯具。整体看上去，便不在想离开，这么让人留恋的地方，真想坐在里面，享受一下午的曼妙时光。");


    }
}
