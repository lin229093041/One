package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.oracle.oaec.androidproject.View.LoginActivity;
import com.oracle.oaec.androidproject.po.Music;
import com.oracle.oaec.androidproject.po.UrlMusic;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class OnDemand extends Activity {
    private ImageView iv_return;
    private TextView btn_demand;
    private StringBuffer sbf =new StringBuffer();
    private Intent intent;//跳转页面
    private Message msg;
    private List<UrlMusic> cp=new ArrayList<>();
    private ListView lv_demand;
    private DemandAdapter adapter;
    private ImageView img;
    private TextView tv_time;

    private ArrayList<Music.DataBean.DataBean2> list = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int i = msg.what;
            if(i==1){

                adapter.notifyDataSetChanged();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_demand);

        lv_demand = (ListView) findViewById(R.id.lv_demand);

        tv_time = (TextView) findViewById(R.id.tv_time);

        adapter = new DemandAdapter();
        lv_demand.setAdapter(adapter);
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        init2();
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();


        iv_return = (ImageView) findViewById(R.id.iv_return);//返回
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDemand.this.finish();
            }
        });

        btn_demand = (TextView) findViewById(R.id.btn_demand);//点歌
        btn_demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到选择歌曲页面
                intent = new Intent();
                intent.setClass(OnDemand.this, Song.class);

                startActivityForResult(intent, 0);
//                finish();
            }
        });
    }
    private void init2() throws IOException {
      StringBuffer sb=new StringBuffer(ToUrl.Url);

        sb.append("MusicServlet?mode=2");
        URL url=new URL(sb.toString());
        sbf.setLength(0);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead=null;
        while((strRead=reader.readLine())!=null){
            sbf.append(strRead);
        }
        reader.close();
        Log.i("123456",sbf.toString());
        String decode = URLDecoder.decode(sbf.toString(), "utf-8");
        Gson gson = new Gson();
        if(LoginActivity.Urlusername==null){
            return;
        }
        cp = gson.fromJson(decode, new TypeToken<List<UrlMusic>>() {
        }.getType());

        msg=handler.obtainMessage();
        msg.what=1;
        handler.sendMessage(msg);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
//            String str = data.getStringExtra("str");
//            String time = data.getStringExtra("time");
            Music.DataBean.DataBean2 duixiang = (Music.DataBean.DataBean2) data.getSerializableExtra("duixiang");
            list.add(duixiang);

            adapter.notifyDataSetChanged();
        }


    }


    public class DemandAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cp.size();
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

            View view = getLayoutInflater().inflate(R.layout.send_music_item, null);
            TextView musicname = (TextView) view.findViewById(R.id.tv_musicname);
            TextView music_people_name= (TextView) view.findViewById(R.id.lv_music_people_name);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
            img= (ImageView) view.findViewById(R.id.music_user_img);
            music_people_name.setText(cp.get(position).getOne_user_name());
            musicname.setText(cp.get(position).getName());
            tv_time.setText(cp.get(position).getTime());
            //解析从网络中获取的图片资源
            DisplayImageOptions displayImageOptions=new DisplayImageOptions.Builder(

            ).cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher).build();
            ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(OnDemand.this).diskCacheSize(1024*1024 * 10).memoryCacheSize(1024 * 1024 * 10).build();
            ImageLoader.getInstance().init(config);
            String picUrl = cp.get(position).getUser_img();
            Log.i("1212",picUrl);
            ImageLoader.getInstance().displayImage(picUrl,img,displayImageOptions,new SimpleImageLoadingListener(){
                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    //图片加载失败后处理


                }
            });
//            musicname.setText(list.get(position).getFilename());
//            tv_time.setText(list.get(position).getTime());

            return view;
        }
    }


}
