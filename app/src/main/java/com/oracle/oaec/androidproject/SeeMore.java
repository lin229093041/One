package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeeMore extends Activity {
    private ImageView iv_return;

    private ArrayList<String> list;
    private SeeMoreAdapter adapter;
    private ListView listView;

    private Intent intent;//跳转页面

    private int[] lv_iamge = new int[]{R.drawable.lv_image1, R.drawable.activity_two_2, R.drawable.lv_image2, R.drawable.activity_four_2, R.drawable.activity_five_image2, R.drawable.activity_six_2, R.drawable.activity_seven_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置去掉自带的标题栏

        setContentView(R.layout.activity_see_more);

        listView = (ListView) findViewById(R.id.lv_see_more);
        //填充数据
        fillData();

        adapter = new SeeMoreAdapter();
        listView.setAdapter(adapter);

        //点击左上角返回图标的监听事件
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(SeeMore.this, "第一个", Toast.LENGTH_SHORT).show();

                        //跳转到第一个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_One.class);
                        startActivity(intent);

                        break;
                    case 1:
                        Toast.makeText(SeeMore.this, "第二个", Toast.LENGTH_SHORT).show();

                        //跳转到第二个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_Two.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(SeeMore.this, "第三个", Toast.LENGTH_SHORT).show();

                        //跳转到第三个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_Three.class);
                        startActivity(intent);
                        break;

                    case 3:
                        Toast.makeText(SeeMore.this, "第四个", Toast.LENGTH_SHORT).show();

                        //跳转到第四个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_Four.class);
                        startActivity(intent);
                        break;
                    case 4:
                        Toast.makeText(SeeMore.this, "第五个", Toast.LENGTH_SHORT).show();

                        //跳转到第五个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_Five.class);
                        startActivity(intent);
                        break;
                    case 5:
                        Toast.makeText(SeeMore.this, "第六个", Toast.LENGTH_SHORT).show();

                        //跳转到第六个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_Six.class);
                        startActivity(intent);
                        break;
                    case 6:
                        Toast.makeText(SeeMore.this, "第七个", Toast.LENGTH_SHORT).show();

                        //跳转到第七个页面
                        intent = new Intent();
                        intent.setClass(SeeMore.this, PageContent_Seven.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }
            }
        });

    }

    //数据
    private class SeeMoreAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.two_items, null);
            ImageView iv_item = (ImageView) view.findViewById(R.id.iv_two_item);
            TextView tv_item = (TextView) view.findViewById(R.id.tv_two_item);

            iv_item.setImageResource(lv_iamge[position]);
            tv_item.setText(list.get(position));


            return view;
        }

    }

    private void fillData() {
        list = new ArrayList<String>();
        list.add(new String("浪漫七夕感恩回馈活动"));
        list.add(new String("2016第三季新品发布"));
        list.add(new String("端午节活动海报"));

        list.add(new String("一眼就爱上这种新格调"));
        list.add(new String("伴在当下，许在未来——巡店感悟"));

        list.add(new String("我与咖啡有个约会"));
        list.add(new String("感受咖啡，感受典雅生活"));
    }

}
