package com.oracle.oaec.androidproject;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oracle.oaec.androidproject.po.CaiPin;
import com.oracle.oaec.androidproject.po.CaipinMsg;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThreeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThreeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThreeFragment extends Fragment {
    private ListView lv_1;//左边栏
    private ListView lv_2;//右边栏
    private ArrayList<CaiPin> datas = new ArrayList<>();//左边栏数据
    private ArrayList<CaipinMsg> msg = new ArrayList<>();//右边栏的数据，在左边栏的数据中用到；
    private ArrayList<CaipinMsg> data2 = new ArrayList<>();//右边栏的数据（在右边栏的adapter中用到）
    private ArrayList<CaipinMsg> data3 = new ArrayList<>();
    private ArrayList<CaipinMsg> data4 = new ArrayList<>();//用于保存加入购物车的菜品
    private TextView tv_show;//左边栏的文字
    private CaipinMsg cmsg;//右边栏的菜品详情
    private BaseAdapter adapter;//左边栏配置的adapter
    private BaseAdapter adapter2;//右边栏配置的adapter
    private AdapterView.OnItemClickListener listener1;//左边栏的监听
    private AdapterView.OnItemClickListener listener2;//右边栏的监听
    private int lastPos = 0;//颜色标志位
    private TextView tv_num;//购买个数
    private String s1 = "";//
    private int num;//购买个数
    private ArrayList<Integer> a = new ArrayList<>();//保存每个菜品msg的个数
    private ImageView food_img;
    private Button btn_commit;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ThreeFragment() {
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
    public static ThreeFragment newInstance(String param1, String param2) {
        ThreeFragment fragment = new ThreeFragment();
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
        if(lv_1==null){
            Log.d("www","1234");
        }
        lv_1.setAdapter(adapter);
        lv_1.setOnItemClickListener(listener1);

        lv_2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (Integer a1 : a) {
                    if (firstVisibleItem == a1) {
                        //Log.i("1234",a1+"");
                    }
                }
            }
        });
        lv_2.setAdapter(adapter2);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            int money;

            @Override
            public void onClick(View v) {
                for (CaiPin data : datas) {
                    for (CaipinMsg d : data.getMsg()) {
                        i = Integer.valueOf(d.getD());
                        if (i != 0) {
                            data4.add(d);
                        }
                    }
                }
                for (CaipinMsg j : data4) {
                    money += Integer.valueOf(j.getD());
                }
                Log.i("1234", "价格为：" + money);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 对控件进行关联
     *
     * @param view
     */
    private void initmsg(View view) {
        btn_commit = (Button) view.findViewById(R.id.btn_commit);

        CaiPin c;
        lv_1 = (ListView) view.findViewById(R.id.lv_shopping_1);

        lv_2 = (ListView) view.findViewById(R.id.shopping_lv_2);
        cmsg = new CaipinMsg("1", "2", "3", "0");
        msg.add(cmsg);//添加菜品信息
        c = new CaiPin("虾", msg);
        //    a.add(msg.size());
        datas.add(c);
        /**
         *  数据2，必须要new 出来，不然两组数据是一样的
         */
        msg = new ArrayList<>();
        cmsg = new CaipinMsg("21", "22", "23", "0");
        msg.add(cmsg);
        cmsg = new CaipinMsg("21", "22", "23", "0");
        msg.add(cmsg);
        cmsg = new CaipinMsg("21", "22", "23", "0");
        msg.add(cmsg);
        cmsg = new CaipinMsg("21", "22", "23", "0");
        msg.add(cmsg);
        cmsg = new CaipinMsg("21", "22", "23", "0");
        msg.add(cmsg);
        cmsg = new CaipinMsg("21", "22", "23", "0");
        msg.add(cmsg);
        data2.add(cmsg);
        c = new CaiPin("炸虾", msg);
        //  a.add(msg.size()+a.get(a.size()-1));
        datas.add(c);
        for (int i = 0; i < 29; i++) {
            msg = new ArrayList<>();
            cmsg = new CaipinMsg("1", "2", "3", "0");
            msg.add(cmsg);//添加菜品信息
            c = new CaiPin("虾" + i, msg);
            //     a.add(msg.size()+a.get(a.size()-1));
            datas.add(c);
        }
        listener1 = new MyItemlis();
        // data2 = datas.get(0).getMsg();//使右边栏默认显示左边第一个菜品
        //初始化适配器
        adapter2 = new MyCaipinMagAdaperte();
        adapter = new MyCaipinAdaperte();
        for (CaiPin cp : datas) {
            for (CaipinMsg msg : cp.getMsg()) {
                data3.add(msg);
            }

        }
    }


    /**
     * 菜品的适配器（左边listview）
     */


    private class MyCaipinAdaperte extends BaseAdapter {

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
            View v = getActivity().getLayoutInflater().inflate(R.layout.item_shopping_left, null);
            tv_show = (TextView) v.findViewById(R.id.shopping_left_tv);
            if (position == lastPos) {
                tv_show.setTextColor(Color.BLUE);
            }
            tv_show.setText(datas.get(position).getCailei());
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

            return data2.size();
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
//            TextView tv = (TextView) v.findViewById(R.id.tv_right);
//            food_img = (ImageView) v.findViewById(R.id.food_img);
            tv_num = (TextView) v.findViewById(R.id.tv_num);
            final ImageButton btn_jian = (ImageButton) v.findViewById(R.id.btn_jian);
            ImageButton btn_jia = (ImageButton) v.findViewById(R.id.btn_jiahao);
            /**
             * 实时更新购买个数中的数字
             每次实体类中的数据发生改变时调用notifyDataSetChanged方法更新adapter
             */
            Log.i("1234", data2.toString() + "1" + data2.get(position).toString() + "2view" + v.toString());
            tv_num.setText(data2.get(position).getD());
            // 获取该商品已卖出个数
            String s = data2.get(position).getD();
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

                    String s = data2.get(position).getD();
                    num = Integer.valueOf(s);
                    num--;
                    data3.get(position).setD(num + "");
                    adapter2.notifyDataSetChanged();
                }
            });
            //对加号进行处理

            btn_jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取该商品已卖出个数

                    String s = data2.get(position).getD();
                    num = Integer.valueOf(s);
                    num++;
                    data2.get(position).setD(num + "");
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

            data2 = datas.get(position).getMsg();
            tv_show = (TextView) view.findViewById(R.id.shopping_left_tv);
            lastPos = position;
            s1 = datas.get(position).getCailei();
            adapter.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);


        return view;
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
