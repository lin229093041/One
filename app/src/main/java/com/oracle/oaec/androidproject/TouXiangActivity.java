package com.oracle.oaec.androidproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.oracle.oaec.androidproject.View.LoginActivity;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TouXiangActivity extends AppCompatActivity {
    private GridView gv_touxiang;
    private List<String> datas = new ArrayList<String>(10);
    private BaseAdapter adapter;
    private Intent intent;
    private String returnposition = "";
    private int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_xiang);

        gv_touxiang = (GridView) findViewById(R.id.gv_touxiang);
        init();
        gv_touxiang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                returnposition = datas.get(position);
                showMyDialog(position);
            }
        });
        adapter = new MyAdapter();
        gv_touxiang.setAdapter(adapter);
    }

    private void init() {
        for(int i=1;i<5;i++) {
            StringBuffer sb = new StringBuffer(ToUrl.Url);
            sb.append("touxiangimg/t");
            sb.append(i+"");
            sb.append(".jpg");
            datas.add(sb.toString());
        }
    }
    private String ToString (String s){
        String str="";
        try {
            str=java.net.URLEncoder.encode(s,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    private void showMyDialog(int i) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(TouXiangActivity.this);
        j=i;
        builder.setTitle("友情提示");
        builder.setMessage("是否选择该图片作为头像?");
        builder.setPositiveButton("确定", listener);
        builder.setNegativeButton("取消", listener);
        builder.show();
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                //确定按钮
                case DialogInterface.BUTTON_POSITIVE:
                    StringBuffer sb=new StringBuffer(ToUrl.Url);
                    sb.append("UserServlet?mode=3&name=");
                    sb.append(LoginActivity.Urlusername);
                    sb.append("&img=");
                    sb.append(ToString(datas.get(j)));
                    Log.i("121212",sb.toString());
                    FinalHttp http=new FinalHttp();
                    http.get(sb.toString(), new AjaxCallBack<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(TouXiangActivity.this,"修改头像成功",Toast.LENGTH_SHORT).show();
                            super.onSuccess(o);
                        }
                    });
                    setResult(1, intent);
                    TouXiangActivity.this.finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    };

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
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
            View v = convertView;
            MyHolder myHolder;
            if (null == v) {
                v = getLayoutInflater().inflate(R.layout.touxiang_item, null);
                myHolder = new MyHolder();
                myHolder.imageView = (ImageView) v.findViewById(R.id.iv_touxiang);
                v.setTag(myHolder);
            } else {
                myHolder = (MyHolder) v.getTag();
            }
            try {
                //解析从网络中获取的图片资源
                DisplayImageOptions displayImageOptions=new DisplayImageOptions.Builder(

                ).cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher).build();
                ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(TouXiangActivity.this).diskCacheSize(1024*1024 * 10).memoryCacheSize(1024 * 1024 * 10).build();
                ImageLoader.getInstance().init(config);
                String picUrl = datas.get(position);
                Log.i("1212",picUrl);
                ImageLoader.getInstance().displayImage(picUrl,myHolder.imageView,displayImageOptions,new SimpleImageLoadingListener(){
                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        super.onLoadingFailed(imageUri, view, failReason);
                        //图片加载失败后处理


                    }
                });
            } catch (Exception e) {
                Log.i("1234","1234");

            }
//            myHolder.imageView.setImageResource(a[position]);
            return v;
        }
        class MyHolder {
            ImageView imageView;
        }
    }
    }

