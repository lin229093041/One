package com.oracle.oaec.androidproject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StartViewPagerActivity extends AppCompatActivity {
    private ViewPager vp;
    private int[] data = new int[]{R.drawable.start_image1, R.drawable.start_image2, R.drawable.start_image3, R.drawable.start_image4};
    private PagerAdapter adapter;
    private ArrayList<ImageView> list;
    private ImageView img1, img2, img3, img4;
    private View btnview;
    private TextView btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view_pager);
        filldata();
        adapter = new MyViewPager();
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 页面滑动时触发
             * @param position 当前页面的下标
             * @param positionOffset 当前移动的百分比
             * @param positionOffsetPixels 当前移动的像素值
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             *  选择事件，最后viewpager停留在哪个页面，参数position就是这个页面的下标
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                resetImage();
                upImage(255);
                switch (position) {
                    case 0:
                        img1.setImageResource(R.drawable.dot_blur);
                        break;
                    case 1:
                        img2.setImageResource(R.drawable.dot_blur);
                        break;
                    case 2:
                        img3.setImageResource(R.drawable.dot_blur);
                        break;
                    case 3:
                        img4.setImageResource(R.drawable.dot_blur);
                        break;
                    case 4:
                        upImage(0);
                        break;
                    default:

                        break;
                }
            }

            /**
             * 滑动状态的改变，3种状态
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        Button btn_1= (Button)btnview.findViewById(R.id.btn_viewpager_start);

    }

    private void filldata() {
        vp = (ViewPager) findViewById(R.id.vp);
        img1 = (ImageView) findViewById(R.id.img_1);
        img2 = (ImageView) findViewById(R.id.img_2);
        img3 = (ImageView) findViewById(R.id.img_3);
        img4 = (ImageView) findViewById(R.id.img_4);
    }

    private void resetImage() {
        img1.setImageResource(R.drawable.dot_focus);
        img2.setImageResource(R.drawable.dot_focus);
        img3.setImageResource(R.drawable.dot_focus);
        img4.setImageResource(R.drawable.dot_focus);
    }

    private void upImage(int i) {
        img1.setAlpha(i);
        img2.setAlpha(i);
        img3.setAlpha(i);
        img4.setAlpha(i);
    }


    class MyViewPager extends PagerAdapter {
        @Override
        public int getCount() {
            return data.length + 1;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        /**
         * getview方法
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            if (position == 4) {
                btnview = getLayoutInflater().inflate(R.layout.start_page_02, null);
                btn_1 = (TextView) btnview.findViewById(R.id.btn_viewpager_start);
                btn_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent=new Intent(ViewPageActivity.this,MainActivity.class);
//                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                btnview = getLayoutInflater().inflate(R.layout.start_page_01, null);
                ImageView img = (ImageView) btnview.findViewById(R.id.start_page_01);
                img.setImageResource(data[position]);

            }
            container.addView(btnview);
            return btnview;
        }

        //不写可能会报错
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
