package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oracle.oaec.androidproject.View.LoginActivity;
import com.oracle.oaec.androidproject.po.Music;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Song extends Activity {
    private TextView tv_close;//返回

    private TextView tv_cancel;
    private EditText ed_search;//搜索歌曲
    private ListView listView;
    private ProgressBar progressBar;

    private MusicAdapter musicAdapter;

    private List<Music.DataBean.DataBean2> musicArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);


        listView = (ListView) findViewById(R.id.lv_music);

        musicAdapter = new MusicAdapter();
        listView.setAdapter(musicAdapter);

        progressBar = (ProgressBar) findViewById(R.id.pb_flash);

        tv_close = (TextView) findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song.this.finish();
            }
        });

        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_search.setText("");
                tv_cancel.setText("");
                listView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                musicAdapter.notifyDataSetChanged();
            }
        });


        ed_search = (EditText) findViewById(R.id.ed_search);
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed_search == null || ed_search.equals("")) {
                    tv_cancel.setText("");
                    return;
                } else {
                    tv_cancel.setText("取消");
                    Log.d("Song1", "获取歌曲");
                    progressBar.setVisibility(View.VISIBLE);
                    //获取音乐的数据
                    FinalHttp finalHttp = new FinalHttp();
                    finalHttp.addHeader("apikey", "af5dc00d260a26e82d337ce40b34c733");
                    String musicstring = ed_search.getText().toString().intern();//获取搜索框的内容
                    finalHttp.get("http://apis.baidu.com/geekery/music/query?s=" + musicstring + "&&size=20&&page=1", new AjaxCallBack<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Log.d("Song1", "获取完毕");
                            progressBar.setVisibility(View.GONE);

                            Gson gs = new Gson();
                            Music music = gs.fromJson(o.toString(), Music.class);
                            if (music.getData() != null) {
                                listView.setVisibility(View.VISIBLE);
                                musicArrayList = music.getData().getData2();

                                musicAdapter.notifyDataSetChanged();
                            }
              /*  msg=handler.obtainMessage();
                msg.arg1=1;
                handler.sendMessage(msg);*/

                            super.onSuccess(o);
                        }

                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {
                            Log.d("Song1", "失败");
                            Toast.makeText(Song.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            super.onFailure(t, errorNo, strMsg);
                        }

                        @Override
                        public void onLoading(long count, long current) {
                            Log.d("Song1", "等待");
                            progressBar.setVisibility(View.VISIBLE);
                            super.onLoading(count, current);
                        }
                    });

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(LoginActivity.Urlusername==null){
                    Toast.makeText(Song.this,"用户没有登陆",Toast.LENGTH_SHORT).show();
                    return;
                }
                //时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                final String time = formatter.format(curDate);
                Toast.makeText(Song.this, time, Toast.LENGTH_SHORT).show();


                AlertDialog.Builder builder = new AlertDialog.Builder(Song.this);
                final String filename = musicArrayList.get(position).getFilename();
                final Music.DataBean.DataBean2 bean2 = musicArrayList.get(position);
                bean2.setTime(time);

                builder.setMessage(filename);//从数据源里面获取点击的歌曲

                builder.setTitle("提示：是否点播这首歌曲？");
                builder.setPositiveButton("取消", null);
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //把选择的歌曲与当前登录用户绑定后传入数据库
                        StringBuffer sb=new StringBuffer(ToUrl.Url);
                        sb.append("MusicServlet?mode=1&name=");
                        sb.append(ToString(bean2.getFilename()));
                        sb.append("&time=");
                        sb.append(ToString(bean2.getTime()));
                        sb.append("&user=");
                        sb.append(LoginActivity.Urlusername);
                        //  String s="http://10.0.12.102:8093/OneAndroidDemo/MusicServlet?mode=1&name=sssss&time=adsadsa";
                        Log.i("1234","准备发送数据"+sb.toString());
                        FinalHttp http=new FinalHttp();
                        //http.configCharset("gb2312");
                        http.get(sb.toString(), new AjaxCallBack<Object>() {
                            @Override
                            public void onFailure(Throwable t, int errorNo, String strMsg) {
                                Toast.makeText(Song.this,"访问服务器失败",Toast.LENGTH_SHORT).show();
                                super.onFailure(t, errorNo, strMsg);
                            }

                            @Override
                            public void onLoading(long count, long current) {
                                Log.i("1234","等待中");
                                super.onLoading(count, current);
                            }

                            @Override
                            public void onSuccess(Object o) {
                                Log.i("1234","访问服务器成功");
                                if (o.toString().equals("2")){
                                    Log.i("1234","失败");

                                }else {
                                    Toast.makeText(Song.this,"点歌成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                super.onSuccess(o);
                            }
                        });

//                        intent.setClass(Song.this, OnDemand.class);//从一个activity跳转到另一个activity
//                        intent.putExtra("str", filename);//给intent添加额外数据，key为“str”,key值为filename
//                        intent.putExtra("time", time);

//                        startActivityForResult(intent, 0);
//                        startActivity(intent);
                       /* Intent intent = new Intent();
                        intent.putExtra("duixiang", bean2);
                        setResult(RESULT_OK, intent);*/

                    }
                });

                builder.show();
            }
        });
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
    private class MusicAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return musicArrayList.size();
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
            View view = getLayoutInflater().inflate(R.layout.music_item, null);
            TextView filename = (TextView) view.findViewById(R.id.filename);
            TextView album_name = (TextView) view.findViewById(R.id.album_name);

            filename.setText(musicArrayList.get(position).getFilename());
            album_name.setText("专辑名：" + musicArrayList.get(position).getAlbum_name());

            return view;
        }
    }

}
