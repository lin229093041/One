package com.oracle.oaec.androidproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oracle.oaec.androidproject.View.LoginActivity;
import com.oracle.oaec.androidproject.po.UrlCaiPin;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 49597679 on 2016/7/10.
 */
public class CarDialog extends Dialog {

    public CarDialog(Context context) {

        super(context, R.style.WaitDialogTheme);
    }

    private ListView lv_car;
    private BaseAdapter adapter;
    private List<String> names = new ArrayList<>();
    private List<List<UrlCaiPin>> list = new ArrayList<>();
    private List<UrlCaiPin> numdata = new ArrayList<>();

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private int money;
    private int num;
    private TextView tv_money;
    private TextView jiesuan;

    public List<List<UrlCaiPin>> getList() {
        return list;
    }

    public void setList(List<List<UrlCaiPin>> list) {
        this.list = list;
    }

    private OnButtonClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialoglayout);
        Button btn = (Button) findViewById(R.id.shopping_cart_btn);
        tv_money = (TextView) findViewById(R.id.shopping_cart_money);
        jiesuan = (TextView) findViewById(R.id.shopping_cart_jiesuan1);
        int i = 0;

        for (List<UrlCaiPin> data : list) {
            for (UrlCaiPin d : data) {
                if (d.getNum() == null) {
                    d.setNum("0");
                }
                i = Integer.valueOf(d.getNum());
                if (i != 0) {
                    numdata.add(d);
                }
            }
        }
        for (UrlCaiPin j : numdata) {
            money += Integer.valueOf(j.getNum()) * Integer.valueOf(j.getPrice());

        }
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginActivity.Urlusername==null){
                    Toast.makeText(getContext(),"用户还没登陆，请登陆后继续操作",Toast.LENGTH_SHORT).show();
                    return;
                }
                for (UrlCaiPin d : numdata) {
                    d.setPeople(LoginActivity.Urlusername);
                }
                Gson gson = new Gson();
                String url = gson.toJson(numdata);
                StringBuffer sb = new StringBuffer(ToUrl.Url);

                sb.append("/BillServlet?mode=1&url=");
                sb.append(ToString(url));
                Log.i("www", sb.toString());
                FinalHttp http = new FinalHttp();
                http.get(sb.toString(), new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getContext(), "进入servlet", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        Toast.makeText(getContext(), "进入servlet失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onButtonClick(getList());
                CarDialog.this.cancel();
            }
        });

        lv_car = (ListView) findViewById(R.id.lv_car);
        adapter = new MyAdapter();
        lv_car.setAdapter(adapter);
    }

    private String ToString(String s) {
        String str = "";
        try {
            str = java.net.URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return numdata.size();
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
            View v = getLayoutInflater().inflate(R.layout.item, null);

            TextView textView = (TextView) v.findViewById(R.id.name);
            TextView tv_num = (TextView) v.findViewById(R.id.num);
            ImageButton btn_jia = (ImageButton) v.findViewById(R.id.image_jia);
            ImageButton btn_jian = (ImageButton) v.findViewById(R.id.image_jian);

            tv_money.setText("￥" + money);
            textView.setText(numdata.get(position).getName());

            tv_num.setText(numdata.get(position).getNum());
            // 获取该商品已卖出个数
            String s = numdata.get(position).getNum();

            num = Integer.valueOf(s);
            // 对减号的处理
            if (num <= 0) {
                // 如果购买数为0，则只显示加号
                list.remove(position);
                adapter.notifyDataSetChanged();
                btn_jian.setVisibility(View.GONE);
                tv_num.setVisibility(View.GONE);
            } else {
                // 购买数大于0之后加号和购买数出现

                btn_jian.setVisibility(View.VISIBLE);
                tv_num.setVisibility(View.VISIBLE);
            }

            btn_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 获取该商品已卖出个数
                    money -= Integer.valueOf(numdata.get(position).getPrice());
                    String s = numdata.get(position).getNum();
                    num = Integer.valueOf(s);
                    num--;
                    numdata.get(position).setNum(num + "");
                    //data3.get(position).setD(num + "");
                    adapter.notifyDataSetChanged();
                }
            });
            //对加号进行处理

            btn_jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取该商品已卖出个数
                    money += Integer.valueOf(numdata.get(position).getPrice());
                    String s = numdata.get(position).getNum();
                    num = Integer.valueOf(s);
                    num++;
                    numdata.get(position).setNum(num + "");
                    adapter.notifyDataSetChanged();
                }
            });
            return v;
        }
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener {
        public void onButtonClick(List<List<UrlCaiPin>> list);
    }

}
