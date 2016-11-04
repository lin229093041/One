package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.oracle.oaec.androidproject.po.UrlCaiPin;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity {
private EditText edt_search;
    private List<UrlCaiPin> urlCaiPin=new ArrayList<>();

    private BaseAdapter adapter;
    private ListView lv;
    private ImageView food_img;
    private int moneynum;
    private TextView search_tv_num;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        edt_search= (EditText) findViewById(R.id.editor_seach);
        lv= (ListView) findViewById(R.id.search_lv);
        adapter=new MyAdapter();
        lv.setAdapter(adapter);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                   String s1= edt_search.getText().toString();
                    StringBuffer sb=new StringBuffer(ToUrl.Url);
                sb.append("CaipinServlet?mode=2&likes=");
                sb.append(s1);
                FinalHttp http=new FinalHttp();
                http.get(sb.toString(), new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Gson gson=new Gson();
                        Log.i("linruoyu",o.toString());
                        urlCaiPin = gson.fromJson(o.toString(),new  TypeToken< List<UrlCaiPin>>(){}.getType());
                        adapter.notifyDataSetChanged();
                        Log.i("linruoyu",urlCaiPin.toString());
                        super.onSuccess(o);
                    }
                });
            }
        });
    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return urlCaiPin.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            /**
             * 关联控件方便之后对控件进行操作
             */
            View v = getLayoutInflater().inflate(R.layout.items_foods, null);
            TextView shopping_food_name = (TextView) v.findViewById(R.id.shopping_food_name);
            food_img = (ImageView) v.findViewById(R.id.shopping_food_img);
            TextView shopping_food_num = (TextView) v.findViewById(R.id.shopping_food_monthnum);
            TextView shopping_food_price = (TextView) v.findViewById(R.id.shopping_food_price);
            moneynum = 0;
            
                for (UrlCaiPin d : urlCaiPin) {
                    if (d.getNum() == null) {
                        d.setNum("0");
                    }
                    moneynum += Integer.valueOf(d.getNum()) * Integer.valueOf(d.getPrice());

                

            }
            
            search_tv_num = (TextView) v.findViewById(R.id.tv_num);
            final ImageButton btn_jian = (ImageButton) v.findViewById(R.id.btn_jian);
            ImageButton btn_jia = (ImageButton) v.findViewById(R.id.btn_jiahao);

            /**
             * 往控件中加入对应的数据
             */
            shopping_food_name.setText(urlCaiPin.get(position).getName());//美食名字
            shopping_food_price.setText("￥" + urlCaiPin.get(position).getPrice());//美食价格
            if (urlCaiPin.get(position).getMonth() == null) {
                urlCaiPin.get(position).setMonth("0");
            }
            shopping_food_num.setText(urlCaiPin.get(position).getMonth());
            try {
                //解析从网络中获取的图片资源
                DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher).build();
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(SearchActivity.this).diskCacheSize(1024 * 1024 * 10).memoryCacheSize(1024 * 1024 * 10).build();
                ImageLoader.getInstance().init(config);
                String picUrl = urlCaiPin.get(position).getImg();
                ImageLoader.getInstance().displayImage(picUrl, food_img, displayImageOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        super.onLoadingFailed(imageUri, view, failReason);
                        //图片加载失败后处理
                        food_img.setImageResource(R.drawable.bottom_home);

                    }
                });
            } catch (Exception e) {
                Log.i("1234", "1234");

            }
            /**
             * 实时更新购买个数中的数字
             每次实体类中的数据发生改变时调用notifyDataSetChanged方法更新adapter
             */
            if (urlCaiPin.get(position).getNum() == null) {
                urlCaiPin.get(position).setNum("0");
            }
            search_tv_num.setText(urlCaiPin.get(position).getNum());
            // 获取该商品已卖出个数
            String s = urlCaiPin.get(position).getNum();

            num = Integer.valueOf(s);
            // 对减号的处理
            if (num <= 0) {
                // 如果购买数为0，则只显示加号

                btn_jian.setVisibility(View.GONE);
                search_tv_num.setVisibility(View.GONE);
            } else {
                // 购买数大于0之后加号和购买数出现

                btn_jian.setVisibility(View.VISIBLE);
                search_tv_num.setVisibility(View.VISIBLE);
            }

            btn_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 获取该商品已卖出个数
                    moneynum -= Integer.valueOf(urlCaiPin.get(position).getPrice());
                    String s = urlCaiPin.get(position).getNum();
                    num = Integer.valueOf(s);
                    num--;
                    urlCaiPin.get(position).setNum(num + "");
                    //data3.get(position).setD(num + "");
                    adapter.notifyDataSetChanged();
                }
            });
            //对加号进行处理

            btn_jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取该商品已卖出个数
                    moneynum += Integer.valueOf(urlCaiPin.get(position).getPrice());
                    String s = urlCaiPin.get(position).getNum();
                    num = Integer.valueOf(s);
                    num++;
                    urlCaiPin.get(position).setNum(num + "");
                    adapter.notifyDataSetChanged();
                }
            });
            //对右侧标题填充文字

            // tv.setText(s1);
            /**
             * 对美食图片处理
             使美食图片变为正方形
             失败，有空再弄
             */


            /*if(height==0) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        int a=(250/12)*5;
                       // height = food_img.getHeight();
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) food_img.getLayoutParams();
                        params.width = a;
                        food_img.setLayoutParams(params);
                        adapter2.notifyDataSetChanged();
                    }
                });
            }*/
            return v;
        }
    }
}
