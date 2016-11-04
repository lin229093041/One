package com.oracle.oaec.androidproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.oracle.oaec.androidproject.View.LoginActivity;
import com.oracle.oaec.androidproject.po.CaiPin;
import com.oracle.oaec.androidproject.po.CaipinMsg;
import com.oracle.oaec.androidproject.po.UrlCaiPin;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment {
    private ListView lv_1;//左边栏
    private ListView lv_2;//右边栏
    private TextView shopping_cart_price;
    private ArrayList<CaiPin> datas = new ArrayList<>();//左边栏数据
    private ArrayList<CaipinMsg> data4 = new ArrayList<>();//用于保存加入购物车的菜品
    int moneynum = 0;
    List<UrlCaiPin> urldata6 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> urldata7 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> urldata8 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> urldata5 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> urldata4 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> urldata3 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> urldata2 = new ArrayList<UrlCaiPin>();
    List<UrlCaiPin> leftdata = new ArrayList<>();
    List<UrlCaiPin> numdata = new ArrayList<>();
    private CarDialog dialog;
    ArrayList<List<UrlCaiPin>> urldata1 = new ArrayList<>();
    private TextView tv_show;//左边栏的文字
    private BaseAdapter adapter;//左边栏配置的adapter
    private BaseAdapter adapter2;//右边栏配置的adapter
    private AdapterView.OnItemClickListener listener1;//左边栏的监听
    private int lastPos = 0;//颜色标志位
    private TextView tv_num;//购买个数
    private int num;//购买个数
    private ImageView food_img;
    private TextView btn_commit;
    private ImageButton shopping_cart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;


    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCartFragment newInstance(String param1, String param2) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initmsg(view);//填充和刷新数据

        btn_commit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int i=0;
                if(LoginActivity.Urlusername==null){
                    Toast.makeText(getContext(),"用户还没登陆，请登陆后继续操作",Toast.LENGTH_SHORT).show();
                    return;
                }
                for (List<UrlCaiPin> data : urldata1) {
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
                for (UrlCaiPin d : numdata) {
                    d.setPeople(LoginActivity.Urlusername);
                }
                Gson gson = new Gson();
                String url = gson.toJson(numdata);
                Log.i("linruoyu",url);
                StringBuffer sb = new StringBuffer(ToUrl.Url);

                sb.append("BillServlet?mode=1&url=");
                sb.append(ToString(url));
                Log.i("www", url.toString());
                FinalHttp http = new FinalHttp();
                http.get(sb.toString(), new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getContext(), "订单信息已提交到服务端", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        Toast.makeText(getContext(), "进入servlet失败,请检查网络设置", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        super.onViewCreated(view, savedInstanceState);
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


    /**
     * 对控件进行关联
     *
     * @param view
     */
    private void initmsg(View view) {
        listener1 = new MyItemlis();

        shopping_cart_price = (TextView) view.findViewById(R.id.shopping_cart_price);
        ImageButton search= (ImageButton) view.findViewById(R.id.shopping_cart_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
        lv_1 = (ListView) view.findViewById(R.id.shopping_lv_1);
        lv_2 = (ListView) view.findViewById(R.id.shopping_lv_2);

        lv_1.setAdapter(adapter);
        lv_1.setOnItemClickListener(listener1);
        lv_2.setAdapter(adapter2);
        //初始化适配器
        adapter2 = new MyCaipinMagAdaperte();
        adapter = new MyCaipinAdaperte();
        btn_commit = (TextView) view.findViewById(R.id.btn_commit);
        shopping_cart = (ImageButton) view.findViewById(R.id.shopping_cart);
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getContext(), "提交订单成功", Toast.LENGTH_SHORT);
              /*  int money=0;
                for (UrlCaiPin j : numdata) {
                        money += Integer.valueOf(j.getNum());

                    }
                    Log.i("1234", "价格为：" + money);*/

                dialog = new CarDialog(getContext());
                dialog.setOnButtonClickListener(new CarDialog.OnButtonClickListener() {
                    @Override
                    public void onButtonClick(List<List<UrlCaiPin>> list) {
                        urldata1= (ArrayList<List<UrlCaiPin>>) list;
                        adapter.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();

                    }
                });
                dialog.setList(urldata1);
                dialog.show();
            }
        });
        FinalHttp http = new FinalHttp();
        StringBuffer sb = new StringBuffer(ToUrl.Url);
        sb.append("CaipinServlet?mode=1");
        http.get(sb.toString(), new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.i("www", "成功获取数据");
                Gson gson = new Gson();
                Log.i("linruoyu",o.toString());
                List<UrlCaiPin> cp = gson.fromJson(o.toString(), new TypeToken<List<UrlCaiPin>>() {
                }.getType());

                for (UrlCaiPin c1 : cp) {
                    if (c1.getMsg().equals("海鲜")) {
                        urldata2.add(c1);
                    } else if (c1.getMsg().equals("青菜")) {
                        urldata3.add(c1);
                    } else if (c1.getMsg().equals("汤")) {
                        urldata4.add(c1);
                    } else if (c1.getMsg().equals("小炒")) {
                        urldata5.add(c1);
                    } else if (c1.getMsg().equals("小吃")) {
                        urldata6.add(c1);
                    } else if (c1.getMsg().equals("饮料")) {
                        urldata7.add(c1);
                    } else if (c1.getMsg().equals("粥")) {
                        urldata8.add(c1);
                    }
                }

                urldata1.add(urldata2);
                urldata1.add(urldata3);
                urldata1.add(urldata4);
                urldata1.add(urldata5);
                urldata1.add(urldata6);
                urldata1.add(urldata7);
                urldata1.add(urldata8);
//                Log.i("www", urldata1.get(2).get(0).getMsg() + "12");
                adapter = new MyCaipinAdaperte();
                lv_1.setAdapter(adapter);
                leftdata = urldata1.get(0);
                lv_2.setAdapter(adapter2);
                // adapter.notifyDataSetChanged();
                super.onSuccess(o);
            }
        });

    }

    /**
     * 菜品的适配器（左边listview）
     */


    private class MyCaipinAdaperte extends BaseAdapter {

        @Override
        public int getCount() {
            return urldata1.size();
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
            View v = getActivity().getLayoutInflater().inflate(R.layout.item_shopping_left, null);
            tv_show = (TextView) v.findViewById(R.id.shopping_left_tv);

            if (position == lastPos) {
                tv_show.setTextColor(Color.BLUE);
            }
            if (urldata1.get(position).get(0) != null) {
                tv_show.setText(urldata1.get(position).get(0).getMsg());
            }
            //左边adapter
            return v;
        }
    }

    /**
     * 菜品信息适配器（右边listview）
     */


    private class MyCaipinMagAdaperte extends BaseAdapter {

        @Override
        public int getCount() {

            return leftdata.size();
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
            View v = getActivity().getLayoutInflater().inflate(R.layout.items_foods, null);
            TextView shopping_food_name = (TextView) v.findViewById(R.id.shopping_food_name);
            food_img = (ImageView) v.findViewById(R.id.shopping_food_img);
            TextView shopping_food_num = (TextView) v.findViewById(R.id.shopping_food_monthnum);
            TextView shopping_food_price = (TextView) v.findViewById(R.id.shopping_food_price);
            moneynum = 0;
            for (List<UrlCaiPin> data : urldata1) {
                for (UrlCaiPin d : data) {
                    if (d.getNum() == null) {
                        d.setNum("0");
                    }
                    moneynum += Integer.valueOf(d.getNum()) * Integer.valueOf(d.getPrice());

                }

            }
            shopping_cart_price.setText("￥" + moneynum);
            tv_num = (TextView) v.findViewById(R.id.tv_num);
            final ImageButton btn_jian = (ImageButton) v.findViewById(R.id.btn_jian);
            ImageButton btn_jia = (ImageButton) v.findViewById(R.id.btn_jiahao);

            /**
             * 往控件中加入对应的数据
             */
            shopping_food_name.setText(leftdata.get(position).getName());//美食名字
            shopping_food_price.setText("￥" + leftdata.get(position).getPrice());//美食价格
            if (leftdata.get(position).getMonth() == null) {
                leftdata.get(position).setMonth("0");
            }
            shopping_food_num.setText(leftdata.get(position).getMonth());
            try {
                //解析从网络中获取的图片资源
                DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher).build();
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext()).diskCacheSize(1024 * 1024 * 10).memoryCacheSize(1024 * 1024 * 10).build();
                ImageLoader.getInstance().init(config);
                String picUrl = leftdata.get(position).getImg();
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
            if (leftdata.get(position).getNum() == null) {
                leftdata.get(position).setNum("0");
            }
            tv_num.setText(leftdata.get(position).getNum());
            // 获取该商品已卖出个数
            String s = leftdata.get(position).getNum();

            num = Integer.valueOf(s);
            // 对减号的处理
            if (num <= 0) {
                // 如果购买数为0，则只显示加号

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
                    moneynum -= Integer.valueOf(leftdata.get(position).getPrice());
                    String s = leftdata.get(position).getNum();
                    num = Integer.valueOf(s);
                    num--;
                    leftdata.get(position).setNum(num + "");
                    //data3.get(position).setD(num + "");
                    adapter2.notifyDataSetChanged();
                }
            });
            //对加号进行处理

            btn_jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取该商品已卖出个数
                    moneynum += Integer.valueOf(leftdata.get(position).getPrice());
                    String s = leftdata.get(position).getNum();
                    num = Integer.valueOf(s);
                    num++;
                    leftdata.get(position).setNum(num + "");
                    adapter2.notifyDataSetChanged();
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

    private class MyItemlis implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            leftdata = urldata1.get(position);
            lastPos = position;
//            s1 = datas.get(position).getCailei();
            adapter.notifyDataSetChanged();
            adapter2 = new MyCaipinMagAdaperte();
            lv_2.setAdapter(adapter2);
//            adapter2.notifyDataSetChanged();


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
